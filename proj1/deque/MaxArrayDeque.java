package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (super.isEmpty()) {
            return null;
        }
        int size = super.size();
        T maxItem = super.get(0);
        for (int i = 1; i < size; i += 1) {
            T comparedItem = super.get(i);
            if (c.compare(comparedItem, maxItem) > 0) {
                maxItem = comparedItem;
            }
        }
        return maxItem;
    }

}
