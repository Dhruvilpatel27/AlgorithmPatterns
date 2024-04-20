import java.util.Arrays;

public class SlidingWindowMaxSum {
    
    public static int maxSumSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
        
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        
        // Compute the sum of the first window
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        maxSum = Math.max(maxSum, currentSum);
        
        // Slide the window and update the max sum
        for (int i = k; i < n; i++) {
            currentSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 1, 7, 8, 1, 2, 8, 1, 0};
        int k = 3;
        int maxSum = maxSumSubarray(nums, k);
        System.out.println("Maximum sum of subarray of size " + k + ": " + maxSum);
    }
}
