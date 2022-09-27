/**
 * This is part of Lec01_01: Environment Setup and Java Introduction.
 */
package lec01_01;

import java.awt.Color;

/**
 * This is a simple object that has a volume.
 */
// You may not make Ball implement the Comparable interface.
public class Ball {

    private double volume;
    private Color color;

    /**
     * Constructor that creates a new ball object with the specified volume and color.
     *
     * @param volume the volume of the new ball object
     * @param color  the color of the new ball object
     */
    public Ball(double volume, Color color) {
        this.volume = volume;
        this.color = color;
    }

    /**
     * Constructor that creates a new ball object with the specified volume given by a string.
     *
     * @param volume A string representing the volume of the new object.
     */
    public Ball(String volume, Color color) {
        try {
            this.volume = Double.parseDouble(volume);
        } catch (NumberFormatException e) {
            this.volume = 0;
        }
        this.color = color;
    }


    /**
     * Returns the volume of the ball.
     *
     * @return the volume of the ball.
     */
    public double getVolume() {
        return this.volume;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() == Ball.class) {
            Ball b = (Ball) o;
            return this.volume == b.volume && this.color.equals(b.color);
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(this.volume).hashCode() + this.color.hashCode();
    }

    public String toString() {
        return "Ball {volume = " + this.volume + "; color = " +
                this.color.toString() + "}";
    }


}


