import java.util.ArrayList;

public class ArrayStack<T> extends ArrayList<T> {
    public ArrayStack() {
        super();
    }

    public void push(T element) {
        add(element);
    }

    /**
     * Pops the last element of the ArrayStack
     * 
     * @return the last element of the ArrayStack
     */
    public T pop() {
        return remove(size() - 1);
    }
}