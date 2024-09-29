package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class BSTMap<Key extends Comparable<Key>, V> implements Map61B<Key, V> {
    private class Node {
        private Node left;
        private Node right;
        private Key key;
        private V val;
        private int size = 0;
        public Node(Key key, V val,int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    private Node root = null;
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(Key key) {
        return containsKey(root,key);
    }
    public boolean containsKey(Node x,Key key) {
        if (x == null) return false;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return containsKey(x.left,key);
        } else if (cmp > 0) {
            return containsKey(x.right,key);
        }
        return true;
    }

    @Override
    public V get(Key key) {
        return get(root,key);
    }
    private V get(Node x,Key key)  {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left,key);
        } else if (cmp > 0) {
            return get(x.right,key);
        }
        return x.val;
    }

    @Override
    public int size() {
        return size(root);
    }
    public int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    @Override
    public void put(Key key, V val) {
        root = put(root,key,val);
    }

    public Node put(Node x,Key key, V val) {
        if (x == null) return new Node(key,val,1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left,key,val);
        } else if (cmp > 0) {
            x.right = put(x.right,key,val);
        } else  {
            x.val = val;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    @Override
    public Set keySet() {
        Set<Key> a = new HashSet<>();
        keySet(a,root);
        return a;
    }
    private void keySet(Set s,Node x) {
        if (x == null) return;
        s.add(x.key);
        keySet(s,x.left);
        keySet(s,x.right);
    }

    @Override
    public V remove(Key key) {
        V targetV = get(key);
        if (targetV != null) {
            root = remove(root,key);
        }
        return targetV;
    }
    private Node remove(Node x,Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left,key);
        } else if (cmp > 0) {
            x.right = remove(x.right,key);
        } else  {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = remove(t.right,x.key);
            x.left = t.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    private Node min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x;
        return min(x.left);
    }
    @Override
    public V remove(Key key, V value) {
        V targetV = get(key);
        if (targetV != null) {
            if (targetV.equals(value)) {
                root = remove(root,key);
            }
        }
        return targetV;
    }

    public void printMapInOrder() {
       printMapInOrder(root);
    }
    public void printMapInOrder(Node x) {
       if (x == null) return;
       printMapInOrder(x.left);
        System.out.println(x.key + ": " + x.val);
        printMapInOrder(x.right);
    }
    @Override
    public Iterator<Key> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 10; i++) {
            b.put("hi" + i, 1+i);
        }
        for(String k : b) {
            System.out.println(k);
        }
    }
}
