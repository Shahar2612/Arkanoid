import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Test t = new Test();
        int someNum = 2;
        int []nums = {10,8,6,4};
        t.someMethod(someNum, nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(someNum);

    }
    private void someMethod(int someNum, int []nums) {
        nums [1] = 5;
        nums = new int[3];
        nums[2] = 5;
    }
}