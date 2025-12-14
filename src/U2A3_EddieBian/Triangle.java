package U2A3_EddieBian;
public class Triangle extends Shape {
    /**
     * Constructor method for triangle
     * @param   unitPrice   the price per unit of the triangle shape
     * @param   width       the width of the triangle
     * @param   height      the height of triangle
    */
    public Triangle(double unitPrice, double width, double height) {
        this.unitPrice = unitPrice;
        // index 0 = width, index 1 = height
        this.dimensionsList = new double[] { width, height };
    }

    /**
     * Calculates area of triangle
     * @return  the area of the triangle as a double
    */
    @Override
    public double getArea() {
        // dimensionslist = {width, height} so it returns width times height divided by 2
        return (getDimensionsList()[0] * getDimensionsList()[1]) / 2;
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
     * Returns information about the shape
     * @return  formatted information about shape as string
    */
    @Override
    public String toString() {
        // dimensionslist = {width, height} -> index0 = width, index1 = height
        return "Triangle:\nUnit price: $" + unitPrice
            + "\nWidth: " + getDimensionsList()[0] + "m\nHeight: "
            + getDimensionsList()[1] + "m\nArea: " + getArea() + "m²";
    }
}