public class LinkedListDeque<Type> {

    private static class Node<Type>{
        Node prev;
        Type item;
        Node next;
        public Node(Node p, Type i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size = 0;
    private Node sentinel;

    /** Create a null Deque. */
    public LinkedListDeque(){
         sentinel = new Node(null, null, null);
         sentinel.next = sentinel;
         sentinel.prev = sentinel;
         size = 0;
    }

    /** Create a Deque contain x. */
    public LinkedListDeque(Type x){
        sentinel = new Node(sentinel, -1, sentinel);
        sentinel.next = new Node(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Add x to the head of the list. */
    public void addFirst(Type x){
        size += 1;
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    /** Add x to the head of the list. */
    public void addLast(Type x){
        size += 1;
        sentinel.prev = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    /** Return true if the list is empty. */
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    /** Return the size of the list. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last. */
    public void printDeque(){
        if (this.isEmpty() == true){
            System.out.println("The list is empty!");
        }
        Node p = sentinel;
        for (int i = 0;i<size;i++){
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. */
    public Type removeFirst(){
        if (this.isEmpty() == true){
            return null;
        } else {
            size -= 1;
            Type first = (Type) sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            return first;
        }
    }

    /** Removes and returns the item at the back of the deque. */
    public Type removeLast() {
        if (this.isEmpty() == true) {
            return null;
        } else {
            size -= 1;
            Type last = (Type) sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            return last;
        }
    }

    /** Gets the item at the given index by iteration. */
    public Type get(int index){
        Node L = sentinel;
        if (index<size) {
            for (int i = -1; i<index; i++){
                L = L.next;
            }
            return (Type) L.item;
        } else {return null;}
    }

    /** Gets the item at the given index by recursion. */
    public Type getRecursive(int index){
        if (index<size){
             if (index == 0){
                 return (Type) sentinel.next.item;
             } else {
                 LinkedListDeque L = new LinkedListDeque();
                 L = this;
                 L.sentinel.next = L.sentinel.next.next;
             return (Type) L.getRecursive(index - 1);
             }
        }
        return null;
    }

    /** Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque other){
        LinkedListDeque L = new LinkedListDeque();
        Node N = other.sentinel;
        for (int i = 0; i<other.size(); i++){
            L.addLast(N.next.item);
            N = N.next;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque L = new LinkedListDeque();
        L.addLast(5);
        L.addLast(4);
        L.printDeque();
    }
}
