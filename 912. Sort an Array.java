class Solution912 {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int[] helper = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, helper);
        return nums;
    }

    void mergeSort(int[] nums, int left, int right, int[] helper) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid, helper);
        mergeSort(nums, mid + 1, right, helper);
        merge(nums, left, mid, right, helper);
    }

    void merge(int[] nums, int left, int mid, int right, int[] helper) {
        int rightStart = mid + 1;
        for (int i = left; i <= right; i++) {
            helper[i] = nums[i];
        }

        int curr = left;
        while (left <= mid && rightStart <= right) {
            if (helper[left] < helper[rightStart]) {
                nums[curr++] = helper[left++];
            } else {
                nums[curr++] = helper[rightStart++];
            }
        }

        while (left <= mid) {
            nums[curr++] = helper[left++];
        }

    }



}

//class Solution {
//    public int[] sortArray(int[] nums) {
//        if (nums == null || nums.length <= 1) {
//            return nums;
//        }
//        helper(nums, 0, nums.length - 1);
//        return nums;
//
//    }
//
//    void helper(int[] nums, int left, int right) {
//        if (left >= right) return;
//        int index = getPivotIndex(left, right);
//        swap(nums, index, right);
//        int i = left;
//        int j = right - 1;
//        int pivot = nums[right];
//        while (i <= j) {
//            if (nums[i] <= pivot) {
//                i++;
//            } else if (nums[j] > pivot) {
//                j--;
//            } else {
//                swap(nums, i++,j--);
//            }
//        }
//        swap(nums, i, right);
//        helper(nums, left, i - 1);
//        helper(nums, i + 1, right);
//    }
//
//
//
//
//    int getPivotIndex(int i, int j) {
//        int length = j - i + 1;
//        return i + (int) (Math.random() * length);
//    }
//
//
//    void swap(int[] nums, int i, int j) {
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }
//
//
//}