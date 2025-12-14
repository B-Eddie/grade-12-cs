package U1A5b_EddieBian;
/**
 * Student Grades Program
 * Eddie
 * July 7, 2025
 * Simulates a grade book for a class with 30 students who each have four test
 * scores, allowing the user to interact with the data
 * Uses 2d array to store data as strings: // eg: ["firstName1", " lastName1", "grade1", "grade2", "grade3, "grade4"],
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean loop = true; // to loop the main menu or not
        int studentCount = 0; // number of students added to the array
        Scanner scanner = new Scanner(System.in); // initialize scanner, user input
        String[][] students = new String[30][6]; // initializes array (row=student, columns 0=firstname, 1=lastname, 2-5=grades)


        // main menu loop
        while (loop) {
            System.out.println("Choose an option:"); // title

            // options
            System.out.println("1. List grades");
            System.out.println("2. Add student");
            System.out.println("3. Get a student's average");
            System.out.println("4. Get class average");
            System.out.println("5. Exit");
            int choice = scanner.nextInt(); // choice
            scanner.nextLine(); // removes newline after nextint

            // different task for different numbers
            switch (choice) {
                case 1:
                    // list students and grades
                    String result = "=============Student Marks=============\n";

                    // edge case if there are no students in the array
                    if (studentCount == 0) {
                        System.out.println("No students added yet");
                        break;
                    }

                    // adds names, marks formatted to result
                    for (int row = 0; row < studentCount; row++) {
                        result += students[row][0] + " " + students[row][1] + ": ";
                        result += students[row][2] + "%, " + students[row][3] + "%, " + students[row][4] + "%, " + students[row][5] + "%\n";
                    }

                    // results
                    System.out.println(result);
                    System.out.println("Total # of students: " + studentCount);

                    break;
                case 2:
                    // add student data
                    if (studentCount >= 30) {
                        System.out.println("Class is at max capacity");
                        break;
                    }

                    // get student info
                    System.out.println("First name: ");
                    String firstName = scanner.nextLine().trim();
                    System.out.println("Last name: ");
                    String lastName = scanner.nextLine().trim();

                    // check if any inputs were invalid (empty)
                    if (firstName.isEmpty() || lastName.isEmpty()) {
                        System.out.println("First name or last name is empty");
                        break;
                    }

                    // check for duplicate student - checking in the 2d list if the firstname lastname combination is found
                    boolean found = false;
                    for (int row = 0; row < studentCount; row++) {
                        if (students[row][0].toLowerCase().equals(firstName.toLowerCase()) && students[row][1].toLowerCase().equals(lastName.toLowerCase())) {
                            found = true;
                            break;
                        }
                    }

                    // if the user is found in the array, meaning it can't add it again
                    if (found) {
                        System.out.println("Student already exists");
                        break;
                    }

                    // get grades from user
                    double[] grades = new double[4];
                    int grade = 0;
                    while (grade < 4) {
                        System.out.println("Enter grade " + (grade + 1) + ": ");

                        // checking if the value is a valid double
                        if (scanner.hasNextDouble()) {
                            double value = scanner.nextDouble();
                            if (value >= 0 && value <= 100) {
                                grades[grade] = value;
                                grade ++;
                            } else {
                                System.out.println("Grade has to be between 0 and 100 (inclusive)");
                            }
                        } else {
                            System.out.println("Input is not number (double)");
                            System.out.println("Try again");
                            scanner.next(); // remove invalid input
                        }
                    }

                    // add data to array
                    students[studentCount][0] = firstName;
                    students[studentCount][1] = lastName;
                    // add marks to array
                    for (int mark = 0; mark < 4; mark++) {
                        students[studentCount][mark + 2] = Double.toString(grades[mark]);
                    }
                    studentCount++; // one more student added
                    System.out.println("Student added");

                    break;
                case 3:
                    // view student average
                    // get student info
                    System.out.println("First name: ");
                    String firstNameLookup = scanner.nextLine().trim();
                    System.out.println("Last name: ");
                    String lastNameLookup = scanner.nextLine().trim();

                    // used for logging at the end if not found
                    boolean avgFound = false;

                    for (int row = 0; row < studentCount; row++) {
                        if (students[row][0].toLowerCase().equals(firstNameLookup.toLowerCase()) && students[row][1].toLowerCase().equals(lastNameLookup.toLowerCase())) {
                            // calculate average
                            double total = 0;
                            for (int col = 0; col < 4; col ++) {
                                // converts to double for avg calculation
                                total += Double.parseDouble(students[row][col + 2]);
                            }
                            double average = total / 4.0; // average of student
                            System.out.println(students[row][0] + " " + students[row][1] + "'s average is " + average + "%");
                            avgFound = true;
                            break;
                        }
                    }

                    // if user not found in array
                    if (!avgFound) {
                        System.out.println("Student not found");
                    }

                    break;
                case 4:
                    // view class average
                    if (studentCount == 0) {
                        System.out.println("No students entered yet");
                        break;
                    }

                    // loop through students, get the students average, add that average to total average variable, then calculate average of the total average variable
                    double totalMarks = 0;
                    for (int row = 0; row < studentCount; row++) {
                        double studentTotal = 0;
                        for (int col = 0; col < 4; col++) {
                            // converts to double for calcuation
                            studentTotal += Double.parseDouble(students[row][col + 2]); // converts it from string to double
                        }
                        totalMarks += studentTotal / 4.0; // adds students average to total average
                    }

                    double courseAvg = totalMarks / studentCount; // total average of all students divided by how many students there are
                    System.out.println("Course average is " + courseAvg + "%");

                    break;
                case 5:
                    // exit - change the while loop condition to false
                    loop = false;
                    break;
                default:
                    // invalid menu choice
                    System.out.println("Invalid input");

            }
            System.out.println("\n\n");
        }
        scanner.close(); // closes scanner
    }
}