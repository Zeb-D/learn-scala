/**
 * @author created by Zeb灬D on 2021-08-13 17:44
 */
public class NearPow {
    public static void main(String[] args) {
        int n = 19;
        int m = n - 2; // 17
        int k = (n + m) / 2; // 18
        System.out.println((n * m) == (k * k - 1));
        //问这公式使用范围，n的条件
    }
}
