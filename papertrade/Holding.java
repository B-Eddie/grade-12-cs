/**
 * PaperTrade
 * Eddie, Seyon, Eyosias
 * July 19, 2025
 * The current holdings class of Papertrade
*/

import java.time.LocalDate;

public class Holding {
    // instance variables
    private Stock stock;
    private int quantity;
    private double purchasePrice;
    private LocalDate purchaseDate;
    private String type; // buy/sell
    
    
    /**
     * The constructor method
     * @param
     * @return
    */
    public Holding(Stock stock, int quantity, double purchasePrice, LocalDate purchaseDate, String type) {
        this.stock = stock;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.type = type;
    }
    
    // Getters and setters
    /**
     * Getter method to get the Stock
     * @return      return the Stock
    */
    public Stock getStock() {
        return this.stock;
    }
    
    /**
     * Getter method to get the Quantity
     * @return      return the Quantity
    */
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * Getter method to get the PurchasePrice 
     * @return      return the PurchasePrice
    */
    public double getPurchasePrice() {
        return this.purchasePrice;
    }
    
    /**
     * Getter method to get the Value
     * @return      return the Value
    */
    public double getValue() {
        return this.quantity * this.purchasePrice;
    }
    
    /**
     * Getter method to get the Date
     * @return      return the Date
    */
    public LocalDate getDate() {
        return this.purchaseDate;
    }
    
    /**
     * Getter method to get the Type
     * @return      return the Type
    */
    public String getType() {
        // returns the type as uppercase
        return this.type.toUpperCase();
    }
    
    /**
     * Setter method to set the Stock
     * @param   stock   the new variable to set as
    */
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    
    /**
     * Setter method to set the Quantity
     * @param   quantity    the new variable to set as
    */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Setter method to set the PurchasePrice
     * @param   purchasePrice   the new variable to set as
    */
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
    /**
     * Setter method to set the PurchaseDate
     * @param   purchaseDate    the new variable to set as
    */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    /**
     * Setter method to set the Type
     * @param   type    the new variable to set as
    */
    public void setType(String type) {
        this.type = type;
    }
    
    
    /**
     * Override method to display information about the object formatted
     * @return      the formatted information as a string
    */
    @Override
    public String toString() {
        return getType() + " " + stock.getTicker() + " x " + this.quantity + 
            " at $" + this.purchasePrice + " bought at " + this.purchaseDate;
    }
}