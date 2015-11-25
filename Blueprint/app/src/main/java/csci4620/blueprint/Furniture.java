package csci4620.blueprint;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class Furniture {
    private String type;
    private String name;
    private double length;
    private double width;
    private double height;

    public Furniture(String type, String name, double length, double width, double height) {
        this.type = type;
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public double getLength() {
        return this.length;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
}
