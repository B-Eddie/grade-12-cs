package U2A3_EddieBian;
/**
 * Tile Price Calculator Application
 * Eddie
 * July 11, 2025
 * Program that allows user to intereact with tiles and get information about all tiles
*/

import java.util.Scanner;
import java.util.ArrayList;

public class TileCalculator
{
    public static void main(String[] args)
    {
        // initial variables
        boolean running = true;
        ArrayList<Shape> shapes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); // initialize scanner

        // main menu loop
        while (running) {
            // menu items
            System.out.println("Tile Price Calculator");
            System.out.println("================");
            System.out.println("1: Create new shape (add in meters)");
            System.out.println("2: Delete shape");
            System.out.println("3: Display details");
            System.out.println("4: Calculate cost");
            System.out.println("5: Calculate area");
            System.out.println("6: Clear shapes");
            System.out.println("7: Exit");

            int option = scanner.nextInt(); // takes the user's input
            scanner.nextLine(); // removes new line
            System.out.println(); // separates text

            if (option == 1) {
                // Create a new shape
                // Enter the price per square unit - // done when creating the shape
                addShape(scanner, shapes);
            } else if (option == 2) {
                // Delete an existing shape
                deleteShape(scanner, shapes);
            } else if (option == 3) {
                // Display a list of existing shapes with details of each shape
                listShapes(shapes);
            } else if (option == 4) {
                // Calculate the total cost of the floor
                totalCost(shapes);
            } else if (option == 5) {
                // Calculate the total area of the floor
                totalArea(shapes);
            } else if (option == 6) {
                // Clear the the list of shapes
                shapes.clear();
                System.out.println("Shapes cleared");
            } else if (option == 7) {
                // stop running
                running = false;
            } else {
                // input is not 1-7
                System.out.println("Invalid option");
            }

            // ending code
            // https://www.edureka.co/community/4668/how-to-clear-the-console-in-java
            // wait 2 seconds - https://stackoverflow.com/questions/11785200/how-can-i-get-the-current-milliseconds-from-the-current-time
            long pastTime = System.currentTimeMillis(); // time when starting to loop

            // keeps looping until the difference in time is more than 4s
            while (System.currentTimeMillis() - pastTime < 4000) {}
            System.out.print("\033[H\033[2J"); // moves mouse to top left
            System.out.flush(); // clears screen
        }

