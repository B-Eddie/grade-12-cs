package U2A1_EddieBian;
/**
 * OOP - Vehicle
 * Eddie
 * July 7, 2025
 * Main class for testing all methods of the vehicle class
 * Prompts the user for input, creates 3 vehicles classes, displays results
*/

import java.util.Scanner;

public class MyProgram
{
    public static void main(String[] args)
    {
        // initial variables
        int distance = 0; // trip distance
        double gasPrice = 0;

        Scanner scanner = new Scanner(System.in); // initialize scanner

        // gets gas price from user and sets it
        while (true) {
            System.out.print("Gas price (per liter): ");

            // if input is double
            if (scanner.hasNextDouble()) {
                gasPrice = scanner.nextDouble();
                // if input is positive
                if (gasPrice >= 0) {
                    break;
                } else {
                    System.out.println("Invalid input, enter a positive number");
                }
            } else {
                // not a double
                System.out.println("Invalid input, enter a number");
                scanner.next(); // remove invalid input
            }
        }

        // gets travelled distance from user and stores it
        while (true) {
            System.out.print("Trip distance (km): ");

            // if input is int
            if (scanner.hasNextInt()) {
                distance = scanner.nextInt();
                // if input is positive
                if (distance >= 0) {
                    break;
                } else {
                    System.out.println("Invalid input, enter a positive number");
                }
            } else {
                // not a int
                System.out.println("Invalid input, enter a number");
                scanner.next(); // remove invalid input
            }
        }

        Vehicle.setGasPrice(gasPrice); // sets the gas price

        // Input data for 3 vehicles
        Vehicle[] vehicles = new Vehicle[3];

        // loop 3 times to add vehicle to the array
        for (int i = 0; i < 3; i++) {
            // variables
            int passengers = 0;
            double fare = 0;
            double efficiency = 0;

            System.out.println("\nVehicle " + (i + 1) + ":");

            // get passengers
            while (true) {
                System.out.print("Enter number of passengers: ");
                // if input is int
                if (scanner.hasNextInt()) {
                    passengers = scanner.nextInt();
                    // if input is positive
                    if (passengers >= 0) {
                        break;
                    } else {
                        System.out.println("Invalid input, enter a positive number");
                    }
                } else {
                    // not a integer
                    System.out.println("Invalid input, enter a number");
                    scanner.next(); // remove invalid input
                }
            }

            // get fare
            while (true) {
                System.out.print("Enter passenger fare: ");
                // if input is double
                if (scanner.hasNextDouble()) {
                    fare = scanner.nextDouble();
                    // if input is positive
                    if (fare >= 0) {
                        break;
                    } else {
                        System.out.println("Invalid input, enter a positive number");
                    }
                } else {
                    // not a double
                    System.out.println("Invalid input, enter a number");
                    scanner.next(); // remove invalid input
                }
            }

            // get fuel efficiency
            while (true) {
                System.out.print("Enter fuel efficiency (L/km): ");
                // if input is double
                if (scanner.hasNextDouble()) {
                    efficiency = scanner.nextDouble();
                    // if input is positive
                    if (efficiency >= 0) {
                        break;
                    } else {
                        System.out.println("Invalid input, enter a positive number");
                    }
                } else {
                    // not a double
                    System.out.println("Invalid input, enter a number");
                    scanner.next(); // remove invalid input
                }
            }

            vehicles[i] = new Vehicle(passengers, fare, efficiency);
        }

        // display all vehicle data and calculations
        System.out.println("========Vehicles info========");
        for (int i = 0; i< vehicles.length; i++) {
            System.out.println("\nVehicle " + (i + 1) + " info:");
            System.out.println(vehicles[i]);
            System.out.println("Revenue: " + vehicles[i].revenue());
            System.out.println("Total Cost: " + vehicles[i].totalCost(distance));
            System.out.println("Profit: " + vehicles[i].calculateProfit(distance));
            if (vehicles[i].isProfitable(distance)) {
                System.out.println("Is profitable?: Yes");
            } else {
                System.out.println("Is profitable?: No");
            }
        }

        // compare vehicles (finds one with highest profit)
        Vehicle best = Vehicle.compareTo(vehicles[0], vehicles[1], distance);
        best = Vehicle.compareTo(best, vehicles[2], distance);
        System.out.println("\n\n========Vehicle with highest profit========\n" + best.toString());

        scanner.close(); // closes the scanner
    }
}