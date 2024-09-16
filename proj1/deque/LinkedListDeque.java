package deque;

import com.sun.tools.doclets.internal.toolkit.util.links.LinkInfo;

import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    private ListNode node;
    private ListNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ListNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new ListNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i += 1) {
            addLast(other.get(i));
        }
    }

    private class ListNode {
        public ListNode prev;
        T item;
        public ListNode next;

        public ListNode(T value) {
            item = value;
        }
    }

    public void addFirst(T item) {
        ListNode node = new ListNode(item);
        ListNode firstNode = sentinel.next;
        node.next = firstNode;
        node.prev = sentinel;
        sentinel.next = node;
        firstNode.prev = node;
        size += 1;
    }

    public void addLast(T item) {
        ListNode newNode = new ListNode(item);
        ListNode lastNode = sentinel.prev;
        newNode.prev = lastNode;
        newNode.next = sentinel;
        lastNode.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public void printDeque() {
        ListNode p = sentinel.next;
        String s = "";
        while (p != sentinel) {
            if (s.isEmpty()) {
                s += p.item;
            } else {
                s += " " + p.item;
            }
            p = p.next;
        }
        System.out.println(s);
    }

    public int size() {
        return size;
    }


    public T removeFirst() {
        ListNode firstItem = sentinel.next;
        if (firstItem != sentinel) {
            ListNode secNode = firstItem.next;
            sentinel.next = secNode;
            secNode.prev = sentinel;
            firstItem.prev = null;
            firstItem.next = null;
            size -= 1;
            return firstItem.item;
        }
        return null;
    }

    public T removeLast() {
        ListNode lastNode = sentinel.prev;
        if (lastNode != sentinel) {
            ListNode secToLate = lastNode.prev;
            secToLate.next = sentinel;
            sentinel.prev = secToLate;
            lastNode.prev = null;
            lastNode.next = null;
            size -= 1;
            return lastNode.item;
        }
        return null;
    }

    public T get(int index) {
        ListNode p = sentinel.next;
        if (index < 0 || index >= size) {
            return null;
        }
        while (index > 0 && p != null) {
            index -= 1;
            p = p.next;
        }
        if (p != sentinel) {
            return p.item;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, ListNode p) {
        if (p == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }
}
