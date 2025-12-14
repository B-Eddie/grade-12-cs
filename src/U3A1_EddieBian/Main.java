package U3A1_EddieBian;
/**
 * Modified Fibonacci
 * Eddie
 * July 15, 2025
 * Generates the term value of a modified Fibonacci sequence
 * The modified sequence is using the last 3 nums rather than the last 2. It starts with 3, 5, 8
*/

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int userInput; // the term number

        // keeps on receiving input and check if its integer until they enter -1 (to exit)
        while (true) {
            System.out.print("Enter the term number which you would like to know (enter -1 to exit): ");

            // if scanner detects an int
            if (scanner.hasNextInt()) {
                // set id to the next input
                userInput = scanner.nextInt();
                scanner.nextLine(); // remove new lines

                // checking input range
                if (userInput < -1) {
                    // input smaller than 0
                    System.out.println("Input cannot be negative");
                } else if (userInput == -1) {
                    break; // stop looping
                } else {
                    // display what the term value is (input is correct)
                    System.out.println("Term #" + userInput + " is " + modifiedFibonacci(userInput));
                }
            } else {
                // if scanner can't find integer
                System.out.println("Input has to be integer");
                scanner.nextLine(); // remove invalid input
            }
        }

    }

    /**
     * Finds the term value of the modified Fibonacci sequence given a term number (recursion)
     * It breaks the number into smaller problems to solve
     * base case: t0=3, t1=5, t2=8, anything larger is calculated with the sum of previous 3 terms
     * It will keep on calling itself until it gets to the base cases, where it will then return the answer
     * @param   term    the term number to find the value (starting from 0)
     * @return          the term value of the modified sequence as a integer
    */

    public static int modifiedFibonacci(int term) {
        // 3, 5, 8, 16, 29, 53, 98   -    sequence (formula is adds the last 3 numbers to get the next)

        // base cases - sequence starts from 0
        if (term == 0) {
            return 3;
        } else if (term == 1) {
            return 5;
        } else if (term == 2) {
            return 8;

        } else {
            // return the sum of the last 3 terms
            return modifiedFibonacci(term-1) + modifiedFibonacci(term-2) + modifiedFibonacci(term-3);
        }
    }
}