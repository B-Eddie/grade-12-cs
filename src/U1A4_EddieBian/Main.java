package U1A4_EddieBian;
/**
 * Custom method program
 * Eddie
 * July 4, 2025
 * A multi functional program that uses custom methods (some using built in java methods-Math) to accomplish a variety of tasks, including string reversal, prime number checking, calculating area and volume, generating pyramid patterns, and dice rolling.
*/

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        boolean loop = true; // to repeat the main loop or not
        Scanner scanner = new Scanner(System.in); // initialize scanner, used to take input from user

        // loop so that user can choose multiple options in 1 run - main menu loop
        while (loop) {
            System.out.println("Choose an option (1-6):"); // title

            // options
            System.out.println("1. Reverse a string");
            System.out.println("2. Check if number is prime");
            System.out.println("3. Calculate area");
            System.out.println("4. Calculate volume");
            System.out.println("5. Roll a dice");
            System.out.println("6. Create a pyramid pattern");
            System.out.println("7. Exit");
            int choice = scanner.nextInt(); // choice

            // different task for different numbers
            switch (choice) {
                case 1:
                    // reversing string
                    // getting user input
                    scanner.nextLine(); // removes the newline
                    System.out.println("Enter string to reverse: ");
                    String inputString = scanner.nextLine();

                    String reversed = reverseString(inputString); // pass input to method
                    System.out.println("Reversed string: " + reversed); // print result
                    break;
                case 2:
                    // checking if number is prime
                    // user input
                    System.out.println("Enter number to check if it's prime: ");
                    int num = scanner.nextInt(); // takes in the next int and passes it into the ifprime method that returns either true of false depending on if the number is prime

                    if (isPrime(num)) {
                        // if method returns true
                        System.out.println(num + " is prime.");
                    } else {
                        // if method returns false
                        System.out.println(num + " is not prime.");
                    }
                    break;
                case 3:
                    // gets area of 2d shape
                    // user inputs (shape, base, height)
                    System.out.println("Enter type of shape (1-2):\n1. Triangle\n2. Square/rectangle");
                    int twoDShape = scanner.nextInt(); // gets the type of shape (1 for treiangle, two for square/rectangle)
                    System.out.println("Enter base: ");
                    int twoDBase = scanner.nextInt(); // gets the variable base from the user input
                    System.out.println("Enter height: ");
                    int twoDHeight = scanner.nextInt(); // gets the height value from the user input

                    // print result
                    System.out.println("Area: " + calculateArea(twoDBase, twoDHeight, twoDShape));
                    break;
                case 4:
                    // calculate volume of 3d shape
                    // user inputs (shape, base, height) based on given options
                    System.out.println("Enter type of shape (1-4):");
                    System.out.println("1. Sphere");
                    System.out.println("2. Cube");
                    System.out.println("3. Cylinder");
                    System.out.println("4. Cone");
                    int shape = scanner.nextInt(); // get user input

                    // additional inputs for different shapes
                    if (shape == 1) {
                        // if the shape is a sphere
                        System.out.println("Enter radius:");
                        int radius = scanner.nextInt(); // get additional radius value from user

                        // output
                        System.out.println("Sphere volume: " + calculateVolume(radius, 1));
                    } else if (shape == 2) {
                        // cube
                        System.out.println("Enter side length of cube:");
                        int cubeSide = scanner.nextInt(); // gets side length value from user

                        // output
                        System.out.println("Cube volume: " + calculateVolume(cubeSide, 2));
                    } else if (shape == 3) {
                        // cylinder
                        System.out.println("Enter radius of cylinder:");
                        int radius = scanner.nextInt(); // gets radius value from user
                        System.out.println("Enter height of cylinder:");
                        int height = scanner.nextInt(); // gets height from user

                        // output
                        System.out.println("Cylinder volume: " + calculateVolume(radius, height, 3));
                    } else if (shape == 4) {
                        // cone
                        System.out.println("Enter radius of cone:");
                        int radius = scanner.nextInt(); // gets radius value from user
                        System.out.println("Enter height of cone:");
                        int height = scanner.nextInt(); // gets height value from user

                        // output
                        System.out.println("Cone volume: " + calculateVolume(radius, height, 4));
                    }
                    break;
                case 5:
                    // roll a die with x amount of sides
                    System.out.println("Enter number of sides of die: ");
                    int sides = scanner.nextInt(); // gets the number of sides on the die
                    System.out.println("Enter number of dice to roll: ");
                    int numDice = scanner.nextInt(); // gets the number of dices to role

                    // output
                    System.out.println("Dice roll result: " + rollDice(sides, numDice));
                    break;
                case 6:
                    // draw a pyramid with x height
                    System.out.println("Enter height: ");
                    int height = scanner.nextInt(); // gets number of rows from user

                    printPyramid(height); // calls the method to print the pyramid
                    break;
                case 7:
                    // exit by setting loop condition to false to end the loop
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            System.out.println("\n\n"); // make it look better - space between instructions
        }
        scanner.close();
    }

    // custom methods

    /**
     * Calculates the volume of shapes, returns the volume as a double
     * @param   value   either the radius or height of the shape
     * @param   shape   the type of shape it is
     * @return          returns the volume of the shape depending on what type of shape it is
    */
    public static double calculateVolume(int value, int shape) {
        // first checks what shape the user wants, then calculates using the formula depending on what shape it is

        if (shape == 1) {
            // sphere formula
            return (4.0 / 3.0) * Math.PI * Math.pow(value, 3);
        } else {
            // cube formula
            return Math.pow(value, 3);
        }
    }

    /**
     * Calculates the volume of shapes, returns the volume as a double (overloaded method since other shapes need more variables to calculate volume)
     * @param   radius  the radius of the shape
     * @param   height  the height of the shape
     * @param   shape   the type of shape it is
     * @return          returns the volume of the shape depending on what type of shape it is
    */
    public static double calculateVolume(int radius, int height, int shape) {
        // first checks what shape the user wants, then calculates using the formula depending on what shape it is

        if (shape == 3) {
            // cylinder formula
            return Math.PI * Math.pow(radius, 2) * height;
        } else {
            // cone formula
            return Math.PI * Math.pow(radius, 2) * (height/3.0);
        }
    }

    /**
     * Calculates the area of 2d shapes
     * @param   base    the size of the base of the shape
     * @param   height  the height of the shape
     * @param   shape   the type of shape
     * @return          the area of the shape
    */
    public static double calculateArea(int base, int height, int shape) {
        if (shape == 1) {
            // triangle formula
            return base * height / 2;
        } else {
            // square or rectangle (they're the same) formula
            return base * height;
        }
    }

    /**
     * Reverses the inputted string, returns the reversed string
     * @param   str     the inputted string
     * @return          returns a reversed string
    */
    public static String reverseString(String str) {
        String reversedString = ""; // initializes a string to add characters to

        // loops through the sizelength of the string, from highest to 0
        for (int i = str.length()-1; i >= 0; i--) {
            reversedString += str.charAt(i); // add the character to the reversedString variable
        }

        return reversedString;
    }


    /**
     * Checks if a number is prime
     * @param   num     the number that is checked
     * @return          returns true (if prime) or false (not prime)
    */
    public static boolean isPrime(int num) {
        // edge cases
        if (num < 2) {
            return false;
        }

        // loops through all the numbers up to the inputted number to check if the number is divisible by it
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // when its divisible by the number
            }
        }
        return true; // when it didn't find any numbers that can be divided with the user num
    }

    /**
     * Prints a pyramid of stars based height
     * @param   height  how many rows there are
    */
    public static void printPyramid(int height) {
        // loops through the number of rows
        for (int i = 1; i <= height; i++) {
            // prints left spaces
            for (int j = 0; j < height - i; j++) {
                System.out.print(" ");
            }
            // print the asteriks
            for (int k = 0; k < (2*i - 1); k++) {
                System.out.print("*");
            }

            System.out.println(); // new line to separate rows
        }
    }

    /**
     * Rolls a dice with x amount of sides
     * @param   sides   the number of sides on the die
    */
    public static String rollDice(int sides, int num) {
        String results = ""; // initialize empty string to be filled with random nums

        // loop through num (how many dice the user wants) and add the random numbers to the results
        for (int i = 0; i < num; i++) {
            int roll = (int)(Math.random() * sides + 1); // generate random roll between 1 and the # of sides
            results += roll;

            // only add comma if its not the last num
            if (i < num - 1) {
                results += ", ";
            }
        }

        return results; // return the created results string
    }
}