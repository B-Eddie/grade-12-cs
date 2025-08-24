/**
 * PaperTrade
 * Eddie, Seyon, Eyosias
 * July 19, 2025
 * The user class of Papertrade
*/

import java.util.ArrayList;

public class User {
    // initial variables
    private String username;
    private String password;
    private Portfolio portfolio; // current portfolio
    private ArrayList<Holding> transactions = new ArrayList<>(); // all history of transactions
    
    /**
     * Constructor method
     * @param   username    the username to set
     * @param   password    the password to set
    */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.portfolio = new Portfolio(); // add a default portfolio for each user
    }
    
    // getters & setters
    /**
     * Getter method to get the Username
     * @return  the Username as a String
    */
    public String getUsername() {
        return this.username;
    }
    
    /**
     * Setter method to set the Username
     * @param   username    the new String object to set as
    */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Getter method to get the Password
     * @return  the Password as a String
    */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Setter method to set the Password
     * @param   password    the new String to set as
    */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Getter method to get the Portfolio
     * @return  the Portfolio as a Portfolio object
    */
    public Portfolio getPortfolio() {
        return this.portfolio;
    }
    
    /**
     * Setter method to set the Portfolio
     * @param   portfolio   the new Portfolio object to set as
    */
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
    
    /**
     * Setter method to add the holding to transaction arraylist
     * @param   holding     the new arraylist to set as
    */
    public void addTransaction(Holding holding) {
        transactions.add(holding);
    }
    
    /**
     * Getter method to get the Transactions
     * @return  the transactions as an ArrayList
    */
    public ArrayList<Holding> getTransactions() {
        return this.transactions;
    }
    
    /**
     * Getter method to get the balance of portfolio
     * @return      the portfolio's balance
    */
    public double getBalance() {
        return portfolio.getBalance(this);
    }
    
    /**
     * returns formatted information about the user
     * passing "this" from https://coderanch.com/t/550104/java/pass-current-object-parameter-method
     * @return      formatted information about the user as a string
    */
    @Override
    public String toString() {
        // add initial information, check if arraylist is empty. if it isn't, loop & add data
        
        // add initial information
        String result = this.username + "\nBalance:\n" + getBalance() + "\nHistory:\n";
        
        // add transaction history to result
        // check if transaction is empty
        if (transactions.size() != 0) {
            // loop through transactions
            for (int i = 0; i < transactions.size(); i++) {
                // append to result string
                result += transactions.get(i) + "\n";
            }
        } else {
            // when the transactions arraylist is empty
            result += "No transactions";
        }
        
        // add current holdings to portfolio
        result += "\n\nPortfolio:\n";
        
        // check if the portfolio arraylist is empty
        if (portfolio.getHoldings(this).size() != 0) {
            // loop through the holding arraylist, adding to the result string
            for (Holding holding : portfolio.getHoldings(this)) {
            		// append to result string
            		result += holding + "\n";
            }
        } else {
            // when there is nothing inside portfolio
            result += "No stocks";
        }
        
        // return the result
        return result;
    }
}