        scanner.close(); // closes the scanner
    }

    // MENU OPTIONS
    /**
     * adds shape to the arraylist
     * @param   scanner     scanner to receive data from user
     * @param   shapes      arraylist to store object in
    */
    public static void addShape(Scanner scanner, ArrayList<Shape> shapes) {
        // list available shapes
        System.out.println("Available Shapes:");
        System.out.println("1: Circle\n2: Diamond\n3: Donut\n4: Parallelogram\n5: Rectangle\n6: Trapezoid\n7: Triangle");
        int choice = getIntInput("Pick a shape (1-7): ", scanner);

        // initially gets unitprice from user
        double unitPrice = getDoubleInput("Enter unit price: $", scanner);
        switch (choice) {
            case 1:
                // circle
                // get variables from user
                double radius = getDoubleInput("Enter radius: ", scanner);

                // add specific shape object to arraylist
                shapes.add(new Circle(unitPrice, radius));

                // sucess message
                System.out.println("Shape added successfully");
                break;
            case 2:
                // diamond
                // get variables from user
                double diagonalOne = getDoubleInput("Enter first diagonal: ", scanner);
                double diagonalTwo = getDoubleInput("Enter second diagonal: ", scanner);

                // add specific shape object to arraylist
                shapes.add(new Diamond(unitPrice, diagonalOne, diagonalTwo));

                // sucess message
                System.out.println("Shape added successfully");
                break;
            case 3:
                // donut
                // get variables from user
                double outerRadius = getDoubleInput("Enter outer radius: ", scanner);
                double innerRadius = getDoubleInput("Enter inner radius: ", scanner);

                // add specific shape object to arraylist
                shapes.add(new Donut(unitPrice, outerRadius, innerRadius));

                // sucess message
                System.out.println("Shape added successfully");
                break;
            case 4:
                // parrallelogram
                // get variables from user
                double widthParallelogram = getDoubleInput("Enter width: ", scanner);
                double heightParallelogram = getDoubleInput("Enter height: ", scanner);

                // add specific shape object to arraylist
                shapes.add(new Parallelogram(unitPrice, widthParallelogram, heightParallelogram));

                // sucess message
                System.out.println("Shape added successfully");
                break;
            case 5:
                // rectangle
                // get variables from user
                double widthRectangle = getDoubleInput("Enter width: ", scanner);
                double heightRectangle = getDoubleInput("Enter height: ", scanner);

                // add specific shape object to arraylist
                shapes.add(new Rectangle(unitPrice, widthRectangle, heightRectangle));

                // sucess message
                System.out.println("Shape added successfully");
                break;
            case 6:
                // trapezoid
                // get variables from user
                double baseOne = getDoubleInput("Enter base one: ", scanner);
                double baseTwo = getDoubleInput("Enter base two: ", scanner);
                double heightTrapezoid = getDoubleInput("Enter height: ", scanner);

                // add specific shape object to arraylist
                shapes.add(new Trapezoid(unitPrice, baseOne, baseTwo, heightTrapezoid));

                // sucess message
                System.out.println("Shape added successfully");
                break;
            case 7:
                // triangle
                // get variables from user
                double widthTriangle = getDoubleInput("Enter width: ", scanner);
                double heightTriangle = getDoubleInput("Enter height: ", scanner);

                // add specific shape object to arraylist
                shapes.add(new Triangle(unitPrice, widthTriangle, heightTriangle));

                // sucess message
                System.out.println("Shape added successfully");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    /**
     * Deletes shape from the arraylist
     * @param   scanner     for user inputs
     * @param   shapes      the arraylist to interact with
    */
    public static void deleteShape(Scanner scanner, ArrayList<Shape> shapes) {
        // check if arraylist is empty
        if (shapes.isEmpty()) {
            System.out.println("No shapes added");
        } else {
            // display all shapes and indexes
            System.out.println("Choose an index to remove");
            listShapes(shapes); // display all the shapes that are in the arraylist

            int choice = getIntInput("Enter choice: ", scanner); // get choice from user
            // check if choice is valid
            if (choice >= 0 && choice < shapes.size()) {
                shapes.remove(choice); // remove index from arraylist
                System.out.println("Shape " + choice + " removed."); // show output
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    /**
     * List information about the shapes
     * @param   shapes      arraylist to get information from
    */
    public static void listShapes(ArrayList<Shape> shapes) {
        // check if arraylist is empty
        if (shapes.isEmpty()) {
            System.out.println("No shapes added");
        } else {
            // if arraylist isn't empty
            System.out.println("======Shapes======");

            // loop through length of arraylist
            for (int index = 0; index < shapes.size(); index++) {
                // print info about each object (shapes.get calls the toString())
                System.out.println("Index: " + index + ":\n" + shapes.get(index) + "\n");
            }
        }
    }

    /**
     * gets the total cost of all the shapes added
     * @param   shapes      arraylist to get information from
    */
    public static void totalCost(ArrayList<Shape> shapes) {
        double totalShapeCost = 0.0; // variable to add cost of each shape

        // loop through length of arraylist
        for (int index = 0; index < shapes.size(); index++) {
            // add area times unitprice to totalshapecost
            totalShapeCost += shapes.get(index).getArea() * shapes.get(index).getUnitPrice();
        }

        System.out.println("The total cost is $" + totalShapeCost); // output
    }

    /**
     * gets the total area of all the shapes added
     * @param   shapes      arraylist to get information from
    */
    public static void totalArea(ArrayList<Shape> shapes) {
        double totalArea = 0.0; // variable to add area of each shape

        // loop through length of arraylist
        for (int index = 0; index < shapes.size(); index++) {
            // add area to totalshapecost
            totalArea += shapes.get(index).getArea();
        }

        System.out.println("The total area is " + totalArea); // output
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
            System.out.println(input);
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