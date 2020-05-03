public interface Deque<Type> {
    default boolean isEmpty(){
        return size() == 0;
    }
    void addFirst(Type i);
    void addLast(Type i);
    Type removeFirst();
    Type removeLast();
    int size();
    void printDeque();
    Type get(int i);
}
