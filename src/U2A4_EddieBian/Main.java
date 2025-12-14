package U2A4_EddieBian;
/**
 * Grocery Store Inventory
 * Eddie
 * July 14, 2025
 * Allows user to interact with a file (inventory.txt) that stores a lot of information about items
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        // initial variables
        boolean running = true;
        ArrayList<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); // initialize scanner


        // loop through file, add lines to arraylist
        try {
            Scanner input = new Scanner(new File("inventory.txt"));  // open file
            while (input.hasNext()) {
                // create scanner from each line in file
                String line = input.nextLine();
                Scanner lineItems = new Scanner(line);

                // separate values from the line
                lineItems.useDelimiter(","); // delimiter as comma
                String sku = lineItems.next();
                String name = lineItems.next();
                String category = lineItems.next();
                int quantity = Integer.parseInt(lineItems.next());
                int minimumQuantity = Integer.parseInt(lineItems.next());
                double vendorPrice = Double.parseDouble(lineItems.next());
                double markupPercetange = Double.parseDouble(lineItems.next());
                double regularPrice = Double.parseDouble(lineItems.next());
                double currentDiscount = Double.parseDouble(lineItems.next());
                double currentPrice = Double.parseDouble(lineItems.next());

                // create new item object of parsed variables from lines
                Item item = new Item(sku, name, category, quantity, minimumQuantity, vendorPrice, markupPercetange, regularPrice, currentDiscount, currentPrice);
                items.add(item); // add item object to arraylist

                lineItems.close();
            }
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        }


        // main menu loop
        while (running) {
            // menu items
            System.out.println("Tile Price Calculator");
            System.out.println("================");
            System.out.println("1: Query the inventory");
            System.out.println("2: Add item");
            System.out.println("3: Exit");

            int option = getIntInput("Your choice: ", scanner); // takes the user's input

            if (option == 1) {
                // query inventory based on sku/name
                queryInventory(scanner, items);
            } else if (option == 2) {
                // add items
                addItem(scanner, items);
            } else if (option == 3) {
                // exit
                running = false;
            } else {
                // input is not 1-7
                System.out.println("Invalid option");
            }

            // continuing
            if (!getStringInput("\nEnter any character to continue: ", scanner).isEmpty()) {
                System.out.print("\033[H\033[2J"); // moves mouse to top left
                System.out.flush(); // clears screen
            }
        }

        scanner.close(); // closes the scanner
    }

    // MENU OPTIONS
    /**
     * Query inventory based on SKU or name. display the data if found, otherwise display error message
     * @param   scanner     the scanner to receive input from user
     * @param   items       arraylist that holds all the Item objects
    */
    public static void queryInventory(Scanner scanner, ArrayList<Item> items) {
        // receive input from user
        String identifier = getStringInput("Enter SKU/name: ", scanner).toLowerCase();
        boolean found = false; // used at end to display error messages

        // loop through items in arraylist and check if the SKU or name is the same as input
        // checks for matching name + SKU at same time
        for (Item item : items) {
            // gets two variables with getter
            String itemSuk = item.getSku().toLowerCase();
            String itemName = item.getName().toLowerCase();

            // check if user input is either equal to a sku/name of item
            if (identifier.equals(itemSuk) || identifier.equals(itemName)) {
                // display the item in a formatted way
                displayItem(item);
                found = true; // set variable found to true
                // break; - in case there are multiple items with the same name
            }
        }

        // if it isn't found, display error
        if (found == false) {
            // check if its valid SKU
            if (validSku(identifier)) {
                // if its identified as sku
                System.out.println("Valid SKU, but not found in file");
            } else {
                // if it isn't identified as a valid sku
                System.out.println("Invalid SKU/name not found in file");
            }
        }
    }

    /**
     * Add item to arraylist, update inventory.txt file on confirmation (ONLY GETS NEEDED INFO)
     * @param   scanner     the scanner to receive input from user
     * @param   items       arraylist that holds all the Item objects
    */
    public static void addItem(Scanner scanner, ArrayList<Item> items) {
        // Item constructor variables
        String name;
        String category;
        int categoryOption;
        int quantity;
        int minimumQuantity;
        double vendorPrice;
        double markupPercetange;
        double currentDiscount;

        // name – max 20 characters
        while (true) {
            name = getStringInput("Enter name: ", scanner); // gets user input

            // check if input is in range
            if (name.length() > 20) {
                // error message
                System.out.println("Name cannot be more than 20 characters");
            } else {
                break;
            }
        }

        // category (this used for SKU - user chooses an option from list - ALL CAPS)
        while (true) {
            categoryOption = getIntInput("\n1: FRUIT\n2: VEGETABLE\n3: MEAT\nYour choice: ", scanner);

            // check if input is in range
            if (categoryOption > 0 && categoryOption <= 3) {
                // change category from choice to string
                if (categoryOption == 1) {
                    category = "FRUIT";
                } else if (categoryOption == 2) {
                    category = "VEGETABLE";
                } else {
                    category = "MEAT";
                }
                break;
            } else {
                System.out.println("Choice must be 1-3");
            }
        }

        // quantity - available in inventory
        quantity = getIntInput("Enter quantity: ", scanner);

        // minimumQuantity
        minimumQuantity = getIntInput("Enter minimum quantity: ", scanner);

        // vendorPrice
        vendorPrice = getDoubleInput("Enter vendor price: $", scanner);

        // markupPercetange
        markupPercetange = getDoubleInput("Enter markup percetange (as decimal x 100): ", scanner);

        // currentDiscount
        currentDiscount = getDoubleInput("Enter current discount percetange (as decimal x 100): ", scanner);


        // --------VARIABLES that depend on other variables
        // sku - gets the next number for the category with getSkuNum method
        String sku = category.substring(0, 3) + "-" + getSkuNum(category, items);

        // regularPrice - https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        // formula for the unrounded number: vendor * ((100+markup)/100)
        double regularPrice = Math.round((vendorPrice * ((100+markupPercetange)/100)) * 100) / 100.0;

        // currentPrice
        // unrounded number: reg price * ((100-current discount percent)/100)
        double currentPrice = Math.round((regularPrice * ((100-currentDiscount)/100)) * 100) / 100.0;

        // create new item
        Item newItem = new Item(sku, name, category, quantity, minimumQuantity, vendorPrice, markupPercetange, regularPrice, currentDiscount, currentPrice);

        items.add(newItem); // add new item to arraylist


        // save the file - updated arraylist (note; when i tried running this + very simple write code, it didn't show anything when i clicked the file on sidebar)
        try {
            // create new writer instance
            // BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"));
            PrintWriter writer = new PrintWriter("inventory.txt");

            // loop through writer and write the updated things to the file (using the toString()) - uses format so that it can be read again
            for (int i = 0; i < items.size(); i++) {
                // add the formatted (toString) line to the file
                writer.println(items.get(i).toString());
            }

            System.out.println("Success! Written " + items.size() + " to file"); // succes message

            writer.close(); // close writer
        } catch (IOException e) {
            // catch the error
            System.out.println("Error, cannot access file");
        }
    }

    /**
     * Generate the next SKU number based on current ones in the arraylist
     * @param   category    item category
     * @param   items       arraylist of all the Item objects
     * @return              the next sku number as a string
    */
    public static String getSkuNum(String category, ArrayList<Item> items) {
        int nextNum = 0;

        for (Item item : items) {
            // get the category of the item (eg. MEA)
            String[] splitItem = item.getSku().split("-"); // first, split it into two eg MEA and 0014
            String itemCategory = splitItem[0]; // the category part
            int itemNum = Integer.parseInt(splitItem[1]); // the number

            // check if the category is the same + the item number is bigger than next num
            if (itemCategory.equals(category.substring(0, 3)) && itemNum > nextNum) {
                nextNum = itemNum; // find the highest number
            }
        }

        nextNum ++; // add one to get the next number

        return String.format("%04d", nextNum); // format string (padding of 0s)
    }

    /**
     * checks if a string is a valid SKU
     * @param   sku     the sku of the item to check if valid
     * @return          a boolean true of false depending on if its valid - true = valid
    */
    public static boolean validSku(String sku) {
        // checks length of string
        if (sku.length() != 8) {
            return false;
        }

        // checks where the dash is
        if (sku.indexOf("-") != 3) {
            return false;
        }

        // checks if the string after the dash is a valid int
        try {
            int number = Integer.parseInt(sku.substring(4));
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     *  Display formatted item details
     *  @param  item    the item object
    */
    public static void displayItem(Item item) {
        // display info abt the item so it looks better than just slapping the file line version on it
        System.out.println("\n========Item " + item.getSku() + "========");
        System.out.println("Name: " + item.getName());
        System.out.println("Category: " + item.getCategory());
        System.out.println("Quantity: " + item.getQuantity());
        System.out.println("Minimum quantity: " + item.getMinimumQuantity());
        System.out.println("Vendor price: " + item.getVendorPrice());
        System.out.println("Markup percetange: " + item.getMarkupPercetange());
        System.out.println("Regular price: " + item.getRegularPrice());
        System.out.println("Current discount: " + item.getCurrentDiscount());
        System.out.println("Current price: " + item.getCurrentPrice());
    }

    /**
     * helper functions to receive correct string data from user
     * @param   input       question string to ask user
     * @param   scanner     scanner to receive data from user
     * @return              the user input as a string
    */
    public static String getStringInput(String input, Scanner scanner) {
        while (true) {
            // print question and receive input
            System.out.print(input);
            String userInput = scanner.nextLine();

            // check if input is empty
            if (userInput.isEmpty()) {
                System.out.println("Input cannot be empty");
            } else {
                return userInput;
            }
        }
    }


    /**
     * helper functions to receive correct double data from user
     * @param   input       question string to ask user
     * @param   scanner     scanner to receive data from user
     * @return              the user input as a double
    */
    public static double getDoubleInput(String input, Scanner scanner) {
        while (true) {
            System.out.print(input); // print question

            // check if input is valid, if it isn't, continue looping
            if (scanner.hasNextDouble()) {
                // receive user input
                double userInput = scanner.nextDouble();
                scanner.nextLine(); // remove new lines

                // checking input
                if (userInput < 0) {
                    // input smaller than 0
                    System.out.println("Input cannot be negative");
                } else {
                    // stop looping
                    return userInput;
                }
            } else {
                // if input isn't a double
                System.out.println("Input is not a number");
                scanner.nextLine(); // remove new line
            }
        }
    }

    /**
     * helper functions to receive correct integer data from user
     * @param   input       question string to ask user
     * @param   scanner     scanner to receive data from user
     * @return              the user input as a integer
    */
    public static int getIntInput(String input, Scanner scanner) {
        while (true) {
            // get input and check if its integer
            System.out.print(input);

            // if scanner detects next int
            if (scanner.hasNextInt()) {
                // set id to the next input
                int userInput = scanner.nextInt();
                scanner.nextLine(); // remove new lines

                // checking input
                if (userInput < 0) {
                    // input smaller than 0
                    System.out.println("Input cannot be negative");
                } else {
                    // stop looping
                    return userInput;
                }
            } else {
                // if scanner can't find integer
                System.out.println("Input has to be integer");
                scanner.nextLine();
            }
        }
    }
}