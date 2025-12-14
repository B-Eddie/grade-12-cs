package U1A2_EddieBian;
/** Coin Calculator
Eddie
July 2, 2025
Turns a price into the number of coins needed to make up that price
*/


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Change Exchange");
            System.out.print("Enter the amount in dollars and cents (e.g., xxxx.xx): ");
            double amount = scanner.nextDouble();
            int totalCents = (int) Math.round((amount * 100)); // added round becuase it added .9999

            // start calculations
            int toonies = totalCents / 200; // 2$ -> 2 dollars is 200 cents
            totalCents %= 200; // remove the num of toonies from the total
            int loonies = totalCents / 100; // 1$ -> 1 dollar is 100 cents
            totalCents %= 100; // remove the num of loonies from the total

            int quarters = totalCents / 25;
            totalCents %= 25;
            int dimes = totalCents / 10;
            totalCents %= 10;
            int nickels = totalCents / 5;
            totalCents %= 5;
            int pennies = totalCents % 5;

            System.out.println("Amount: $" + amount);
            System.out.println("Toonies: " + toonies);
            System.out.println("Loonies: " + loonies);
            System.out.println("Quarters: " + quarters);
            System.out.println("Dimes: " + dimes);
            System.out.println("Nickels: " + nickels);
            System.out.println("Pennies: " + pennies);

            System.out.println("Do you want to calculate again? (yes/no)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("no")) {
                exit = true;
            }
        }

        scanner.close();
    }
}