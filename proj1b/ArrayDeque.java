/**
 * ArrayDeque
 * implemented in circular way
 * @author Ch1zzZ
 */

public class ArrayDeque<Type> implements Deque<Type>{
    private Type[] Types;
    private int first;
    private int last;
    private int size;

    /** Create an empty list. */
    public ArrayDeque(){
        Types = (Type[]) new Object[8];
        first = 0;
        last = 1;
        size = 0;
    }

    /** Return the size of the ArrayDeque. */
    @Override
    public int size(){
        return size;
    }

    /** return true if deque is full. */
    public boolean isFull(){
        return size == Types.length;
    }

    /** return x - 1 circularly. */
    public int minusOne(int x){
        return (x - 1 + Types.length) % Types.length;
    }

    /** return x + 1 circularly. */
    public int plusOne(int x){
        return (x + 1 + Types.length) % Types.length;
    }

    /** Resize the deque. */
    public void resize(int capacity){
        Type[] newTypes =  (Type[]) new Object[capacity];
        int start = plusOne(first);
        for (int i = 0;i<size;i++){
            newTypes[i] = Types[start];
            start = plusOne(start);
        }
        Types = newTypes;
        first = capacity - 1;
        last = size;
    }

    /** Return true if the deque is sparse. */
    public boolean isSparse(){
        return Types.length>16 && (size/Types.length)<0.25;
    }

    /** Adds an Type to the front of the deque. */
    @Override
    public void addFirst(Type x){
        if (size == Types.length){
            this.resize(2 * size);
        }
        Types[first] = x;
        first = minusOne(first);
        size += 1;
    }

    /** Adds an Type to the front of the deque. */
    @Override
    public void addLast(Type x){
        if (size == Types.length){
            this.resize(2 * size);
        }
        Types[last] = x;
        last = plusOne(last);
        size += 1;
    }

    /** Prints the Types in the deque from first to last. */
    @Override
    public void printDeque(){
        int start = plusOne(first);
        for (int i = 0; i<size; i++){
            System.out.print(Types[start] + " ");
            start = plusOne(start);
        }
    }

    /** Removes and returns the Type at the front of the deque. */
    @Override
    public Type removeFirst(){
        if (this.isEmpty()){
            return null;
        } else {
            if (this.isSparse()){
                this.resize(size/2);
            }
            first = plusOne(first);
            Type x = Types[first];
            Types[first] = null;
            size -= 1;
            return x;
        }
    }

    /** Removes and returns the Type at the back of the deque. */
    @Override
    public Type removeLast(){
        if (this.isEmpty()){
            return null;
        } else {
            if (this.isSparse()){
                this.resize(size/2);
            }
            last = minusOne(last);
            Type x = Types[last];
            Types[last] = null;
            size -= 1;
            return x;
        }
    }

    /** Gets the Type at the given index. */
    @Override
    public Type get(int index){
        if (index >= size){
            return null;
        } else {
            return Types[(first + 1 + index) % Types.length ];
        }
    }

    /** Create a deep copy of other. */
    public ArrayDeque(ArrayDeque other){
        Types = (Type[]) new Object[other.Types.length];
        first = other.first;
        last = other.last;
        size = other.size;

        System.arraycopy(other.Types, 0, Types, 0, other.Types.length);
    }

}
