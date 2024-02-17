import java.util.LinkedList;

public class ListQueue<T> extends LinkedList<T> {
    public ListQueue() {
        super();
    }

    @Override
    public T pop() {
        throw new UnsupportedOperationException("ListQueue does not support pop().");
    }

    @Override
    public void push(T t) {
        throw new UnsupportedOperationException("ListQueue does not support push().");
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