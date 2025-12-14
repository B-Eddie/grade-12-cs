package U2A1_EddieBian;
/**
 * Vehicle class with passenger, fare, fuel efficiency
 * Provides methods to calculate revenue, cost, profit, etc.
*/
public class Vehicle {
    // instance variables
    private int passengerNum; // number of passengers in vehicle
    private double passengerFare; // cost per passenger
    private double fuelEfficiency; // liters / km

    // class fields (need to set their value..?)
    public static double gasPrice; // the gas price same for all vehicles
    public static final int PROFIT = 400; // profit constant - 400

    // constructor
    /**
     * Constructor for vehicle class
     * @param   passengerNum      number of passengers
     * @param   passengerFare     fare charged per passenger
     * @param   fuelEfficiency    fuel efficiency
    */
    public Vehicle(int passengerNum, double passengerFare, double fuelEfficiency) {
        // sets parameters to the variables
        this.passengerNum = passengerNum;
        this.passengerFare = passengerFare;
        this.fuelEfficiency = fuelEfficiency;
    }

    // =====GETTERS=====

    /**
     * Calculates and returns the revenue
     * @return  total revenue from passengers
    */
    public double revenue() {
        // calculates money made
        return passengerNum * passengerFare;
    }

    /**
     * Total fuel cost of trip for given distance
     * @param   distance    distance travelled (km)
     * @return              the total cost
    */
    public double totalCost(int distance) {
        // returns total cost
        return gasPrice * distance * fuelEfficiency;
    }

    /**
     * Calculates and returns the profit of trip
     * @param   distance    traveled distnace
     * @return              the profit (revenue minus total cost)
    */
    public double calculateProfit(int distance) {
        // returns total profit
        return revenue() - totalCost(distance);
    }

    // check if profit is bigger than constant
    /**
     * Checks if the trip is profitable
     * @param   distance    total distance travelled
     * @return              returns true if profit is bigger than constant, false otherwise
    */
    public boolean isProfitable(int distance) {
        // checks if the profit is bigger than the constant
        if (calculateProfit(distance) > PROFIT) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Compares two vehicles and returns the one with higher profit
     * @param   vehicleOne    first vehicle object to compare
     * @param   vehicleTwo    second vehicle object to compare
     * @param   distance      total distance travelled (km)
     * @return                the vehicle with higher profit
    */
    public static Vehicle compareTo(Vehicle vehicleOne, Vehicle vehicleTwo, int distance) {
        // compares two vehicles using calculateProfit() method
        if (vehicleOne.calculateProfit(distance) > vehicleTwo.calculateProfit(distance)) {
            return vehicleOne;
        } else {
            return vehicleTwo;
        }
    }

    // ======SETTER=====
    /**
     * Sets the class gas price
     * @param   price   price of gas
    */
    public static void setGasPrice(double price) {
        gasPrice = price;
    }

    /**
     * Returns all variables of vehicle as a string, including # of passengers, fare, efficiency
     * @return  formatted vehicle details as string
    */
    @Override
    public String toString() {
        return "Vehicle with " + passengerNum + " passengers, $" + passengerFare + " fare, " + fuelEfficiency + "L/km fuel efficiency";
    }
}