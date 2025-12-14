package U2A3_EddieBian;
public class Trapezoid extends Shape {
    /**
     * Constructor method for shape
     * @param   unitPrice       the price per unit of the shape
     * @param   baseOne         the top base of the trapezoid
     * @param   baseTwo         the bottom base of trapezoid
     * @param   height          the trapezoid's height
    */
    public Trapezoid(double unitPrice, double baseOne, double baseTwo, double height) {
        this.unitPrice = unitPrice;
        // index 0 = bottom base, index 1 = top base, index 2 = height
        this.dimensionsList = new double[] { baseOne, baseTwo, height };
    }

    /**
     * Calculates area of shape
     * @return  the area of the shape as a double
    */
    @Override
    public double getArea() {
        // dimensionslist = { base1 (a), base2 (b), height }
        // formula: ((a+b) / 2) * h
        return ((getDimensionsList()[0] + getDimensionsList()[1]) / 2) * getDimensionsList()[2];
    }

    /**
     * gets all the dimensions of the shape
     * @return  returns the dimensions as a double array
    */
    @Override
    public double[] getDimensionsList() {
        // returns dimension array
        return dimensionsList;
    }

    /**
     * Returns information about the shape
     * @return  formatted information about shape as string
    */
    @Override
    public String toString() {
        // dimensionslist = {base1, base2, height}
        // dimensionslist = {base1, base2, height} -> index0=first base, index1=second base, index2=height
        return "Trapezoid:\nUnit price: $" + unitPrice + "\nBase 1: "
            + getDimensionsList()[0]  + "m\nBase 2: " + getDimensionsList()[1]
            + "m\nHeight: " + getDimensionsList()[2] + "m\nArea: "
            + getArea() + "m²";
    }
}