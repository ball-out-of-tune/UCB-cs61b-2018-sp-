public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;
    public class TNode{
        TNode prev;
        T item;
        TNode next;
        public TNode(){};
        public TNode(T Item){
            item = Item;
        }
    }
    public LinkedListDeque(){
        sentinel = new TNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        sentinel.item = null;
        size = 0;
    }

    public boolean isEmpty(){
        if (size == 0)
            return true;
        else
            return false;
    }
    public void addFirst(T item){
        TNode newNode = new TNode(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next = newNode;
//        sentinel.prev = newNode;
        if (isEmpty() == true)
            sentinel.prev = newNode;
        size++;
    }

    public void addLast(T item){
        TNode newNode = new TNode(item);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev = newNode;
        if (isEmpty() == true)
            sentinel.next = newNode;
        size++;
    }

    public void printDeque(){
        TNode p = sentinel;
        while (p.next != sentinel){
            if (sentinel.item == null)
                return;
            System.out.print(sentinel.item.toString() + " ");
            p = p.next;
        }
    }

    public T removeFirst(){
        if(sentinel.next == null){
            return null;
        }
        T firstNodeItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return firstNodeItem;
    }

    public T removeLast(){
        if(sentinel.prev == null){
            return null;
        }
        T prevNodeItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return prevNodeItem;
    }
    public int size(){
        return size;
    }

    public T get(int index){
        TNode p = sentinel;
        if(index > size/2){
            for(int i = 0; i < index; i++)
                p = p.next;
        } else {
            for(int i = 0; i < size - index + 1; i++)
                p = p.prev;
        }
        return p.item;
    }

    public T getRecursiveHelper(TNode p, int index){
        if (index == 1){
            return p.item;
        } else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }
    public T getRecursive(int index){
        return getRecursiveHelper(sentinel, index);
    }

}
