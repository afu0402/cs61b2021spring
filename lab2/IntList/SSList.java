package IntList;

public class SSList {

    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int f, IntNode n) {
            item = f;
            next = n;
        }

        public String toString() {
            if (next == null) {
                return "" + item;
            }
            return item + "->" + next.toString();
        }
    }

    private IntNode sentinel;
    private int size;

    public SSList() {
        size = 0;
        sentinel = new IntNode(12, null);
    }

    public SSList(int x) {
        sentinel = new IntNode(12, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public void addLast(int x) {
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size += 1;
    }

    public String toString() {
        IntNode p = sentinel;
        if (p.next == null) {
            return "";
        }
        return p.next.toString();
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        SSList s = new SSList(100);
        s.addFirst(10);
        s.addFirst(15);
        s.addLast(33);
        SSList empty = new SSList();

        System.out.println(empty);
        System.out.println(empty.getSize());
        System.out.println(s);
        System.out.println(s.getSize());

    }

}
