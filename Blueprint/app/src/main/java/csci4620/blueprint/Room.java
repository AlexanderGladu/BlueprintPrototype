package csci4620.blueprint;

import java.io.Serializable;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class Room implements Serializable {
    private String name;
    private double length;
    private double width;
    private double height;

    public Room(String name, double length, double width, double height) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
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
