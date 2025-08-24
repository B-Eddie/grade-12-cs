/**
 * PaperTrade
 * Eddie, Seyon, Eyosias
 * July 19, 2025
 * The market class of Papertrade
*/

import java.util.ArrayList;

public class Market {
    // instance variables
    private ArrayList<Stock> stocks;
    
    /**
     * Constructor method
     * @param   stocks      the arraylist to set as the stocks variable
    */
    public Market(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }
    
    
    // getters and setters
    
    /**
     * Get a specific stock object
     * @param   toUpperCase     the ticker/company name to find
     * @return                  the stock object. if not found, return null
    */
    public Stock getStock(String stockName) {
        String name = stockName.toUpperCase(); // turn string to uppercase for checking
        
        // loop through stocks and check if either ticker or company name equals stockName var
        for (Stock stock : stocks) {
            // compare ticker and company name
            if (stock.getTicker().toUpperCase().equals(name) || stock.getCompany().toUpperCase().equals(name)) {
                // return stock if found
                return stock;
            }
        }
        
        // return null if not found
        return null;
    }
    
    /**
     * Setter method to set arraylist
     * @param   stocks      the new arraylist to set as stocks
    */
    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }
    
    /**
     * Prints all the stocks
     * @param   sort    sorting the stocks alphabetically (sort=0) or by price (sort=1)
    */
    public void listStocks(int sort) {
        // create new arraylist copy of stocks, do selection sort based on either name/price
        
        // create indepedant copy of array to sort stocks
        // https://howtodoinjava.com/java/collections/arraylist/arraylist-clone-deep-copy/#:~:text=Using%20ArrayList.&text=The%20clone()%20method%20creates,same%20objects%20in%20the%20memory.
        ArrayList<Stock> stockToSort = (ArrayList<Stock>) stocks.clone();
        
        // sort arraylist with selection sort
        for (int i = 0; i < stockToSort.size() - 1; i++) {
            int minIndex = i; // set the min index to the first index
            
            // loop through the rest of the list
            for (int k = i + 1; k < stockToSort.size(); k++) {
                // checks which type of sorting
                if (sort == 0) {
                    // sorting by ticker alphabetically
                    String indexName = stockToSort.get(k).getTicker();
                    String lowestName = stockToSort.get(minIndex).getTicker();
                    if (indexName.compareTo(lowestName) < 0) {
                        // when the indexName string goes before lowestName
                        minIndex = k;
                    }
                } else {
                    // sorting by price from low to high
                    double indexPrice = stockToSort.get(k).getPrice();
                    double lowestPrice = stockToSort.get(minIndex).getPrice();
                    if (indexPrice < lowestPrice) {
                        minIndex = k;
                    }
                }
            }
            
            // swap places
            swapPlaces(stockToSort, i, minIndex);
        }
        
        // display stock info       
        for (Stock stock : stockToSort) {
            // System.out.println("===" + stock.getTicker() + " " + stock.getCompany() + "===");
            // System.out.println("Price: " + stock.getPrice());
            // System.out.println("Percent change: " + stock.getPercentChange());
            System.out.println(stock.toString());
        }
    }
    
    /**
     * Helper method to swap places in an array
     * @param   stockList   the arraylist in which to swap the places
     * @param   one         the first index to swap places
     * @param   two         the second index to swap places
    */
    public void swapPlaces(ArrayList<Stock> stockList, int one, int two) {
        // create temp value of first index
        Stock temp = stockList.get(one);
        
        // swap position of first and second index
        stockList.set(one, stockList.get(two));
        stockList.set(two, temp);
    }
    
    /**
     * Moves stocks up/down by random amount
    */
    public void simulatePriceChange() {
        // loop through all stocks, change price randomly -5% to 5%
        for (Stock stock : stocks) {
            double oldPrice = stock.getPrice(); // temp variable
            double percentChange = Math.random() * 10 - 5; // range of -5% to 5%
            
            double change = oldPrice * percentChange; // get the price change
            double newPrice = Math.max(1.0, oldPrice + percentChange); // doesn't let stock be < $1
            
            // update stock variables
            stock.setPrice(newPrice);
            stock.setPreviousPrice(oldPrice);
            stock.setPercentChange();
            stock.setPriceChange();
        }
    }
}