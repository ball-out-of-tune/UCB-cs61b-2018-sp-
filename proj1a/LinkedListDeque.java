public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;
    private class TNode {
        TNode prev;
        T item;
        TNode next;
        private TNode() { };
        private TNode(T someItem) {
            item = someItem;
        }
    }
    public LinkedListDeque() {
        sentinel = new TNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        sentinel.item = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public void addFirst(T item) {
        TNode newNode = new TNode(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
//        sentinel.prev = newNode;
        if (isEmpty()) {
            sentinel.prev = newNode;
        }
        size++;
    }

    public void addLast(T item) {
        TNode newNode = new TNode(item);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        if (isEmpty()) {
            sentinel.next = newNode;
        }
        size++;
    }

    public void printDeque() {
        TNode p = sentinel;
        while (p.next != sentinel) {
            if (sentinel.item == null) {
                return;
            }
            System.out.print(sentinel.item.toString() + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T firstNodeItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return firstNodeItem;
    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T prevNodeItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return prevNodeItem;
    }
    public int size() {
        return size;
    }

    public T get(int index) {
        TNode p = sentinel;
        if (index > size / 2) {
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            for (int i = 0; i < size - index + 1; i++) {
                p = p.prev;
            }
        }
        return p.next.item;
    }

    private T getRecursiveHelper(TNode p, int index) {
        if (index == 0) {
            return p.next.item;
        } else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel, index);
    }
}
