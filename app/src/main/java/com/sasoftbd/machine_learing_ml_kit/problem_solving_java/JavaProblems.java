package com.sasoftbd.machine_learing_ml_kit.problem_solving_java;

/**
 * Md Zakaria Ahnaf
 * 20-Sep-2022
 */
public class JavaProblems {

    /**
     * with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]
     * @param nums
     * @param k
     */
    public void rotateArrayJava(int[] nums, int k) {
        if(k > nums.length)
            k = k % nums.length;

        int[] result = new int[nums.length];

        for(int i = 0; i < k; i++){
            result[i] = nums[nums.length-k+i];
        }
        int j = 0;
        for(int i = k; i < nums.length; i++){
            result[i] = nums[j];

        }
        System.out.println("Hello World");

    }

}
