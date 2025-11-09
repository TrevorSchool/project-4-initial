package com.example.iterable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Bag class.
 *
 * These tests cover:
 * - Normal operations (add, remove, contains, size, isEmpty)
 * - Edge cases (empty bag, null elements, removing non-existent items)
 * - Iterator functionality
 * - Behavior with single and multiple items
 */
public class BagTest {

    private Bag<String> bag;

    /**
     * Creates a fresh Bag instance before each test.
     */
    @BeforeEach
    void setUp() {
        bag = new Bag<>();
    }

    // ------------------------------------------------------------
    // Normal operations
    // ------------------------------------------------------------

    /**
     * Verifies that adding an item increases the size of the bag.
     */
    @Test
    void testAddIncreasesSize() {
        assertEquals(0, bag.size(), "New bag should start with size 0.");
        bag.add("apple");
        assertEquals(1, bag.size(), "Size should be 1 after adding one item.");
    }

    /**
     * Verifies that contains() returns true after an item is added.
     */
    @Test
    void testContainsAfterAdd() {
        bag.add("banana");
        assertTrue(bag.contains("banana"), "Bag should contain 'banana' after it is added.");
    }

    /**
     * Verifies that remove() returns true and actually removes the item.
     */
    @Test
    void testRemoveExistingItem() {
        bag.add("cherry");
        assertTrue(bag.remove("cherry"), "Removing an existing item should return true.");
        assertFalse(bag.contains("cherry"), "Item should no longer be in the bag after removal.");
    }

    /**
     * Verifies that the bag allows duplicate items and that remove()
     * only removes a single occurrence.
     */
    @Test
    void testMultipleIdenticalItems() {
        bag.add("x");
        bag.add("x");
        bag.add("x");

        assertEquals(3, bag.size(), "Bag should contain three items.");
        assertTrue(bag.remove("x"), "Removing 'x' should succeed.");
        assertEquals(2, bag.size(), "Only one 'x' should be removed.");
        assertTrue(bag.contains("x"), "At least one 'x' should remain.");
    }

    /**
     * Verifies that isEmpty() reflects the bag state correctly.
     */
    @Test
    void testIsEmptyChangesWithAdds() {
        assertTrue(bag.isEmpty(), "New bag should be empty.");
        bag.add("item");
        assertFalse(bag.isEmpty(), "Bag should not be empty after adding an item.");
    }

    // ------------------------------------------------------------
    // Single item / empty bag behavior
    // ------------------------------------------------------------

    /**
     * Verifies that a new bag is empty and has size 0.
     */
    @Test
    void testNewBagIsEmpty() {
        assertTrue(bag.isEmpty(), "New bag should be empty.");
        assertEquals(0, bag.size(), "New bag should have size 0.");
    }

    /**
     * Verifies the lifecycle of a single item: add, check, remove, empty again.
     */
    @Test
    void testSingleItemAddAndRemove() {
        bag.add("single");
        assertFalse(bag.isEmpty(), "Bag should not be empty after adding one item.");
        assertTrue(bag.contains("single"), "Bag should contain the added item.");

        assertTrue(bag.remove("single"), "Removing existing single item should return true.");
        assertTrue(bag.isEmpty(), "Bag should be empty again after removal.");
        assertEquals(0, bag.size(), "Size should be 0 after removing the only item.");
    }

    // ------------------------------------------------------------
    // Edge cases
    // ------------------------------------------------------------

    /**
     * Removing from an empty bag should return false, not throw.
     */
    @Test
    void testRemoveFromEmptyBag() {
        assertFalse(bag.remove("nothing"), "Removing from empty bag should return false.");
    }

    /**
     * contains() on an empty bag should always return false.
     */
    @Test
    void testContainsOnEmptyBag() {
        assertFalse(bag.contains("anything"), "Empty bag should not contain any items.");
    }

    /**
     * Verifies that adding a null item is allowed (since ArrayList allows null)
     * and that it can be queried and removed.
     */
    @Test
    void testNullItemBehavior() {
        bag.add(null);
        assertEquals(1, bag.size(), "Bag should contain one item after adding null.");
        assertTrue(bag.contains(null), "Bag should report containing null.");

        assertTrue(bag.remove(null), "Removing null should return true if present.");
        assertFalse(bag.contains(null), "Bag should no longer contain null after removal.");
    }

    /**
     * Verifies that removing a non-existing item from a non-empty bag returns false.
     */
    @Test
    void testRemoveNonExistingItemFromNonEmptyBag() {
        bag.add("exists");
        assertFalse(bag.remove("does-not-exist"),
                "Removing an item that isn't in the bag should return false.");
        assertTrue(bag.contains("exists"),
                "Existing items should remain untouched after failed removal.");
    }

    // ------------------------------------------------------------
    // Iterator functionality
    // ------------------------------------------------------------

    /**
     * Verifies that the iterator can traverse all items in the bag.
     */
    @Test
    void testIteratorTraversesAllItems() {
        bag.add("one");
        bag.add("two");
        bag.add("three");

        Iterator<String> it = bag.iterator();
        int count = 0;
        while (it.hasNext()) {
            String value = it.next();
            assertNotNull(value, "Values in this test should not be null.");
            count++;
        }

        assertEquals(3, count, "Iterator should visit exactly three items.");
    }

    /**
     * Verifies that an iterator on an empty bag has no elements.
     */
    @Test
    void testIteratorOnEmptyBagHasNoNext() {
        Iterator<String> it = bag.iterator();
        assertFalse(it.hasNext(), "Iterator over empty bag should have no next element.");
    }

    /**
     * Verifies that calling next() on an empty bag's iterator throws NoSuchElementException.
     * This is standard behavior for Java collections.
     */
    @Test
    void testIteratorNextOnEmptyBagThrows() {
        Iterator<String> it = bag.iterator();
        assertThrows(NoSuchElementException.class, it::next,
                "Calling next() when there are no elements should throw NoSuchElementException.");
    }

    /**
     * Verifies that iterator reflects current contents at the time it is created.
     */
    @Test
    void testIteratorSeesItemsPresentAtCreation() {
        bag.add("first");
        bag.add("second");

        Iterator<String> it = bag.iterator();
        assertTrue(it.hasNext(), "Iterator should see at least one element.");
        String first = it.next();
        assertTrue(first.equals("first") || first.equals("second"),
                "Iterator should return one of the items in the bag.");
    }
}
