package U2A4_EddieBian;
/**
 * Grocery Store Inventory - Item class
 * Eddie
 * July 14, 2025
 * Allows user to interact with a file (inventory.txt) that stores a lot of information about items
 * This class allows to create objects that go into each line of the text file
 * (btw didn't have enough time to have different @param descriptions)
*/

public class Item {
    // instance variables
    private String sku;
    private String name;
    private String category;
    private int quantity;
    private int minimumQuantity;
    private double vendorPrice;
    private double markupPercetange;
    private double regularPrice;
    private double currentDiscount;
    private double currentPrice;

    /**
     * Class constructor
     * @param   sku                 the sku of the product (XXX-####)
     * @param   name                name of product
     * @param   category            product category
     * @param   quantity            quantity of product
     * @param   minimumQuantity     minimum quantity of product
     * @param   vendorPrice         vendor's price of product
     * @param   markupPercetange    markup percentage of product
     * @param   regularPrice        regular price of product
     * @param   currentDiscount     current discoutn of product
     * @param   currentPrice        current product price
    */
    public Item(String sku, String name, String category, int quantity, int minimumQuantity, double vendorPrice, double markupPercetange, double regularPrice, double currentDiscount, double currentPrice) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
        this.vendorPrice = vendorPrice;
        this.markupPercetange = markupPercetange;
        this.regularPrice = regularPrice;
        this.currentDiscount = currentDiscount;
        this.currentPrice = currentPrice;
    }

    // getters and setters
    /**
     * gets the sku
     * @return  the sku as a string
    */
    public String getSku() { return this.sku; }
    /**
     * sets the Sku
     * @param   Sku     the new value to set the variable as
    */
    public void setSku(String sku) { this.sku = sku; }

    /**
     * gets the name of product
     * @return  the name as a string
    */
    public String getName() { return this.name; }
    /**
     * sets the Name
     * @param   Name        the new value to set the variable as
    */
    public void setName(String name) { this.name = name; }

    /**
     * gets the category
     * @return  the category as a string
    */
    public String getCategory() { return this.category; }
    /**
     * sets the Category
     * @param   Category        the new value to set the variable as
    */
    public void setCategory(String category) { this.category = category; }

    /**
     * gets the quantity
     * @return  the quantity as a integer
    */
    public int getQuantity() { return this.quantity; }
    /**
     * sets the Quantity
     * @param   Quantity        the new value to set the variable as
    */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * gets the minimum quantity
     * @return  the min quantity as a integer
    */
    public int getMinimumQuantity() { return this.minimumQuantity; }
    /**
     * sets the MinimumQuantity
     * @param   MinimumQuantity     the new value to set the variable as
    */
    public void setMinimumQuantity(int minimumQuantity) { this.minimumQuantity = minimumQuantity; }

    /**
     * gets the vendor price
     * @return  the vendor price as double
    */
    public double getVendorPrice() { return this.vendorPrice; }
    /**
     * sets the VendorPrice
     * @param   VendorPrice     the new value to set the variable as
    */
    public void setVendorPrice(double vendorPrice) { this.vendorPrice = vendorPrice; }

    /**
     * gets the markup percent
     * @return  the markup percent as double
    */
    public double getMarkupPercetange() { return this.markupPercetange; }
    /**
     * sets the MarkupPercetange
     * @param   MarkupPercetange        the new value to set the variable as
    */
    public void setMarkupPercetange(double markupPercetange) { this.markupPercetange = markupPercetange; }

    /**
     * gets the reg price
     * @return  the reg price as double
    */
    public double getRegularPrice() { return this.regularPrice; }
    /**
     * sets the RegularPrice
     * @param   RegularPrice        the new value to set the variable as
    */
    public void setRegularPrice(double regularPrice) { this.regularPrice = regularPrice; }

    /**
     * gets the current discount percent
     * @return  the current discount percent as double
    */
    public double getCurrentDiscount() { return this.currentDiscount; }
    /**
     * sets the CurrentDiscount
     * @param   CurrentDiscount     the new value to set the variable as
    */
    public void setCurrentDiscount(double currentDiscount) { this.currentDiscount = currentDiscount; }

    /**
     * gets the current product price
     * @return  the current product price as double
    */
    public double getCurrentPrice() { return this.currentPrice; }
    /**
     * sets the CurrentPrice
     * @param   CurrentPrice        the new value to set the variable as
    */
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    /**
     * Overrides the toString method to display formatted details (also used to put Item back into file)
     * @return  a formatted string
    */
    @Override
    public String toString() {
        return sku + "," + name + "," + category + "," + quantity + "," + minimumQuantity + "," + vendorPrice + "," + markupPercetange + "," + regularPrice + "," + currentDiscount + "," + currentPrice;
    }
}