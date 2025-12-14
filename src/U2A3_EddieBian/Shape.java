package U2A3_EddieBian;
public abstract class Shape {
    // parent class for all the other shapes

    // protected attributes - used by children
    protected double unitPrice; // price per unit
    protected double[] dimensionsList; // list for dimensions of shape

    // abstract methods to be implemented by children
    public abstract double getArea();
    public abstract double[] getDimensionsList();

    /**
     * Get unit price
     * @return  the unit price
    */
    public double getUnitPrice() {
        return unitPrice;
    }
}