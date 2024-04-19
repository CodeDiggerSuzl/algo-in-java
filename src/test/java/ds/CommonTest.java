package ds;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Suz1
 * @date 2020/5/21 9:56 下午
 */
public class CommonTest {
    @Test
    public void testBitMove() {
        int i = 8;
        Assert.assertEquals(i >> 1, 4);
    }

    @Test
    public void testArrayList() {
        ArrayList<String> list = new ArrayList<>(1);
        ArrayList<String> list1 = new ArrayList<>();
        Assert.assertEquals(0, list1.size());
    }

    public static void main(String[] args) {
        int a = 0;
        int b = 0;

        int c = a++;
        int d = ++b;

        System.out.println("c = " + c);
        System.out.println("d = " + d);
    }
}
