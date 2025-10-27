package leetcode.solution.binary_ops;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类：用于处理订单类型的混合标识（mix_type）
 * <p>
 * 功能：
 * 1. 将多个订单类型（如独享、优享、拼车等）转换为十进制的 mix_type 编码
 * 2. 反向解析十进制 mix_type，得到原始的类型列表
 * <p>
 * 类型编码规则（从右往左为低位到高位）：
 * bit0 -> 独享      -> 十进制 1
 * bit1 -> 优享      -> 十进制 2
 * bit2 -> 拼车1+1   -> 十进制 4
 * bit3 -> 站点拼车   -> 十进制 8
 * bit4 -> 特惠拼车   -> 十进制 16
 * <p>
 * 举例：
 * [1, 3] => 二进制 00101 => 十进制 5
 * [2, 4, 10] => 二进制 11010 => 十进制 26
 * <p>
 * mix_type 11111五位，订单类型二进制对应十进制值 从右到左每位依次代表独享、优享、拼车1+1、站点拼车、特惠拼车'
 * mix_type_str varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单类型：1-独享，2-优享，3-拼车1+1，4-站点拼车，10-特惠拼车；如3,4 或 1',
 */
public class MixTypeUtil {

    /**
     * 订单类型 → 二进制位值 对照表
     * key: 订单类型（1、2、3、4、10）
     * value: 二进制位（1、2、4、8、16）
     */
    private static final Map<Integer, Integer> TYPE_BIT_MAP = new HashMap<>();

    // mix_type 11111五位，订单类型二进制对应十进制值 从右到左每位依次代表独享、优享、拼车1+1、站点拼车、特惠拼车'
    // 独享 00001
    // 优享 00010
    // 拼车1+1 00100
    // 站点拼车 01000
    // 特惠拼车 10000
    // mix_type_str varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单类型：1-独享，2-优享，3-拼车1+1，4-站点拼车，10-特惠拼车；如3,4 或 1',
    static {
        TYPE_BIT_MAP.put(1, 1);   // 独享 -> 00001
        TYPE_BIT_MAP.put(2, 2);   // 优享 -> 00010
        TYPE_BIT_MAP.put(3, 4);   // 拼车1+1 -> 00100
        TYPE_BIT_MAP.put(4, 8);   // 站点拼车 -> 01000
        TYPE_BIT_MAP.put(10, 16); // 特惠拼车 -> 10000
    }

    /**
     * 根据订单类型数组计算 mix_type 的十进制值
     *
     * @param types 订单类型数组，如 [1,3]
     * @return mix_type 对应的十进制数值
     * <p>
     * 实现思路：
     * - 遍历所有类型
     * - 通过查表获取对应的二进制位值
     * - 使用“按位或（|=）”操作叠加，最终得到组合后的数值
     */
    public static int getMixType(List<Integer> types) {
        int mixType = 0;
        for (int type : types) {
            Integer bit = TYPE_BIT_MAP.get(type);
            if (bit != null) {
                mixType |= bit; // 按位或，用于叠加不同类型的二进制标志
            }
        }
        return mixType;
    }

    /**
     * 反向解析：根据 mix_type 十进制值，反推原始的类型列表
     *
     * @param mixType mix_type 十进制值
     * @return 类型列表，如 [1,3]
     * <p>
     * 实现思路：
     * - 遍历 TYPE_BIT_MAP
     * - 使用按位与 (&) 判断该位是否被置位
     * - 若被置位，说明该类型被包含
     */
    public static List<Integer> parseMixType(int mixType) {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : TYPE_BIT_MAP.entrySet()) {
            if ((mixType & entry.getValue()) != 0) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 测试用例
        List<Integer> types = Lists.newArrayList(1, 3, 10);
        int mixType = getMixType(types);
        System.out.println("Types: " + types + " => MixType: " + mixType); // 1 + 4 + 16 = 21

        int testMixType = 21;
        List<Integer> parsedTypes = parseMixType(testMixType);
        System.out.println("MixType: " + testMixType + " => Parsed Types: " + parsedTypes); // [1, 3, 10]
    }
}