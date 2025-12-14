package U2A3_EddieBian;
public class Rectangle extends Parallelogram {
    // note: extends from paralleogram since a rectangle is a parallelogram
    // therefore, area formula method + getDimensionsList is same

    /**
     * Constructor method for rectangle
     * @param   unitPrice   the price per unit of the shape
     * @param   width       the width of the shape
     * @param   height      the height of shape
    */
    public Rectangle(double unitPrice, double width, double height) {
        super(unitPrice, width, height); // from the parent class
        // note: width and height are used for getDimensionsList method
    }

    /**
     * Returns specific information about the rectangle
     * @return  formatted information about shape as string
    */
    @Override
    public String toString() {
        // dimensionslist = {width, height} -> index0 = width, index1 = height
        return "Rectangle:\nUnit price: $" + unitPrice
            + "\nWidth: " + getDimensionsList()[0] + "m\nHeight: "
            + getDimensionsList()[1] + "m\nArea: " + getArea() + "m²";
    }
}