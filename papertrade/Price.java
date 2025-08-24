/**
 * PaperTrade
 * Eddie, Seyon, Eyosias
 * July 19, 2025
 * The price class of Papertrade
*/

import java.io.*;
import java.util.ArrayList;

public class Price {
    // instance variables
    private static ArrayList<String> currencies;
    private static ArrayList<Double> rates;
    
    // getters and setters
    /**
     * gets the currencies arraylist
     * @return  the arraylist that contains currencies
    */
    public ArrayList<String> getCurrencies() {
        return this.currencies;
    }
    
    /**
     * Sets the currrencies arraylist
     * @param   currencies      the arraylist to it set as
    */
    public void setCurrencies(ArrayList<String> currencies) {
        this.currencies = currencies;
    }
    
    /**
     * gets the rates arraylist
     * @return  the arraylist that contains rates
    */
    public ArrayList<Double> getRates() {
        return this.rates;
    }
    
    /**
     * Sets the currrencies arraylist
     * @param   rates      the arraylist to set it as
    */
    public void setRates(ArrayList<Double> rates) {
        this.rates = rates;
    }
    
    /**
     * Initialize price class by reading from file
    */
    public static void initialize() {
        // initlize currencies and respective conversion rates
        currencies = new ArrayList<String>();
        rates = new ArrayList<Double>();
        
        // try reading file
        try {
            // create new reader object
            BufferedReader reader = new BufferedReader(new FileReader("exchange_rates.txt"));
            
            // get first line
            String line = reader.readLine();
            
            // skip first line
            line = reader.readLine();
            
            // for each line after first, add data to currency and rate lists
            while (line != null) {
                String[] data = line.split(","); // split data by comma
                
                // add data accordingly
                currencies.add(data[0]);
                rates.add(Double.parseDouble(data[1]));
                
                // next line
                line = reader.readLine();
            }
            
            // Close reader
            reader.close();
        } catch (IOException e) {
            // If error, display error and end function
            e.printStackTrace();
            return;
        }
    }
    
    /**
     * Convert given price to USD
     * @param   currency    currency of price
     * @param   price       price to be converted
     * @return              USD conversion of price
    */
    public static double toUSD(String currency, double price) {
        // if already USD, return price
        if (currency.equals("USD")) {
            return price;
        }
        
        // find the currency of price in currency list and use it to convert and return price
        for (int i = 0; i < currencies.size(); i++) {
            if (currency.equals(currencies.get(i))) {
                // return the correct rate
                return Math.round(price * rates.get(i) * 100) / 100.0;
            }
        }
        
        // return -1 if currency could not be found
        return -1;
    }
}