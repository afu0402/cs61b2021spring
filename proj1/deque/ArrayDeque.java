package deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextPrev = 0;
    private int nextLast = 1;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(int numElement) {
        items = (T[]) new Object[numElement];
        size = 0;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextPrev] = item;
        if (nextPrev == 0) {
            nextPrev = items.length - 1;
        } else {
            nextPrev -= 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    public void resize(int opacity) {
        T[] newItems = (T[]) new Object[opacity];
        for (int i = 0; i < size; i += 1) {
            int p = (nextPrev += 1) % items.length;
            newItems[i] = items[p];
        }
        items = newItems;
        nextPrev = newItems.length - 1;
        nextLast = size;
    }


    public T get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        return items[(nextPrev + i + 1) % items.length];
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) return null;
        nextPrev = (nextPrev + 1) % items.length;
        T temp = items[nextPrev];
        items[nextPrev] = null;
        size -= 1;
        return temp;
    }

    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.println(get(i) + " ");
        }
    }

    public T removeLast() {
        if (size == 0) return null;
        nextLast = nextLast == 0 ? items.length - 1 : nextLast - 1;
        T temp = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return temp;
    }

}
