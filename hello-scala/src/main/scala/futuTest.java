import java.util.HashMap;
import java.util.Map;

/**
 * @author created by Zeb灬D on 2022-03-21 17:54
 */
public class futuTest {

    public static Map<Integer, Integer> vip = new HashMap<>();
    public static Map<Integer, Integer> price = new HashMap<>();

    public static void main(String[] args) {
        initConfig();
        System.out.println(count(21));
    }

    private static int count(int n) {
        int sum = 0;
        int step = 1;

        for (; n > 0 && step <= 3; ) {
            int vipCount = vip.get(step);
            int count = price.get(step);
            if (n <= vipCount) {
                sum += n * count;
            } else {
                sum += vipCount * count;
            }
            step++;
            n = n - vipCount;
        }

        return sum;
    }

    private static void initConfig() {
        vip.put(1, 5);
        vip.put(2, 20);
        vip.put(3, 50);

        price.put(1, 30);
        price.put(2, 15);
        price.put(3, 10);
    }
}
