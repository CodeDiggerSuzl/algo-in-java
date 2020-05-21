package ds;

import org.junit.Assert;
import org.junit.Test;

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
}
