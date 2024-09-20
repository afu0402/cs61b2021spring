package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextPrev = 0;
    private int nextLast = 1;
    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        ArrayDequeIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return  pos < size;
        }

        @Override
        public T next() {
            T item = get(pos);
            pos += 1;
            return item;
        }
    }
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(int numElement) {
        items = (T[]) new Object[numElement];
        size = 0;
    }
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
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

    @Override
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


    @Override
    public T get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        return items[(nextPrev + i + 1) % items.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        nextPrev = (nextPrev + 1) % items.length;
        T temp = items[nextPrev];
        items[nextPrev] = null;
        size -= 1;
        return temp;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.println(get(i) + " ");
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        nextLast = nextLast == 0 ? items.length - 1 : nextLast - 1;
        T temp = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return temp;
    }


}
