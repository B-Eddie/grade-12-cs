/**
 * PaperTrade
 * Eddie, Seyon, Eyosias
 * July 19, 2025
 * The main file for the papertrade program. It contains the main methods to run.
 * Papertrade is a simulation for the stock market. It uses simulated data to work.
*/

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main
{
    // initial variables (put here so methods can use them)
    static Scanner scanner = new Scanner(System.in); // initialize scanner
    static boolean menu = true;
    static ArrayList<User> users = new ArrayList<>(); // user list
    static ArrayList<Stock> stocks = loadStocks(); // stock list
    static Market market = new Market(stocks); // market object
    static User userProfile;
    
    // https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java/23910924#23910924
    // parse date from yyyy-mm-dd pattern
    // create the initial date for the program to run
    static LocalDate currentDate = LocalDate.parse("2025-01-01");
    
    // main method
    public static void main(String[] args) {
        // initialize price class
        Price.initialize();
        
        // update the arraylists with values from file
        users = updateUsers();
        loadTransactions(users);
        
        // display the signup/login page
        displaySignupLogin(scanner, users);

        // keep on looping to show the menu
        while (menu) {
            // returns variable to keep looping or not
            menu = displayMenu(scanner);
        }
    }
    
    /**
     * Display the login/signup page
     * @param   scanner     the scanner for user interactions
     * @param   users       arraylist to check if user already exists
    */
    public static void displaySignupLogin(Scanner scanner, ArrayList<User> users) {
        // initial vars
        boolean signupLogin = false; // to continue looping or not
        
        // signin login page
        System.out.println("====PaperTrade====");
        System.out.println("1) Sign up");
        System.out.println("2) Log in");
        
        // receive user input
        int signupLoginChoice = getIntInput("Your choice: ", scanner);
        
        // check if choice is in range
        while (signupLoginChoice <= 0 && signupLoginChoice >= 3) {
            signupLoginChoice = getIntInput("Invalid choice, try again: ", scanner);
        }
        
        // clear screen
        clearScreen();
        
        // while loop to signup/login
            // if input is 1 (signing up), get passwords and usernames, and call method to check
                // if they are right, stop looping, otherwise continue looping
            // if input is 2 (loging in), get passwords and usernames, and call method to check if they're right
                // if they are right, stop looping, otherwise continue looping
        // continue looping until the input is correct
        while (!signupLogin) {
            if (signupLoginChoice == 1) {
                // sign up
                System.out.println("====Sign up====");
                String username = getStringInput("Username: ", scanner);
                String password = getStringInput("Password: ", scanner);
                signupLogin = signup(username, password, users); // returns true if correct
            } else {
                // log in
                System.out.println("====Log in====");
                String username = getStringInput("Username: ", scanner);
                String password = getStringInput("Password: ", scanner);
                signupLogin = login(username, password, users); // returns true if correct
            }
        }
        
        // clear screen when user chooses
        clearScreen(1000);
        updateUserFile(users);
    }
    
    /**
     * Display the menu and calls other methods based on users option
     * @param   scanner     to receive user input
     * @return              boolean of true/false to continue looping (return true) or not
    */
    public static boolean displayMenu(Scanner scanner) {
        // display options
        System.out.println("====Menu====");
        System.out.println("Date " + currentDate);
        System.out.println("1) Profile");
        System.out.println("2) Trade");
        System.out.println("3) List all stocks");
        System.out.println("4) Stock search");
        System.out.println("5) Next day");
        System.out.println("6) Leaderboard");
        System.out.println("7) Information");
        System.out.println("8) Exit");
        
        // get user choice with scanner
        int menuChoice = getIntInput("Your choice: ", scanner);
        
        // runs method based on user choice
            // choice == 1 -> run method at option 1
            // choice == 2 -> run method at option 2
            // choice == 3 -> run method at option 3
            // and so on
        // checks if user choice is in correct range
        if (menuChoice > 0 && menuChoice <= 8) {
            if (menuChoice == 1) {
                // profile
                displayProfile();
            } else if (menuChoice == 2) {
                // trade
                trade(scanner);
            } else if (menuChoice == 3) {
                // display all stocks
                // get the input of way of sorting stocks
                int sortBy = getIntInput("Sort stocks alphabetically (0) or by price (1): ", scanner);
                
                // continune looping if the input is not 1 or 0
                while (sortBy != 1 && sortBy != 0) {
                    System.out.println("Invalid input");
                    sortBy = getIntInput("Sort stocks alphabetically (0) or by price (1): ", scanner);
                }
                
                // display the stocks sorted
                market.listStocks(sortBy);
            } else if (menuChoice == 4) {
                // stock search
                stockSearch(scanner);
            } else if (menuChoice == 5) {
                // simulate the stock movements
                nextDay(market);
            } else if (menuChoice == 6) {
                // leaderboard
                leaderboard(users);
            } else if (menuChoice == 7) {
                // information
                displayInfo();
            } else if (menuChoice == 8) {
                // exit program
                System.out.println("Exited.");
                return false;
            }
        } else {
            // display wrong option message and display info about program
            System.out.println("Invalid option");
            // display info
            displayInfo();
        }
        
        // code to continue the loop when user presses"enter"
        // checks if scanner has a next line token, if it does, call the clear screen method
        System.out.print("\nPress enter to continue: ");
        if (scanner.hasNextLine()) {
            clearScreen(); // clear screen
        }
        
        // returns true to keep on showing the menu (keeps on looping until false -> when user chooses 7)
        return true;
    }
    
    // ===MENU OPTIONS===
    /**
     * Displays information about the user's profile
    */
    public static void displayProfile() {
        System.out.println(userProfile);
    }
    
    /**
     * Allowing the user to buy/sell stocks
     * @param   scanner     scanner to get user input
    */
    public static void trade(Scanner scanner) {
        clearScreen(); // clears console
        
        // display options
        System.out.println("====Enter a trade====");
        System.out.println("1: Buy stocks");
        System.out.println("2: Sell stocks");
        
        // get user input
        int choice = getIntInput("Your choice: ", scanner);
        
        
        // checks which option the user chose
            // if the user chose 1 (buying stocks) -> do buying stock logic
                // display stocks to buy from, get user input, checks if user can buy, displays confirmation, then buys
            // if the user chose 2 (selling stocks) -> do selling stock logic
                // display existing stocks that user can sell, get user input, checks if user can sell, displays confirmation, then sells
        if (choice == 1) {
            // buy stock
            // display stocks to buy, sorted alphabetically
            market.listStocks(0);
            
            // gets user input and turns to uppercase (tickers are uppercased)
            String ticker = getStringInput("Enter ticker to buy: ", scanner).toUpperCase();
            Stock stock = market.getStock(ticker);
            
            // check if stock is found
            // keep on looping until user inputs a correct stock
            while (stock == null) {
                System.out.println("Stock not found");
                ticker = getStringInput("Enter ticker to buy: ", scanner).toUpperCase();
                stock = market.getStock(ticker);
            }
            
            // get quantity (if its 0, don't let user buy)
            int quantity = getIntInput("Enter quantity to buy: ", scanner);
            if (quantity == 0) {
                System.out.println("Cannot buy 0 shares.");
                return;
            }
            
            // get total cost of buying the stocks by multipliying price in usd x quantity
            double totalCost = Price.toUSD(stock.getCurrency(), stock.getPrice()) * quantity;
            
            
            // check if user can buy (if user balance is bigger than total cost of buying stock)
            if (userProfile.getPortfolio().getBalance(userProfile) >= totalCost) {
                // confirm purchase with user by checking if its y/n
                if (!getConfirmInput("Are you sure you would like to buy $" + totalCost + " worth of " + stock.getTicker() + " (Y/N)? ", scanner)) {
                    // if its false (n), do not continue and cancel purchase
                    System.out.println("Purchase cancelled");
                    return;
                }
                // when user inputted "y", continue
                
                // create holding object (to be added to transaction list)
                Holding holding = new Holding(stock, quantity, Price.toUSD(stock.getCurrency(), stock.getPrice()), currentDate, "BUY");
                
                // add transaction to user profile
                userProfile.addTransaction(holding);
                saveTransactions(users); // save the transaction
                
                // success msg
                System.out.println("Bought " + quantity + " shares of " + stock.getTicker() + " at $" + Price.toUSD(stock.getCurrency(), stock.getPrice()) + " USD for $" + totalCost + " USD total");
            } else {
                // when balance is less than cost
                System.out.println("Insufficient balance");
            }
            
        } else if (choice == 2) {
            // sell
            // get holdings arraylist from portfolio
            ArrayList<Holding> holdings = userProfile.getPortfolio().getHoldings(userProfile); 
            
            // check length of arraylist to see if user has any stocks
            if (holdings.size() == 0) {
                System.out.println("You have no stocks to sell");
                return;
            }
            
            // loop through holdings and display them
            System.out.println("Your holdings:");
            for (int i = 0; i < holdings.size(); i++) {
                // display the current stocks
                System.out.println((i + 1) + ": " + holdings.get(i));
            }
            
            // get user input for stock to sell
            int index = getIntInput("Choose a holding index to sell: ", scanner);
            
            // checking if the user's input is correct and reprompt if wrong
            while (index > holdings.size() || index == 0) {
                System.out.println("Invalid choice");
                index = getIntInput("Choose a holding index to sell: ", scanner);
            }
            
            // start removal process
            Holding holdingToSell = holdings.get(index - 1); // get holding object
            Stock stock = market.getStock(holdingToSell.getStock().getTicker()); // get updated stock data
            
            // get price of stock in usd
            double sellPrice = Price.toUSD(stock.getCurrency(), stock.getPrice());
            
            // get quantity of the stock
            int quantity = getIntInput("Enter quantity to sell: ", scanner);
            
            // do not let the user sell 0 shares
            if (quantity == 0) {
                System.out.println("Cannot sell 0 shares.");
                return;
            }
            
            // check if selling the stock is under the quantity of stock the user has
            int maxQuantity = holdingToSell.getQuantity(); // get quantity of stock
            
            // keep on looping if the # of shares is more than the user has to sell
            while (quantity > maxQuantity) {
                System.out.println("You don't have that many shares to sell");
                quantity = getIntInput("Enter quantity to sell: ", scanner); // reprompt
            }
            
            // calculate profit
            double profit = sellPrice * quantity;
            
            // confirm sell with user
            if (!getConfirmInput("Are you sure you would like to sell $" + profit + " worth of " + stock.getTicker() + " (Y/n)? ", scanner)) {
                // if the user chose "n", cancel the transaction
                System.out.println("Sell cancelled");
                return;
            }
            
            // add transaction to user profile
            // create new holding object
            Holding holding = new Holding(stock, quantity, Price.toUSD(stock.getCurrency(), stock.getPrice()), currentDate, "SELL");
            // add the holding the transactions history
            userProfile.addTransaction(holding);
            saveTransactions(users); // save the transaction
            
            // success msg
            System.out.println("Sold " + quantity + " shares of " + stock.getTicker() + " at $" + sellPrice + " USD");
        } else {
            // if the user didn't choose buy or sell
            System.out.println("Invalid option");
        }
    }
    
    /**
     * Saves transactions to the transactions.txt file
     * @param   users   arraylist to get the transactions from
    */
    public static void saveTransactions(ArrayList<User> users) {
        try {
            // open file
            BufferedWriter fileWrite = new BufferedWriter(new FileWriter("transactions.txt"));
    
            // loop through all users
            for (User user : users) {
                // for each user, get the holding and write to the transaction file
                for (Holding h : user.getTransactions()) {
                    // format: username,type,ticker,quantity,price,date
                    // write to file
                    fileWrite.write(user.getUsername() + "," + h.getType() + "," + h.getStock().getTicker() + "," + h.getQuantity() + "," + h.getPurchasePrice() + "," + h.getDate() + "\n");
                }
            }
            
            // display success message
            System.out.println("Saved transactions");
            fileWrite.close();
        } catch (IOException e) {
            // error message
            System.out.println("Error writing to file");
        }
        
    }
    
    /**
     * Loads transactions from the file
     * @param   users   arraylist to get the transactions from
    */
    public static void loadTransactions(ArrayList<User> users) {
        // open file, loop through all lines and add the data to the arraylist
        try {
            // open file
            BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"));
            
            // get line variable to be used to get new lines
            String line;
            
            // keep on looping through lines until next line is empty
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(","); // split lines by comma
                
                // extract the variables accordingly 
                // format: username,type,ticker,quantity,price,date
                String username = splitLine[0];
                String type = splitLine[1];
                String ticker = splitLine[2];
                int quantity = Integer.valueOf(splitLine[3]);
                double price = Double.valueOf(splitLine[4]);
                LocalDate date = LocalDate.parse(splitLine[5]);
                
                // match a user object with the username
                // first set the user object to null to set when found
                User user = null;
                
                // loop through all users
                for (User u : users) {
                    // check if the username equals the username to look for
                    if (u.getUsername().equals(username)) {
                        // set the user to the found user
                        user = u;
                        break;
                    }
                }
                
                // get the stock object
                Stock stock = market.getStock(ticker);
                user.addTransaction(new Holding(stock, quantity, price, date, type)); // add to arraylist
            }
            
            reader.close(); // close reader
        } catch (IOException e) {
            System.out.println("Error opening file.");
        }
        
    }
    
    /**
     * Finds a stock based on a user's input
     * @param   scanner     the scanner to receive input from
    */
    public static void stockSearch(Scanner scanner) {
        // get user input, call the getstock function
        
        // get user option
        String userSearch = getStringInput("Enter ticker/company name: ", scanner);
        
        // call method for finding stock
        Stock stockFound = market.getStock(userSearch);
        
        // check if the user was found
        if (stockFound == null) {
            System.out.println(userSearch + " was not found");
        } else {
            System.out.println(stockFound);
        }
    }
    
    /**
     * Goes to the next day by simulating price changes
     * @param   market      market object to simulate the changes
    */
    public static void nextDay(Market market) {
        // call the function to simulate price change, add a day to the in game day
        market.simulatePriceChange();
        currentDate = currentDate.plusDays(1);
    }
    
    /**
     * Displays how to use the program
    */
    public static void displayInfo() {
        // display info about the program
        System.out.println("\n====Information====");
        System.out.println("This is a text-based stock sim");
        System.out.println("Use menu options to:");
        System.out.println("1: View your profile and balance");
        System.out.println("2: Start trading stocks through buying/selling");
        System.out.println("3: View all available stocks");
        System.out.println("4: Search for a specific stock");
        System.out.println("5: Make the simulation go to the next day and simulate prices");
        System.out.println("6: Display the leaderboard for people with most profit");
        System.out.println("7: Display information");
        System.out.println("8: Exit program");
        
        System.out.println("\nNotes:");
        System.out.println("- the stocks prices are generated automatically");
        System.out.println("- no real money is involved");
    }
    
    /**
     * Displays the leaderboard
     * @param   users   arraylist to get check user's total profit
    */
    public static void leaderboard(ArrayList<User> users) {
        // create a list of users to be sorted
        ArrayList<User> sortedLeaderboard = new ArrayList<User>();
        
        // copy all users from unsorted list by looping through existing arraylist and adding each user to arraylist
        for (int i = 0; i < users.size(); i++) {
            sortedLeaderboard.add(users.get(i));
        }
        
        // sort the users from high to low
        // loop through the users, use selection sort to sort 
        for (int i = 0; i < sortedLeaderboard.size(); i++) {
            double highestScore = -Double.MAX_VALUE; // placeholder for highest score
            int index = -1;
            
            // compare every item after current one and find the highest balance 
            for (int j = i; j < sortedLeaderboard.size(); j++) {
                double balance = sortedLeaderboard.get(j).getBalance(); // get the balance of a user
                
                // check if the balance is bigger than the highest score
                if (balance > highestScore) {
                    highestScore = balance;
                    index = j;
                }
            }
            
            // swap highest with current index
            User placeholder = sortedLeaderboard.get(index); // create placeholder value
            sortedLeaderboard.set(index, sortedLeaderboard.get(i));
            sortedLeaderboard.set(i, placeholder);
        }
        
        // display list of users
        for (int i = 0; i < sortedLeaderboard.size(); i++) {
            // for each one, display the username and balance
            System.out.println("" + (i + 1) +  " | " + sortedLeaderboard.get(i).getUsername() + " : " + sortedLeaderboard.get(i).getBalance());
        }
    }
    
    
    /**
     * Loads all stocks from the file
     * @return      all the available stocks in an arraylist of Stock objects
    */
    public static ArrayList<Stock> loadStocks() {
        // create temporary arraylist, open file, add each line to the arraylist
        
        ArrayList<Stock> stocks = new ArrayList<>(); // arraylist to store stocks
        
        // format: TICKER,companyname,price
        try {
            // open file
            BufferedReader reader = new BufferedReader(new FileReader("stocks.txt"));
            
            // get line variable to be used to get new lines
            String line;
            
            // skip first line
            line = reader.readLine();
            
            // keep on looping through lines until next line is empty
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(","); // split lines by comma
                
                // extract the variables accordingly
                String company = splitLine[0];
                String ticker = splitLine[1];
                double price = Double.valueOf(splitLine[2]); // price is required as double
                String currency = splitLine[3];
                String exchange = splitLine[4];
                String category = splitLine[5];
                
                // add to arraylist
                stocks.add(new Stock(ticker, company, price, currency, exchange, category));
            }
            
            reader.close(); // close reader
        } catch (IOException e) {
            System.out.println("Error opening file.");
        }
        
        return stocks; // return arraylist
    }
    
    /**
     * Updates arraylist with values from file
     * @return      Arraylist of users loaded from users.txt
    */
    public static ArrayList<User> updateUsers() {
        ArrayList<User> users = new ArrayList<>(); // create new arraylist to return later
        
        // format: username,password
        try {
            // open file
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            
            // get line variable to be used to get new lines
            String line;
            
            // keep on looping through lines until next line is empty
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(","); // split lines by comma
                String username = splitLine[0]; // first index is username
                String password = splitLine[1]; // second is password
                
                users.add(new User(username, password)); // add user to arraylist
            }
            
            reader.close(); // close reader
        } catch (IOException e) {
            System.out.println("Error opening file.");
        }
        
        return users; // return arraylist
    }
    
    /**
     * Checks if user exists. return true if correct credentials, false if not found
     * @param   username    the user's username
     * @param   password    the user's password
     * @param   users       arraylist of all users to check if credentials are right
     * @return              true if correct credentials, false if not
    */
    public static boolean login(String username, String password, ArrayList<User> users) {
        // loop through users in arraylist, compare username and password (hased)
            // if it is correct, display success message and set global user object as that user
        
        // loop through all users in arraylist
        for (User user : users) {
            // check if username and password are correct combination
            // for the password, check it with the hash
            if (user.getUsername().equals(username) && user.getPassword().equals(generateHash(password))) {
                System.out.println("Logged in."); // display message to user
                userProfile = user; // set the global user variable for easy acces
                return true;
            }
        }
        
        // when it looped through the entire arraylist
        System.out.println("User not found. Try again");
        return false;
    }
    
    /**
     * Checks if username is taken. if not, allow the user to signup
     * @param   username    the user's username
     * @param   password    the user's password
     * @param   users       arraylist of all users to check
     * @return              true if no conflicting records, false if username taken
    */
    public static boolean signup(String username, String password, ArrayList<User> users) {
        // check if username is alphanumeric, loop through arraylist, check if username exists
        
        // checks if there are non-alphanumeric characters in username w/ regex. if there is, display error message and return false
        // https://stackoverflow.com/questions/8248277/how-to-determine-if-a-string-has-non-alphanumeric-characters
        if (username.matches("^.*[^a-zA-Z0-9 ].*$")) {
            System.out.println("Error: username can only contain alphanumeric characters.");
            return false;
        }
        
        // loop through all users in arraylist
        for (User user : users) {
            // check if username is taken (equal to the users choice)
            if (user.getUsername().equals(username)) {
                // display message and return
                System.out.println("Username taken");
                return false;
            }
        }
        
        // when there is no conflicting usernames
        User userToAdd = new User(username, generateHash(password));
        users.add(userToAdd); // add to arraylist
        userProfile = userToAdd; // set the global user variable for easy acces
        System.out.println("Signup successful"); // succeess message
        return true;
    }
    
    /**
     * Update the users.txt file to have updated username password pairs
     * @param   users   the arraylist to update the txt file with
    */
    public static void updateUserFile(ArrayList<User> users) {
        // open file, loop through users and add username,password to file
        
        try {
            // open file
            BufferedWriter fileWrite = new BufferedWriter(new FileWriter("users.txt"));
    
            // loop through users
            for (User user : users) {
                // write the combination to the file
                fileWrite.write(user.getUsername() + "," + user.getPassword() + "\n");
            }
            
            fileWrite.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
    
    /**
     * Clears the console screen
    */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // moves mouse to top left
        System.out.flush(); // clears screen
    }
    
    /**
     * Clears the console screen - overload method to add delay
     * @param   delay   the amount of time to wait for in ms
    */
    public static void clearScreen(int delay) {
        long pastTime = System.currentTimeMillis(); // time when starting to loop
            
        // keeps looping until the difference in time is more than the delay in ms
        while (System.currentTimeMillis() - pastTime < delay) {}
        
        System.out.print("\033[H\033[2J"); // moves mouse to top left
        System.out.flush(); // clears screen
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
            System.out.print(input);
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
    
    /**
     * helper functions to receive correct Y/n input from user
     * @param   input       question string to ask user
     * @param   scanner     scanner to receive data from user
     * @return              the user input as a boolean
    */
    public static boolean getConfirmInput(String input, Scanner scanner) {
        while (true) {
            // get line from user
            String line = getStringInput(input, scanner);
            
            // if user entered 'y', return true
            if (line.equals("y")) {
                return true;
            } else if (line.equals("n")) {
                // if user entered 'n', return false
                return false;
            } else {
                // Otherwise, tell user input is invalid and keep looping
                System.out.println("Invalid input");
            }
        }
    }
    
    /**
     * Generates MD5 hash of a given string
     * @param   str     string to be hashed
     * @return              hashed string
    */
    public static String generateHash(String str) {
        // Using https://www.baeldung.com/java-md5
        
        // create md5 hasing object
        MessageDigest md;
        
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        
        // generate md5 hash of given string
        md.update(str.getBytes());
        byte[] digest = md.digest();
        
        // return string representation of hash
        return HexFormat.of().formatHex(digest).toUpperCase();
    }
}