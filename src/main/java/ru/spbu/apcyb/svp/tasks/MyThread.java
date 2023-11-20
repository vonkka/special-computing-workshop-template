package ru.spbu.apcyb.svp.tasks;

public class MyThread implements Runnable {
    double[] res;
    double[] nums;
    int start;
    int end;

    public MyThread(double res[], double[] nums, int start, int end) {
        this.res = res;
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    public void calculateTan() {
        end = Math.min(end, nums.length);
        for (int i = start; i < end; ++i) {
            res[i] = Math.tan(nums[i]);
        }
    }

    @Override
    public void run() {
        calculateTan();
    }
}
