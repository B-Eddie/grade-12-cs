/**
 * PaperTrade
 * Eddie, Seyon, Eyosias
 * July 19, 2025
 * The portfolio class of Papertrade
*/

import java.util.ArrayList;

public class Portfolio {
    // instance variables
    private ArrayList<Holding> holdings;
    private double balance;
    
    /**
     * Constructor - create arraylist for holdings
    */
    public Portfolio() {
        holdings = new ArrayList<>();
    }
    
    // getters and setters
    /**
     * Gets the balance of a user (for entire history)
     * @param   user    the user to check the balance of
     * @return          the balance as double
    */
    public double getBalance(User user) {
        double balance = 1000000; // user starts with 1 mil
        ArrayList<Holding> transactions = user.getTransactions(); // get all transactions
        
        // loop through transactions, if value equals buy, remove from balance. if sell, add to bal
        for (Holding holding : transactions) {
            // check what type of holding it is
            if (holding.getType().equals("BUY")) {
                // remove from balance
                balance -= holding.getValue();
            } else {
                // add to balance
                balance += holding.getValue();
            }
        }
        
        // return the updated balance
        return balance;
    }
    
    /**
     * Set the balance
     * @param   balance     the balance to set it as
    */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    /**
     * Get all the user holdings
     * @param   user    the user to get holdings from
     * @return          all holdings in an arraylist
    */
    public ArrayList<Holding> getHoldings(User user) {
        // create new arraylist for current stocks
        ArrayList<Holding> syncedList = new ArrayList<Holding>();
        // get existing transactions for the user
        ArrayList<Holding> transactions = user.getTransactions();
        
        // loop through transactions
        for (Holding h : transactions) {
            // get the string
            String ticker = h.getStock().getTicker();
            boolean found = false; // if the transaction was already added to arraylist
            
            // loop through the syncedlist to check if exists
            for (Holding s : syncedList) {
                // compare ticker names
                if (s.getStock().getTicker().equals(ticker)) {
                    if (h.getType().equals("BUY")) {
                        // if type is buy, add to the quantity
                        s.setQuantity(s.getQuantity() + h.getQuantity());
                    } else {
                        // if type is sell, subtract quantity
                        int newQuantity = s.getQuantity() - h.getQuantity();
                        
                        // checks if subtracting equals 0 and if it is, remove it
                        if (newQuantity == 0.0) {
                            // remove from arraylist
                            syncedList.remove(s);
                        } else {
                            // set the quantity to updated one
                            s.setQuantity(newQuantity);
                        }
                    }
                    
                    found = true;
                    break;
                }
            }
            
            // add the new holding to the synced arraylist if wasn't found
            // the last variable is a string since displaying portfolio doesn't need to show buy/sell
            if (!found) {
                // add to syncedlist
                syncedList.add(new Holding(h.getStock(), 
                    h.getQuantity(), 
                    h.getPurchasePrice(), 
                    h.getDate(), 
                    ""
                ));
            }
        }
        
        // return
        return syncedList;
    }
    
    /**
     * add a holding to holdings arralisy
     * @param   holding     the holding to add
    */
    public void addStock(Holding holding) {
        this.holdings.add(holding);
    }
    
    /**
     * Remove stock
     * @param   index   the index to remove from holdings arraylist
    */
    public void removeStock(int index) {
        this.holdings.remove(index);
    }
    
    /**
     * Override method to display information about holdings formatted
     * @return      the formatted information as a string
    */
    @Override
    public String toString() {
        // initial description
        String result = "Holdings:\n";
        
        // check if holdings arraylist is empty
        if (this.holdings.size() == 0) {
            // loop through holdings & adding to string
            for (Holding holding : this.holdings) {
                result += holding + "\n";
            }
        } else {
            // nothing inside arraylist
            result += "None so far";
        }
        return result;
    }
}