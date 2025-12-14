package U2A3_EddieBian;
public class Circle extends Shape {
    /**
     * Constructor method for shape
     * @param   unitPrice       the price per unit of the shape
     * @param   radius          the radius of the circle
    */
    public Circle(double unitPrice, double radius) {
        this.unitPrice = unitPrice;
        // index 0 = radius (only 1 index since a circle only needs one variable to calculate area)
        this.dimensionsList = new double[] { radius };
    }

    /**
     * Calculates area of shape
     * @return  the area of the shape as a double
    */
    @Override
    public double getArea() {
        // formula: PIr^2    the dimensionslist only has one value (radius)
        return Math.PI * Math.pow(getDimensionsList()[0], 2);
    }

    /**
     * gets all the dimensions of the shape
     * @return  returns the dimensions as a double array
    */
    @Override
    public double[] getDimensionsList() {
        // dimensionslist = radius
        return dimensionsList;
    }

    /**
     * Returns information about the shape
     * @return  formatted information about shape as string
    */
    @Override
    public String toString() {
        // dimensionslist = {radius} -> index0 = radius
        return "Circle:\nUnit price: $" + unitPrice + "\nRadius: "
            + getDimensionsList()[0] + "m\nArea: "
            + getArea() + "m²";
    }
}