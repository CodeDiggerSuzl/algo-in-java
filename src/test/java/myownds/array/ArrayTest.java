package myownds.array;

import array.Array;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Suz1
 * @date 2020/3/18 9:24 下午
 */
public class ArrayTest {
    @Test
    public void testOriginArray() {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < 10; i++) {
            System.out.print("|" + arr[i]);
        }
        System.out.println();
        arr[5] = 11;
        for (int i = 0; i < 10; i++) {
            System.out.print("|" + arr[i]);
        }
        Assert.assertNotNull(arr);

    }

    @Test
    public void testMyArray() {
        Array myArr = new Array(10);
        myArr.printAll();
        myArr.insert(1, 1);
        myArr.insert(3, 3);
        myArr.insert(2, 2);
        myArr.insert(4, 4);
        myArr.insert(5, 5);
        myArr.insert(10, 10);
        myArr.delete(9);
        myArr.printAll();
        Assert.assertNotNull(myArr);
    }
}