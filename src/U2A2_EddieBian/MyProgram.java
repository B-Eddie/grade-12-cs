package U2A2_EddieBian;
/**
 * ArrayLists - Employee Records Application
 * Eddie
 * July 7, 2025
 * Record program that allows user to add, remove, list records
 * NOTE: used https://www.geeksforgeeks.org/java/character-isdigit-method-in-java-with-examples/ for detecting if string is numeric
*/

import java.util.Scanner;
import java.util.ArrayList;

public class MyProgram
{
    public static void main(String[] args)
    {
        // initial variables
        boolean running = true;
        ArrayList <Employee> employees = new ArrayList <Employee> ();
        Scanner scanner = new Scanner(System.in); // initialize scanner

        // main menu loop
        while (running) {
            // menu items
            System.out.println("Employee Records");
            System.out.println("================");
            System.out.println("a) Add");
            System.out.println("b) Remove");
            System.out.println("c) List");
            System.out.println("d) Exit");

            String option = scanner.nextLine().trim().toLowerCase(); // takes the user's input and cleans it

            if (option.equals("a")) {
                // variables for adding record
                boolean validDate = false;
                boolean validId = false;
                boolean validFirstName = false;
                boolean validLastName = false;
                boolean validSalary = false;
                double salary = 0;
                String firstName = "";
                String lastName = "";
                int id = 0;
                String date = "";

                // Add record
                // gets id from user
                while (!validId) {
                    // get input and check if its integer
                    System.out.print("Enter employee ID: ");

                    // if scanner detects next int
                    if (scanner.hasNextInt()) {
                        // set id to the next input
                        id = scanner.nextInt();
                        scanner.nextLine(); // remove new lines
                    } else {
                        // if scanner can't find integer
                        System.out.println("ID has to be integer");
                        scanner.nextLine();
                        continue;
                    }

                    boolean idExists = false; // boolean to determine whether or not to keep looping

                    // loop through arraylist
                    for (int employee = 0; employee < employees.size(); employee++) {
                        if (id == employees.get(employee).getEmployeeIdNumber()) {
                            // get employee id number and compare with id
                            idExists = true;
                            System.out.println("Employee ID taken, choose another one");
                            break;
                        }
                    }

                    // if id doesn't exists, stop looping, otherwise keep looping
                    if (!idExists) {
                        validId = true;
                    }
                }

                // gets first name from user
                while (!validFirstName) {
                    System.out.print("Enter employee first name: ");
                    firstName = scanner.nextLine(); // gets users first name

                    // check if input is empty, if it is, continue looping
                    if (firstName.isEmpty()) {
                        System.out.println("Input cannot be empty");
                    } else {
                        // stop looping
                        validFirstName = true;
                    }
                }

                // gets last name from user
                while (!validLastName) {
                    System.out.print("Enter employee last name: ");
                    lastName = scanner.nextLine(); // takes in user input as first name

                    // check if input is empty, if it is, continue looping
                    if (lastName.isEmpty()) {
                        System.out.println("Input cannot be empty");
                    } else {
                        // stop looping
                        validLastName = true;
                    }
                }

                // gets annual salary from user
                while (!validSalary) {
                    System.out.print("Enter employee annual salary (only number, don't add $): ");

                    // check if input is valid, if it isn't, continue looping
                    if (scanner.hasNextDouble()) {
                        // set salary to next double
                        salary = scanner.nextDouble();
                        scanner.nextLine(); // remove new lines
                        if (salary < 0) {
                            // salary smaller than 0
                            System.out.println("Salary cannot be negative");
                        } else {
                            // stop looping
                            validSalary = true;
                        }
                    } else {
                        // if input isn't a double
                        System.out.println("Input is not a number");
                        scanner.nextLine(); // remove new line
                    }
                }

                // gets startDate from user
                while (!validDate) {
                    // receive user input
                    System.out.print("Enter employee start date (dd/mm/yyyy): ");
                    String startDate = scanner.nextLine(); // set new line to start date

                    // split into dd mm yy
                    String[] separatedDate = startDate.split("/");

                    // if doesn't match dd mm yy - doesn't have 3 separate parts
                    if (separatedDate.length != 3) {
                        System.out.println("Invalid format, use dd/mm/yyyy");
                        continue;
                    }

                    // if all of them are integers
                    if (isDigit(separatedDate[0]) && isDigit(separatedDate[1]) && isDigit(separatedDate[2])) {
                        // separate into variables
                        int day = Integer.parseInt(separatedDate[0]);
                        int month = Integer.parseInt(separatedDate[1]);
                        int year = Integer.parseInt(separatedDate[2]);

                        // check if the variables are in range
                        if (day > 0 && day <= 31 && month > 0 && month <= 12 && year > 1000 && year < 9999) {
                            // set date variable to correct date and stop looping
                            date = startDate;
                            validDate = true;
                        } else {
                            // when out of range
                            System.out.println("Date out of range");
                        }
                    } else {
                        // all parts aren't digits
                        System.out.println("Invalid format, has to be numbers");
                    }
                }

                employees.add(new Employee(id, firstName, lastName, salary, date)); // add new employee to arraylist
                System.out.println("Employee added successfully");
            } else if (option.equals("b")) {
                // remove record

                // variables
                boolean idFound = false;

                System.out.print("Enter ID to remove: ");
                if (scanner.hasNextInt()) {
                    int id = scanner.nextInt(); // receive user input as id to remove
                    scanner.nextLine(); // remove new line

                    // loop through employees
                    for (int employee = 0; employee < employees.size(); employee++) {
                        if (employees.get(employee).getEmployeeIdNumber() == id) {
                            // if employee found, remove it from the arraylist
                            employees.remove(employee);
                            idFound = true;
                            System.out.println("Employee removed");
                            break;
                        }
                    }
                } else {
                    // scanner didn't find integer
                    System.out.println("Invalid input - not a number");
                }

                // id not found
                if (!idFound) {
                    System.out.println("Employee not found");
                }
            } else if (option.equals("c")) {
                // list employees
                if (employees.size() == 0) {
                    // if the employee array list is empty
                    System.out.println("No employees found");
                } else {
                    // when employees are available
                    System.out.println("==== Employees ====");

                    // loop through employees and printing them
                    for (int index = 0; index < employees.size(); index++) {
                        System.out.println(employees.get(index) + "\n");
                    }
                }
            } else if (option.equals("d")) {
                // stop running
                running = false;
            } else {
                // input is not a b c
                System.out.println("Invalid option");
            }

            System.out.println("\n"); // separate the inputs
        }

        scanner.close(); // closes the scanner
    }

    /**
     * tool to determine whether or not a string can be turned into integer
     * @param   s   input string to check if it can be turned into integer
     * @return      returns false if cannot be turned into integer, true otherwise
    */
    public static boolean isDigit(String s) {
        // loop through string
        for (int charIndex = 0; charIndex < s.length(); charIndex++) {
            // if the character isn't a digit, return false
            if (!Character.isDigit(s.charAt(charIndex))) {
                return false;
            }
        }
        return true; // if all of the characters were digits
    }
}