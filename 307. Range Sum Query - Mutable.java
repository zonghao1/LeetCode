/*
Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8


Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange.
 */

class NumArray307a {
    // Using BinaryIndexTree/ Fenwick Tree

    BinaryIndexTree tree;
    public NumArray307a(int[] nums) {
        tree = new BinaryIndexTree(nums.length + 1);
        for (int i = 0; i < nums.length; i++) {
            tree.update(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        tree.update(index + 1, val);
    }

    public int sumRange(int left, int right) {
        int leftSum = tree.query(left);
        int rightSum = tree.query(right + 1);
        return rightSum - leftSum;
    }



    class BinaryIndexTree {
        int array[];

        BinaryIndexTree(int n) {
            array = new int[n];
        }

        void update(int index, int val) {
            int before = query(index) - query(index - 1);
            int diff = val - before;
            while (index < array.length) {
                array[index] += diff;
                index += lowBit(index);
            }
        }

        int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += array[index];
                index -= lowBit(index);
            }
            return sum;
        }




        int lowBit(int i) {
            return i & (-i);
        }

    }


}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */