package U1A5a_EddieBian;
/**
 * Array Manipulator
 * Eddie
 * July 5, 2025
 * Stores and manipulates a 1d array of 20 integers
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean loop = true; // to repeat the main loop or not
        int[] numbers = new int[20]; // initializes array
        int totalInts = 0; // number of integers added to the array
        Scanner scanner = new Scanner(System.in); // initialize scanner, used to take input from user

        // main menu loop
        while (loop) {
            System.out.println("Choose an option:"); // title

            // options
            System.out.println("1. Add a number");
            System.out.println("2. Remove number");
            System.out.println("3. List all numbers and sum");
            System.out.println("4. List all even numbers and sum");
            System.out.println("5. List all odd numbers and sum");
            System.out.println("6. Exit");
            int choice = scanner.nextInt(); // choice

            // different task for different numbers
            switch (choice) {
                case 1:
                    // add number
                    if (totalInts >= 20) {
                        System.out.println("Array is full, cannot add more numbers");
                        break;
                    }

                    // receive number from user
                    System.out.print("Enter a positive integer to add: ");
                    int toAdd = scanner.nextInt();

                    // check if number is right
                    if (toAdd > 0) {
                        numbers[totalInts] = toAdd;
                        totalInts ++;
                        System.out.println("Number added");
                    } else {
                        System.out.println("Only positive numbers allowed");
                    }

                    break;
                case 2:
                    // remove a number from the array
                    // removes the first occurance of number
                    System.out.print("Enter number to remove: ");
                    int toRemove = scanner.nextInt();
                    boolean successful = false; // to see if the number exists in the array

                    for (int i = 0; i < totalInts; i++) {
                        if (numbers[i] == toRemove) {
                            numbers[i] = 0; // this is "removing" the number from the array
                            successful = true; // when found the number
                            System.out.println("Successful. Removed index " + i);
                            break;
                        }
                    }

                    if (!successful) {
                        System.out.println("Number not found");
                    }

                    break;
                case 3:
                    // list all numbers and total sum
                    int totalSum = 0;

                    // loop through array, adding to total sum and displaying integer
                    System.out.print("All numbers: ");
                    for (int i = 0; i < totalInts; i++) {
                        if (numbers[i] != 0) {
                            System.out.print(numbers[i] + " ");
                            totalSum += numbers[i];
                        }
                    }
                    System.out.println("\nSum of all numbers: " + totalSum); // prints out the totalsum of the array
                    break;
                case 4:
                    // list all even numbers and sum
                    int evenSum = 0;

                    // loop through array, checking if its divisible by 2 and adding to total even sum and displaying integer
                    System.out.print("Even numbers: ");
                    for (int i = 0; i < totalInts; i++) {
                        if (numbers[i] % 2 == 0 && numbers[i] != 0) {
                            System.out.print(numbers[i] + " ");
                            evenSum += numbers[i];
                        }
                    }
                    System.out.println("\nSum of all even numbers: " + evenSum); // prints out the totalsum of the array
                    break;
                case 5:
                    // list all odd numbers and sum
                    int oddSum = 0;

                    // loop through array, checking if its not divisible by 2 and adding to total odd sum and displaying integer
                    System.out.print("Odd numbers: ");
                    for (int i = 0; i < totalInts; i++) {
                        if (numbers[i] % 2 != 0 && numbers[i] != 0) {
                            System.out.print(numbers[i] + " ");
                            oddSum += numbers[i];
                        }
                    }
                    System.out.println("\nSum of all odd numbers: " + oddSum); // prints out the totalsum of the array
                    break;
                case 6:
                    // exit - change the while loop condition to false
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid input");

            }
            System.out.println("\n\n");
        }
        scanner.close();
    }
}