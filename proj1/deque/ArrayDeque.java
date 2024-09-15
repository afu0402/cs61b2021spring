package deque;

import java.util.Arrays;

public class ArrayDeque<T> {
    T[] items;
    int size;
    int nextPrev = 0;
    int nextLast = 1;
    ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }
    ArrayDeque(int numElement) {
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
       for (int i = 0; i < size;i += 1) {
           int p = (nextPrev += 1) % items.length;
           newItems[i] = items[p];
       }
       items = newItems;
       nextPrev = newItems.length - 1;
       nextLast = size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int i) {
        return items[i];
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
        System.out.println(Arrays.toString(items));
    }
    public T removeLast() {
        if (size == 0) return null;
        nextLast = nextLast == 0 ? items.length - 1 : nextLast - 1;
        T temp = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return temp;
    }

    public static void main(String[] args) {
        ArrayDeque<String> a = new ArrayDeque<>(4);
        a.addFirst("a");
        a.addLast("1");
        a.addFirst("c");
        a.addLast("2");
        a.addLast("3");
        a.addLast("4");
        a.printDeque();
        int size = a.size();
        for (int i = 0;i < size;i += 1) {
            System.out.println(a.removeLast());
        }
        System.out.println("size："+ a.size());
    }
}
