package leetcode.solution;

import annotion.LongTime;
import annotion.Stocked;
import org.junit.Test;

import java.util.Arrays;

/**
 * url
 *
 * <a href="https://leetcode.cn/problems/find-original-array-from-doubled-array/description/?envType=daily-question&envId=2024-04-18">...</a>
 * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。
 * <p>
 * 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：changed = [1,3,4,2,6,8]
 * 输出：[1,3,4]
 * 解释：一个可能的 original 数组为 [1,3,4] :
 * - 将 1 乘以 2 ，得到 1 * 2 = 2 。
 * - 将 3 乘以 2 ，得到 3 * 2 = 6 。
 * - 将 4 乘以 2 ，得到 4 * 2 = 8 。
 * 其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
 * 示例 2：
 * <p>
 * 输入：changed = [6,3,0,1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 * 示例 3：
 * <p>
 * 输入：changed = [1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 */
@LongTime
@Stocked
public class No_3_2007 {
    public int[] findOriginalArray(int[] changed) {

        return null;
    }


    @Test
    public void test() {
        int[] arr = {1, 3, 4, 2, 6, 8};
        int[] originalArray = findOriginalArray(arr);
        System.out.println("Arrays.toString(originalArray) = " + Arrays.toString(originalArray));
    }

}
