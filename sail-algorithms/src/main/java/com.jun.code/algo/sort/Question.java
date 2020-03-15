package algo.sort;

/**
 * @author Jun
 * 创建时间： 2020/2/27
 */
public class Question {

    public static void main(String[] args) {
        System.out.println(sqrt2());
    }

    /**
     * 已知sqrt(2)约等于1.414，要求不用数学库，求sqrt(2)精确到小数点后10位。
     * 使用二分法的查找思想，注意循环的判断条件
     */
    public static double sqrt2() {
        double low = 1.413;
        double high = 1.415;
        double mid = (high + low) / 2;
        while (high - low > 0.0000000001) {
            if (mid * mid < 2) {
                low = mid;
            } else if (mid * mid > 2) {
                high = mid;
            }
            mid = (high + low) / 2;
        }
        return mid;
    }


}
