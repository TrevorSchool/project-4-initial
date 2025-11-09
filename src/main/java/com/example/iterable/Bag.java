package com.example.iterable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Bag class is a generic container that stores elements
 * without enforcing uniqueness or order. It uses an ArrayList
 * internally to manage the elements.
 *
 * @param <E> the type of elements in this bag
 */
public class Bag<E> implements Container<E> {

    // Backing data structure to store elements
    private ArrayList<E> items;



    /**
     * Constructs an empty Bag.
     */
    public Bag() {
        items = new ArrayList<>();
    }



    /**
     * Adds an item to the bag.
     *
     * @param item the element to be added
     */
    @Override
    public void add(E item) {
        items.add(item);
    }



    /**
     * Removes a single occurrence of the specified item from the bag,
     * if it exists.
     *
     * @param item the element to remove
     * @return true if the item was found and removed, false otherwise
     */
    @Override
    public boolean remove(E item) {
        return items.remove(item);
    }



    /**
     * Checks whether the bag contains the specified item.
     *
     * @param item the element to check for
     * @return true if the bag contains the item, false otherwise
     */
    @Override
    public boolean contains(E item) {
        return items.contains(item);
    }



    /**
     * Returns the number of elements currently in the bag.
     *
     * @return the number of items
     */
    @Override
    public int size() {
        return items.size();
    }



    /**
     * Checks whether the bag is empty.
     *
     * @return true if the bag contains no items, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }



    /**
     * Returns an iterator over the elements in this bag.
     * This allows for-each loop iteration.
     *
     * @return an iterator for the bag's items
     */
    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }



    /**
     * Returns a string representation of the bag and its contents.
     *
     * @return a string listing all elements in the bag
     */
    @Override
    public String toString() {
        return "Bag" + items.toString();
    }
}
