package amery;

import java.util.EmptyStackException;

public class LeakyStack {
    private Object[] elements = new Object[20];
    private int size = 0;
    
    public void push(Object o) { elements[size++] = o; }
    
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        else {
            Object result = elements[--size];
            // elements[size+1] = null;
            return result;
        } 
    }
}
