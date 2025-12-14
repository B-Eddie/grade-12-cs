package U2A3_EddieBian;
public class Donut extends Shape {
    /**
     * Constructor method for shape
     * @param   unitPrice       the price per unit of the shape
     * @param   outerRadius     the outer radius of shape
     * @param   innerRadius     the inner radius of shape
    */
    public Donut(double unitPrice, double outerRadius, double innerRadius) {
        this.unitPrice = unitPrice;
        // index 0 = outer radius, index 1 = inner radius
        this.dimensionsList = new double[] { outerRadius, innerRadius };
    }

    /**
     * Calculates area of shape
     * @return  the area of the shape as a double
    */
    @Override
    public double getArea() {
        // formula: π(R² - r²)     dimensionsList = { outer radius, inner radius }
        return Math.PI * (Math.pow(getDimensionsList()[0], 2) - Math.pow(getDimensionsList()[1], 2));
    }

    /**
     * gets all the dimensions of the shape
     * @return  returns the dimensions as a double array
    */
    @Override
    public double[] getDimensionsList() {
        // returns dimensions array
        return dimensionsList;
    }

    /**
     * Returns information about the shape
     * @return  formatted information about shape as string
    */
    @Override
    public String toString() {
        // dimensionslist = {outer radius, inner radius} -> index0 = outer radius, index1=inner radius
        return "Donut:\nUnit price: $"
            + unitPrice + "\nOuter radius: " + getDimensionsList()[0]
            + "m\nInner radius: " + getDimensionsList()[1] + "m\nArea: "
            + getArea() + "m²";
    }
}