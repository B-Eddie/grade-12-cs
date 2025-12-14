package U3A2_EddieBian;
/**
 * Sorting algorithms
 * Eddie
 * July 16, 2025
 * Receives user input to sort an array of user-chosen size using either selection/bubble/insertion/quick sort
*/

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Select an option: ");
            System.out.println("1) Selection sort");
            System.out.println("2) Bubble sort");
            System.out.println("3) Insertion sort");
            System.out.println("4) Quick sort");
            System.out.println("5) Exit");
            int option = getIntInput("Your choice: ", scanner);

            // check if option is in range
            if (option >= 1 && option <= 5) {
                // get array of random numbers to sort from user and create an array of that length
                int[] numbers = new int[getIntInput("How many numbers to be sorted?: ", scanner)]; // how many numbers should there be
                Random random = new Random(); // get random class for adding random numbers to array

                // add random nums to array
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = random.nextInt(-1000, 1000);
                }

                // get order of the sorting
                int order = getIntInput("Ascending (1) or descending? (0)? (enter 0 or 1): ", scanner);// ascending/descending

                // check if choice is either 1/2
                while (order != 0 && order != 1) {
                    System.out.println("Invalid choice");
                    order = getIntInput("Ascending (1) or descending? (0)? (enter 0 or 1): ", scanner); // reprompt if choice is wrong
                }

                System.out.println("Unsorted list: " + Arrays.toString(numbers)); // display list before sorting
                // check which option the user chose
                if (option == 1) {
                    // selection sort
                    selectionSort(numbers, order);
                } else if (option == 2) {
                    // bubble sort
                    bubbleSort(numbers, order);
                } else if (option == 3) {
                    // insertion sort
                    insertionSort(numbers, order);
                } else if (option == 4) {
                    // quick sort
                    quickSort(numbers, order);
                } else if (option == 5) {
                    // exit
                    running = false;
                }
                System.out.println("Sorted list: " + Arrays.toString(numbers)); // display list after sorting
            } else {
                // input is not in range
                System.out.println("Invalid option");
            }

            System.out.print("\nPress enter to continue: ");
            if (scanner.hasNextLine()) {
                System.out.print("\033[H\033[2J"); // moves mouse to top left
                System.out.flush(); // clears screen
            }
        }
    }

    /**
     * Swap helper method to switch places of two indexes in an array
     * @param   numbers     the array to swap the numbers
     * @param   index       the index to swap with in the array
     * @param   index       the other index to swap with in the array
    */
    public static void swap(int[] numbers, int index, int indexToSwap) {
        int temp = numbers[index]; // create temp value

        numbers[index] = numbers[indexToSwap]; // set index to the other index's value
        numbers[indexToSwap] = temp; // set the unswapped index to the temp value
    }

    /**
     * Selection sort method. It modifies the array in place
     * @param   numbers     the array to sort
     * @param   order       lowest to greatest (when order = 1) or vice versa (0)
    */
    public static void selectionSort(int[] numbers, int order) {
        int length = numbers.length; // get length of array to loop through

        if (order == 0) {
            // descending
            // loop through array
            for (int i = 0; i < length - 1; i++) {
                int lowestNum = numbers[i]; // the current lowest number
                int positionOfMin = i; // position of lowest num

                // loop through the rest of the list
                for (int j = i + 1; j < length; j++) {
                    // check if next number is bigger than the initial lowestNum
                    if (numbers[j] > lowestNum) {
                        // found newest smallest num - change the new lowest num to the new number
                        lowestNum = numbers[j];
                        positionOfMin = j; // change the position of the smallest number
                    }
                }
                swap(numbers, i, positionOfMin); // swap the index of old lowest num with the new lowest num
            }
        } else {
            // ascending
            // loop through array
            for (int i = 0; i < length - 1; i++) {
                int biggestNum = numbers[i]; // the current biggest number
                int positionOfMax = i; // position of biggest num

                // loop through the rest of the list
                for (int j = i + 1; j < length; j++) {
                    // check if next number is smaller than the initial biggestNum
                    if (numbers[j] < biggestNum) {
                        // found newest smallest num
                        biggestNum = numbers[j];
                        positionOfMax = j;
                    }
                }
                swap(numbers, i, positionOfMax); // swap the index of old biggest num with the new biggest num
            }

        }

    }

    /**
     * Bubble sort method
     * @param   numbers     the array to sort
     * @param   order       lowest to greatest or vice versa
    */
    public static void bubbleSort(int[] numbers, int order) {
        boolean swapped = true; // variable to check if going through the array swapped the nums

        if (order == 0) {
            // descending
            while (swapped) {
                swapped = false; // to check if didn't swap numbers

                // loops through the list
                for (int i = 0; i < numbers.length - 1; i++) {
                    // compare adj numbers - if the number at index i is smaller than # after that, swap
                    if (numbers[i] < numbers[i + 1]) {
                        swapped = true; // numbers were swapped
                        swap(numbers, i, i + 1); // swap the two values
                    }
                }
            }
        } else {
            // ascending
            while (swapped) {
                swapped = false; // to check if didn't swap numbers

                // loops through the list
                for (int i = 0; i < numbers.length - 1; i++) {
                    // compare adj numbers - if the number at index i is bigger than # after that, swap
                    if (numbers[i] > numbers[i + 1]) {
                        swapped = true; // numbers were swapped
                        swap(numbers, i, i + 1); // swap the two values
                    }
                }
            }
        }
    }

    /**
     * Insertion sort method
     * @param   numbers     the array to sort
     * @param   order       lowest to greatest or vice versa
    */
    public static void insertionSort(int[] numbers, int order) {
        if (order == 0) {
            // descending
            // first index is already sorted, so start at 1
            for (int i = 1; i < numbers.length; i++) {
                int tempNum = numbers[i]; // create temp number -> numbers before it are going to take its place

                int j = i - 1;// walk backwards in array

                // while the index to check hasn't hit the start of the array + the index is smaller than tempNum (number to move)
                while (j >= 0 && numbers[j] < tempNum) {
                    numbers[j + 1] = numbers[j]; // move the number forward
                    j--; // move backwards
                }
                numbers[j + 1] = tempNum; // put the number to move in the correct place
            }
        } else {
            // ascending
            // first index is already sorted, so start at 1
            for (int i = 1; i < numbers.length; i++) {
                int tempNum = numbers[i];

                int j = i - 1;// walk backwards in array

                // while the index to check hasn't hit the start of the array + the index is smaller than tempNum (number to move)
                while (j >= 0 && numbers[j] > tempNum) {
                    numbers[j + 1] = numbers[j];
                    j--;
                }
                numbers[j + 1] = tempNum;
            }
        }
    }

    /**
     * Quick sort overloaded method
     * @param   numbers     the array to sort
     * @param   order       lowest to greatest or vice versa
    */
    public static void quickSort(int[] numbers, int order) {
        quickSort(numbers, order, 0, numbers.length - 1); // call quick sort method -> minus 1 because its starts at 0, not 1
    }

    /**
     * Recursively sorts array using quick sort
     * @param   numbers     the array to sort
     * @param   order       lowest to greatest or vice versa
     * @param   lowIndex    lowest index of array
     * @param   highIndex   highest index of array
    */
    public static void quickSort(int[] numbers, int order, int lowIndex, int highIndex) {
        // base case - when there is only one index left
        if (lowIndex >= highIndex) {
            return;
        }

        int pivotIndex = new Random().nextInt(highIndex - lowIndex) + lowIndex; // get random pivot
        int pivot = numbers[pivotIndex]; // choose pivot
        swap(numbers, pivotIndex, highIndex); // move pivot index to highest

        int leftPointer = partitian(numbers, lowIndex, highIndex, pivot, order); // partitianing

        quickSort(numbers, order, lowIndex, leftPointer - 1); // left side -> recursioning
        quickSort(numbers, order, leftPointer + 1, highIndex); // right side -> recursioning
    }

    /**
     * partitian code for quicksort
     * @param   numbers     the array to sort
     * @param   lowIndex    lowest index of array
     * @param   highIndex   highest index of array
     * @param   pivot       the number to pivot off of
     * @param   order       lowest to greatest or vice versa
    */
    public static int partitian(int[] numbers, int lowIndex, int highIndex, int pivot, int order) {
        // create the index of the two pointers
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        if (order == 0) {
            // descending
            // move the pointers together
            while (leftPointer < rightPointer) {
                // move left pointer (needs to be bigger than pivot because its descending)
                while (numbers[leftPointer] >= pivot && leftPointer < rightPointer) {
                    leftPointer++;
                }

                // move right pointer
                while (numbers[rightPointer] <= pivot && rightPointer > leftPointer) {
                    rightPointer--;
                }

                swap(numbers, leftPointer, rightPointer); // swap the pointer values
            }

            // when pointers are at the same place, puts the pivot in correct place
            swap(numbers, leftPointer, highIndex);
        } else {
            // ascending
            // move the pointers together
            while (leftPointer < rightPointer) {
                // move left pointer (needs to be smaller than pivot bc its ascending order)
                while (numbers[leftPointer] <= pivot && leftPointer < rightPointer) {
                    leftPointer++;
                }

                // move right pointer
                while (numbers[rightPointer] >= pivot && rightPointer > leftPointer) {
                    rightPointer--;
                }

                swap(numbers, leftPointer, rightPointer); // swap the pointer values
            }
            swap(numbers, leftPointer, highIndex); // when the pointers are at the same place
        }

        return leftPointer;
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