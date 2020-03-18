package array;

/**
 * 数组:
 * <p>
 * 1. 随机访问,插入,删除
 * 2. 数据都是 int
 *
 * @author Suz1
 * @date 2020/3/18 8:58 下午
 */
public class Array {
    // container
    public int data[];
    // cap
    private int cap;
    //real length
    private int len;

    // constructor
    public Array(int cap) {
        this.data = new int[cap];
        this.cap = cap;
        this.len = 0;
    }

    // get element by idx
    public int find(int idx) {
        if (idx < 0 || idx >= cap) {
            return -1;
        }
        return data[idx];
    }

    // insert
    public boolean insert(int idx, int value) {
        // if full
        if (len == cap) {
            System.out.println("full array.");
            return false;
        }
        if (idx < 0 || idx >= cap) {
            return false;
        }
        // array
        for (int i = len; i > idx; --i) {
            data[i] = data[i - 1];
        }
        data[idx] = value;
        ++len;
        return true;
    }

    // delete by index
    public boolean delete(int index) {
        if (index < 0 || index > cap) {
            return false;
        }
        for (int i = index + 1; i < cap; ++i) {
            data[i - 1] = data[i];
        }
        --len;
        return true;
    }

    // print all
    public void printAll() {
        for (int i = 0; i < cap; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }


}
