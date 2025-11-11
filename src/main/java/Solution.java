import org.junit.Test;

public class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {

        int left = 0, right = plants.length - 1, alice = capacityA, bob = capacityB, ans = 0;
        // tow pointers
        while (left <= right) {
            // at last, when meet
            if (left == right) {
                if (Math.max(bob, alice) < plants[left]) {
                    ans++;
                }
                return ans;
            }

            // alice
            if (alice < plants[left]) {
                // alice refill
                ans++;
                alice = capacityA;
            }
            alice -= plants[left];
            left++;

            if (bob < plants[right]) {
                // bob refill
                ans++;
                bob = capacityB;
            }
            bob -= plants[right];
            right--;

        }
        return ans;
    }

    @Test
    public void test_break() {
        int i = 0;
        while (i < 100) {
            System.out.println("i = " + i);
            if (i == 2) {
                break;
            }
            i++;
        }
    }

}