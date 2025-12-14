package U2A3_EddieBian;
public class Parallelogram extends Shape {

    /**
     * Constructor method for parallelogram
     * @param   unitPrice   the price per unit of the shape
     * @param   width       the width of the shape
     * @param   height      the height of shape
    */
    public Parallelogram(double unitPrice, double width, double height) {
        this.unitPrice = unitPrice;
        // index 0 = width, index 1 = height
        this.dimensionsList = new double[] { width, height };
    }

    /**
     * Calculates area of shape
     * @return  the area of the shape as a double
    */
    @Override
    public double getArea() {
        // dimensionslist = {width, height} so it returns width times height
        return getDimensionsList()[0] * getDimensionsList()[1];
    }

    /**
     * gets all the dimensions of the shape
     * @return  returns the dimensions as a double array
    */
    @Override
    public double[] getDimensionsList() {
        // returns list
        return dimensionsList;
    }

    /**
     * Returns specific information about the parallelogram
     * @return  formatted information about shape as string
    */
    @Override
    public String toString() {
        // dimensionslist = {width, height} -> index0 = width, index1 = height
        return "Parallelogram:\nUnit price: $"
            + unitPrice + "\nWidth: " + getDimensionsList()[0]
            + "m\nHeight: " + getDimensionsList()[1] + "m\nArea: "
            + getArea() + "m²";
    }
}