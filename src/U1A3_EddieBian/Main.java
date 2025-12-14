package U1A3_EddieBian;
/** Roller Coaster Ride
Eddie
July 3, 2025
Uses data from the user to determine if user can ride on a roller coaster
*/

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // variables
        boolean start = true;

        Scanner scanner = new Scanner(System.in); // initialize scanner

        System.out.println("Roller Coaster Ride\n====================");

        // main loop
        while (start) {
            // ask for info
            System.out.print("Height in cm?: ");
            int height = scanner.nextInt();

            String backTrouble;
            while (true) {
                System.out.print("Back trouble? (Y/N): ");
                backTrouble = scanner.next().toLowerCase();
                if (backTrouble.equals("y") || backTrouble.equals("n")) {
                    break;
                } else {
                    // only allow inputs y or n
                    System.out.println("Invalid input, please enter \'y\' or \'n\'.");
                }
            }

            String heartTrouble;
            while (true) {
                System.out.print("Heart trouble? (Y/N): ");
                heartTrouble = scanner.next().toLowerCase();
                if (heartTrouble.equals("y") || heartTrouble.equals("n")) {
                    break;
                } else {
                    System.out.println("Invalid input, please enter \'y\' or \'n\'.");
                }
            }

            String neckBackIssues;
            while (true) {
                System.out.print("Neck/back issues? (Y/N): ");
                neckBackIssues = scanner.next().toLowerCase();
                if (neckBackIssues.equals("y") || neckBackIssues.equals("n")) {
                    break;
                } else {
                    System.out.println("Invalid input, please enter \'y\' or \'n\'.");
                }
            }


            // determining if user can ride
            if (height <= 188 && height >= 122 && backTrouble.equals("n") && heartTrouble.equals("n") && neckBackIssues.equals("n")) {
                System.out.println("Message: YES, it is safe for you to ride this roller coaster.");
            } else {
                System.out.println("Message: NO, it is not safe for you to ride this roller coaster.");
            }

            // continuing or not
            while (true) {
                System.out.print("Would you like to enter info for the next rider Y/N?: ");
                String response = scanner.next().toLowerCase();
                if (response.equals("n")) {
                    start = false;
                    break;
                } else if (response.equals("y")) {
                    break;
                } else {
                    System.out.println("Invalid input, please enter \'y\' or \'n\'.");
                }
            }
        }

        scanner.close();
    }
}