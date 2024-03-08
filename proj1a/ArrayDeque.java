public class ArrayDeque<T> {
    private T[] array;
    private int first;
    private int last;
    private int size;
    private int capacity;
    public ArrayDeque(){
        array = (T [])new Object[8];
        first = 0;
        last = 7;
        size = 0;
        capacity = 8;
    }
    public void addFirst(T item){
        if(size == capacity){
            T[] newArray = (T[]) new Object[capacity*2];
            System.arraycopy(array,0,newArray,0,first);
            System.arraycopy(array,first,newArray,capacity + first,capacity - first);
            array = newArray;
            capacity *= 2;
            last = capacity + last;
        }
        array[(first + 1) % capacity] = item;
        size++;
    }

    public void addLast(T item){
        if(size == capacity){
            T[] newArray = (T[]) new Object[capacity*2];
            System.arraycopy(array,0,newArray,0,first);
            System.arraycopy(array,first,newArray,capacity + first,capacity - first);
            array = newArray;
            capacity *= 2;
            last = capacity + last;
        }
        array[(last - 1) % capacity] = item;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){

    }

    public T removeFirst(){
        if (size != 0) {
            T rm = array[first];
            first = (first - 1 + capacity)%capacity;
            size--;
            return rm;
        } else {
            return null;
        }
    }
    public T removeLast(){
        if (size != 0){
            T rm = array[last];
            last = (last + 1 + capacity)%capacity;
            size--;
            return rm;
        } else {
            return null;
        }
    }

    public T get(int index){
        if(index > capacity || index < 0)
            return null;
        else if (index > last || index < first)
            return array[index];
        else
            return null;
    }
}
