package U3A3_EddieBian;
/**
 * Book title search
 * Eddie
 * July 17, 2025
 * Reads from BookList.txt file and uses binary + linear search to find the book title from user input
*/

import java.util.Scanner;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        // initial variables
        boolean running = true;
        Scanner scanner = new Scanner(System.in); // initialize scanner
        String[][] items = new String[22][2]; // items array to store book info
        BufferedReader br = null; // initialize bufferedreader

        try {
            br = new BufferedReader(new FileReader("BookList.txt"));
            String line; // used to check if there are any more lines
            int index = 0; // counter

            // checks if its less than 44 because only 44 lines in the file
            // its grouped into lines of 2 (first=book num, second=book title)
            while ((line = br.readLine()) != null && index < 44){
                items[index][0] = line; // book num
                items[index][1] = br.readLine(); // book name

                index++; // increment counter
            }
        } catch (IOException e) {
            // error
            e.printStackTrace();
        } finally {
            try {
                br.close(); // close reader
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        // main menu loop
        while (running) {
            // menu items
            System.out.println("Tile Price Calculator");
            System.out.println("================");
            System.out.println("1: Linear search");
            System.out.println("2: Binary search");
            System.out.println("3: Exit");

            // takes the user's input
            int option = getIntInput("Your choice: ", scanner);

            // checks if user option is in range
            if (option > 0 && option <= 3) {
                // checks if user chose exit option
                if (option == 3) {
                    running = false;
                } else {
                    // get the target value -> string (used for comparing with book numbers (str))
                    String target = String.valueOf(getIntInput("Target value: ", scanner));

                    // other menu options
                    if (option == 1) {
                        // linear search
                        System.out.println(linearSearch(items, target));
                    } else if (option == 2) {
                        // uses binary search
                        System.out.println(binarySearch(items, target));
                    }

                    // code to continue when chosen
                    System.out.print("\nPress enter to continue: ");
                    if (scanner.hasNextLine()) {
                        System.out.print("\033[H\033[2J"); // moves mouse to top left
                        System.out.flush(); // clears screen
                    }
                }
            } else {
                // input is not 1-7
                System.out.println("Invalid option");
            }

        }

        scanner.close(); // closes the scanner
    }

    // MENU OPTIONS
    /**
     * Finds the target book number using linear search
     * Precondition: the list must be sorted (added part to try to make more efficient)
     * @param   list    the array to find the book in
     * @param   target  target number value of the book
     * @return          if its found, return the book details, if not, return not found message
    */
    public static String linearSearch(String[][] list, String target) {
        // loop through array
        for (int i = 0; i < list.length; i++) {
            // check if book number (index[i][0]) equals the target
            if (list[i][0].equals(target)) {
                return list[i][1]; // if it matches, return the book name
            }

            // since its sorted, check if the book num is bigger than target to stop looping early
            if (Integer.valueOf(list[i][0]) > Integer.valueOf(target)) {
                return "Not found";
            }
        }

        return "Not found"; // book number not found in array
    }

    /**
     * Finds the target book number using binary search
     * overload method so only needs to have two arguments when calling the method
     * precondition: array must be sorted in order
     * @param   list    the array to find the book in
     * @param   target  target number value of the book
     * @return          either the book details or not found message
    */
    public static String binarySearch(String[][] list, String target) {
        // minus one from list length since the range is 0-21 not 1-22
        return binarySearch(list, target, 0, list.length - 1);
    }

    /**
     * Binary search method (overloaded method) that uses recursion
     * @param   list    the array to find the book in
     * @param   target  target book number to find
     * @param   left    left pointer
     * @param   right   right pointer
     * @return          If its found in array, display the book title, otherwise display not found
    */
    public static String binarySearch(String[][] list, String target, int left, int right) {
        // base condition -> when left pointer is on the right instead the left
        if (left > right) {
            return "Not found";
        }

        int middle = (left + right) / 2; // get middle index

        // checks if the book number matches the target
        if (list[middle][0].equals(target)) {
            return list[middle][1]; // target found
        }

        // checking which side it should remove
        // target is a string, so need to turn to int to compare with the book value
        if (Integer.valueOf(target) < Integer.valueOf(list[middle][0])) {
            // go to left side
            return binarySearch(list, target, left, middle - 1);
        } else {
            // go to right side
            return binarySearch(list, target, middle + 1, right);
        }
    }

    /**
     * helper functions to receive correct integer data from user
     * @param   input       question string to ask user
     * @param   scanner     scanner to receive data from user
     * @return              the user input as an integer
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