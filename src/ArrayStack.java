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
     * @return the last element of the ArrayStack or null if the ArrayStack is empty
     */
    public T pop() {
        try {   
            return remove(size() - 1);

        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Peeks the last element of the ArrayStack without removing it
     * 
     * @return the last element of the ArrayStack or null if the ArrayStack is empty
     */
    public T peek() {
        try {
            return get(size() - 1);

        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (T ele : this) {
            str += ele.toString() + "\n";
        }

        return str;
    }
}