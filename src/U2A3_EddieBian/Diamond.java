package U2A3_EddieBian;
public class Diamond extends Shape {
    /**
     * Constructor method for shape
     * @param   unitPrice       the price per unit of the shape
     * @param   diagonalOne     the first diagonal
     * @param   diagonalTwo     second diagonal
    */
    public Diamond(double unitPrice, double diagonalOne, double diagonalTwo) {
        this.unitPrice = unitPrice;
        // index 0 = first diagonal, index 1 = second diagonal
        this.dimensionsList = new double[] { diagonalOne, diagonalTwo };
    }

    /**
     * Calculates area of shape
     * @return  the area of the shape as a double
    */
    @Override
    public double getArea() {
        // dimensionslist = { first diagonal (a), second diagonal (b) }
        // formula: (a * b) / 2
        return (getDimensionsList()[0] * getDimensionsList()[1]) / 2;
    }

    /**
     * gets all the dimensions of the shape
     * @return  returns the dimensions as a double array
    */
    @Override
    public double[] getDimensionsList() {
        // returns diagonal array
        return dimensionsList;
    }

    /**
     * Returns information about the shape
     * @return  formatted information about shape as string
    */
    @Override
    public String toString() {
        // dimensionslist = {diagonalOne, diagonalTwo} -> index0 = first diagonal, index1 = second diagonal
        return "Diamond:\nUnit price: $" + unitPrice + "\nDiagonal 1: "
            + getDimensionsList()[0]  + "m\nDiagonal 2: "
            + getDimensionsList()[1] + "m\nArea: " + getArea() + "m²";
    }
}