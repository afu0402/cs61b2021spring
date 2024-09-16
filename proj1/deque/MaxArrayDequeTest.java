package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class MaxArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void testMax() {

        MaxArrayDeque<Integer> a = new MaxArrayDeque<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return  t1 - t2;
            }
        });
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(4,(int)a.max());
        MaxArrayDeque<String> b = new MaxArrayDeque<>(new Comparator<String>() {
            @Override
            public int compare(String t1, String t2) {
                return  t1.length() - t2.length();
            }
        });
        b.addFirst("ababsdfj");
        b.addLast("acf");
        b.addLast("aslfasf");
        b.addLast("lajfsksafsfsafsfs");
        assertEquals("lajfsksafsfsafsfs",(String) b.max());
    }

}
