/**
 * This is part of Lec01_01: Environment Setup and Java Introduction.
 */
package lec01_01;

import java.awt.Color;
import java.lang.Iterable;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Collections;
import java.util.HashSet;

/**
 * This is a container can be used to contain balls.
 * A given ball may only appear in a BallContainer once.
 */
public class BallContainer implements Iterable<Ball> {

    // Contents of the BallContainer.
    private Set<Ball> contents;

    /**
     * Constructor that creates a new ball container.
     */
    public BallContainer() {
        // Only works when Ball uses reference equality (equals() is not overridden)
        // contents = new LinkedHashSet<Ball>();
//        contents = new LinkedHashSet<Ball>();

        contents = new TreeSet<Ball>((e1, e2) -> {
            if (e1.getVolume() - e2.getVolume() == 0) {
                // If two balls have the same volume we first check if they are the same object
                //   because we need to implement reference equality, not the value equality.
                // If two objects are the same, they are equal, so return 0.
                // Otherwise, they are not equal, and we don't care in which order they come in
                //   the ordering. So, we return either -1 or 1.
                if (e1 == e2) return 0;
                else return -1;
            }
            else if (e1.getVolume() - e2.getVolume() < 0) return -1;
            else return 1;
        });
    }

    /**
     * Implements the Iterable interface for this container.
     * @return an Iterator over the Ball objects contained
     * in this container
     */
    public Iterator<Ball> iterator() {
        // If we just returned the iterator of "contents", a client
        // could call the remove() method on the iterator and modify
        // it behind our backs.  Instead, we wrap contents in an
        // "unmodifiable set"; calling remove() on this iterator
        // throws an exception.  This is an example of avoiding
        // "representation exposure."  You will learn more about this
        // concept later in the course.
        return Collections.unmodifiableSet(contents).iterator();
    }

    /**
     * Adds a ball to the container. This method returns <tt>true</tt>
     * if the ball was successfully added to the container, i.e., the ball was
     * not already in the container. Of course, you are allowed to put
     * a Ball into a container only once. Hence, this method returns
     * <tt>false</tt>, if the ball is already in the container.
     * @param b ball to be added
     * @return true if the ball was successfully added to the container,
     * i.e., the ball was not already in the container. Returns false, if the ball is
     * already in the container
     */
    public boolean add(Ball b) {
        boolean result;

        if(!this.contents.contains(b)){
            result=true;
            this.contents.add(b);
        }
        else {result=false;}

        return result;
    }

    /**
     * Removes a ball from the container. This method returns
     * <tt>true</tt> if the ball was successfully removed from the
     * container, i.e., the ball was actually in the container. You cannot
     * remove a ball if it is not already in the container, therefore in this
     * case the method returns <tt>false</tt>.
     * @param b ball to be removed
     * @return true if the ball was successfully removed from the container,
     * i.e., the ball was actually in the container. Returns false, if the ball
     * is not in the container
     */
    public boolean remove(Ball b) {
        boolean result;

        if(this.contents.contains(b)){
            result=true;
            this.contents.remove(b);
        }
        else {result=false;}

        return result;
    }

    /**
     * Each ball has a volume. This method returns the total volume of
     * all balls in the container.
     * @return the volume of the contents of the container
     */
    public double getVolume() {
        double result = 0;

        for(Ball b:contents){
            result+=b.getVolume();
        }
        return result;
    }

    /**
     * Returns the number of balls in this container.
     * @return the number of balls in this container
     */
    public int size() {
        return this.contents.size();

    }

    /**
     * Returns the number of different colors for the balls in this container.
     * @return the number of different colors for the balls in this container
     */
    public int differentColors() {
        Set<Color> colColor=new HashSet<>();
        for(Ball b : this.contents){
            colColor.add(b.getColor());
        }
        return colColor.size();
    }


    /**
     * Returns true if all balls in this container have the same color,
     *   otherwise returns false.
     * @return true if all balls in this container have the same color,
     *   otherwise returns false
     */
    public boolean areSameColor() {


        boolean result=false;
        Set<Color> colColor=new HashSet<>();
        for(Ball b : this.contents){
            colColor.add(b.getColor());
        }
        if((colColor.size()==1) || (this.contents.size()==0)){result=true;}

        return result;

    }

    /**
     * Empties the container, i.e., removes all its contents.
     */
    public void clear() {
        this.contents.clear();
    }

    /**
     * This method returns <tt>true</tt> if this container contains
     * the specified ball. It will return <tt>false</tt> otherwise.
     * @param b ball to be checked if it is in the container
     * @return true if this container contains the specified ball. Returns
     * false otherwise.
     */
    public boolean contains(Ball b) {
        return this.contents.contains(b);
    }

}
