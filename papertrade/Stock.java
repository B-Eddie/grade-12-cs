/**
 * PaperTrade
 * Eddie, Seyon, Eyosias
 * July 19, 2025
 * The stock class of Papertrade
*/

public class Stock {
    // instance variables
    private double price;
    private String ticker;
    private String company;
    private double previousPrice;
    private double percentChange;
    private double priceChange;
    
    // currency variables
    private String currency;
    private String exchange;
    private String category;
    
    /**
     * The constructor method
     * @param   ticker      the ticker to initialize as
     * @param   company     the company to initialize as
     * @param   price       the price to initialize as
     * @param   currency    the currency to initialize as
     * @param   exchange    the exchange to initialize as
     * @param   category    the category to initialize as
    */
    public Stock (String ticker, String company, double price, String currency, String exchange, String category) {
        this.ticker = ticker;
        this.company = company;
        this.price = price;
        this.previousPrice = price;
        this.percentChange = 0;
        this.priceChange = 0;
        
        this.currency = currency;
        this.exchange = exchange;
        this.category = category;
    }
    
    // Getters
    /**
     * gets the Ticker
     * @return  the Ticker as a String 
    */
    public String getTicker() {
        return this.ticker;
    }
    /**
     * gets the Company
     * @return  the Company as a String 
    */
    public String getCompany() {
        return this.company;
    }
    /**
     * gets the Currency
     * @return  the Currency as a String 
    */
    public String getCurrency() {
        return this.currency;
    }
    /**
     * gets the Exchange
     * @return  the Exchange as a String 
    */
    public String getExchange() {
        return this.exchange;
    }
    /**
     * gets the Category
     * @return  the Category as a String 
    */
    public String getCategory() {
        return this.category;
    }
    /**
     * gets the PreviousPrice
     * @return  the PreviousPrice as a double 
    */
    public double getPreviousPrice() {
        return this.previousPrice;
    }
    /**
     * gets the PercentChange
     * @return  the PercentChange as a double 
    */
    public double getPercentChange() {
        return this.percentChange;
    }
    /**
     * gets the PriceChange
     * @return  the PriceChange as a double 
    */
    public double getPriceChange() {
        return this.priceChange;
    }
    /**
     * gets the Price
     * @return  the Price as a double 
    */
    public double getPrice() {
        return this.price;
    }
    /**
     * sets the PreviousPrice
     * @param   PreviousPrice   the variable to set the PreviousPrice as
    */
    public void setPreviousPrice(double price) {
        this.previousPrice = price;
    }
    
    /**
     * sets the Price
     * @param   Price   the variable to set the Price as
    */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * sets the PercentChange
     * @param   PercentChange   the variable to set the PercentChange as
    */
    public void setPercentChange() {
        // [(Current Value - Beginning Value) / Beginning Value] * 100
        this.percentChange = 100*((this.price - this.previousPrice) / this.previousPrice);
    }
    
    /**
     * sets the PriceChange
     * @param   PriceChange the variable to set the PriceChange as
    */
    public void setPriceChange() {
        this.priceChange = this.price - this.previousPrice;
    }
    
    /**
     * sets the Currency
     * @param   Currency    the variable to set the Currency as
    */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    /**
     * sets the Exchange
     * @param   Exchange    the variable to set the Exchange as
    */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    
    /**
     * sets the Category
     * @param   Category    the variable to set the Category as
    */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Override method to display information about the object formatted
     * @return      the formatted information as a string
    */
    @Override
    public String toString() {
        // return formatted info about the stock
        return "===" + this.ticker + " " + this.company + "===" + "\n" + 
            "Price: $" + this.price + " " + this.currency +"\n" + 
            "Percent change: " + this.percentChange + "\n" +
            "Price change: " + this.priceChange + "\n" +
            "Stock Exchange: " + this.exchange + "\n" +
            "Category: " + this.category + "\n";
    }
}