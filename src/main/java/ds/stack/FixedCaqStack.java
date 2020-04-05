package ds.stack;

/**
 * algo 4th edition
 *
 * @author Suz1
 * @date 2020/3/19 9:46 下午
 */
public class FixedCaqStack<T> {
    private T[] a;
    private int len;

    public FixedCaqStack(int cap) {
        a = (T[]) new Object[cap];
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public int size() {
        return len;
    }

    public void push(T item) {
        if (len == a.length) {
            resize(2 * a.length);
        }
        a[len++] = item;
    }

    public T pop() {
        T t = a[--len];
        // 避免对象游离
        a[len] = null;
        if (len > 0 || len == a.length) {
            resize(a.length / 4);
        }
        return t;
    }

    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        System.arraycopy(a, 0, temp, 0, len);
        a = temp;
    }


}
