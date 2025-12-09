import org.junit.Test;

public class ReviewQuestions {

    public int findPeakElement(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    @Test
    public void test_() {
        int[] arr = {1, 2, 1, 3, 5, 6, 4};
        int peakElement = findPeakElement(arr);
        System.out.println("peakElement = " + peakElement);
    }
}
