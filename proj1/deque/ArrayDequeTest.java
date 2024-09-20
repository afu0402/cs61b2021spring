package deque;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized ArrayDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<String>();
        ArrayDeque<Double> lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 9; i++) {
            lld1.addLast(i);
        }
        assertEquals("the capacity should be 16", 16, (double) lld1.getCapacity(), 0.0);
        for (int i = 0; i < 5; i++) {
            lld1.removeLast();
        }
        assertEquals("the capacity should be 8", 8, (double) lld1.getCapacity(), 0.0);

    }

    @Test
    public void testEequals() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        LinkedListDeque<Integer> l2 = new LinkedListDeque<>();
        LinkedListDeque<String> l3 = new LinkedListDeque<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        l2.addFirst(1);
        l2.addFirst(2);
        l3.addFirst("a");
        l3.addFirst("b");
        assertEquals("lld1 should equals to l2", true, lld1.equals(l2));
        assertEquals("lld1 should not equals to l3", false, lld1.equals(l3));

    }
    @Test
    public void testRandomCall() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();

        a.addFirst(0);
        a.addLast(1);
        assertEquals("should be 1",1, (int)a.removeLast());
        assertEquals("should be 0",0, (int)a.removeFirst());
        a.addLast(4);
        a.addFirst(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        assertEquals("should be 8",8, (int)a.removeLast());
        a.addLast(10);
        assertEquals("should be 0",10, (int)a.removeLast());
        a.addLast(12);
        assertEquals("should be 5",5, (int)a.removeFirst());
        assertEquals("should be 12",12, (int)a.removeLast());
        assertEquals("should be 4",4, (int)a.get(0));
        int size = a.size();
        for (int i = 0; i < size; i += 1) {
            if (i % 2 == 0) {
                a.addFirst(i);
            } else {
                a.addLast(i);
            }
        }
        size = a.size();
        for (int i = 0; i < size; i += 1) {
            a.removeLast();
        }
        assertTrue("should be empty", a.isEmpty());

    }
}
