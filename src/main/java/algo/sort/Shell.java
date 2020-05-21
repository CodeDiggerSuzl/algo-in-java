package algo.sort;

/**
 * @author Suz1
 * @date 2020/5/17 11:38 下午
 */
public class Shell {
    // 对数组a中的元素进行排序
    public static void shellSort(Comparable[] a) {
        // 1. 根据数组长度,确定增加量 h 的初始值
        int h = 1;
        while (h < a.length / 2) {
            h = 2 * h + 1;
        }
        // 2. shell sort
        while (h >= 1) {
            // 2.1 找到待插入的元素
            for (int i = h; i < a.length; i++) {
                // 2.2 把待插入的元素插入到有序数组中
                for (int j = i; i < a.length; i++) {
                    // 待插入的元素为 a[j],比较 a[j] 和 a[j-h]
                    if (greater(a[j - h], a[j])) {
                        // exchange element
                        exchange(a, j - h, j);
                    } else {
                        // 待插入元素已经找到了合适的位置，结束循环；
                        break;
                    }
                }
            }
            // 减少 h 的值
            h = h / 2;
        }
    }

    /**
     * 比较 v 是否大于w 元素
     */
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * change the the position
     */
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

