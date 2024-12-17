
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Q1 {

    public static long normalRound(long number) {

        int numDigits = (int) Math.log10(number);

        return round(number, numDigits + 1);

    }

    public static long round(long number, int digit) {

        int multiple = (int) Math.pow(10, digit);

        long rounded = (Math.round(number / (double) multiple) * multiple);
        return rounded;

    }

    public static long chainRound(long x) {

        long ret = x;
        int loop = (int) Math.log10(x) + 1;

        for (int i = 1; i <= loop; i++) {
            ret = round(ret, i);
        }

        return ret;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int count = sc.nextInt();

        List<Integer> nums = new ArrayList<Integer>();

        Map<Integer, Integer> m = new HashMap<Integer, Integer>();

        for (int t = 0; t < count; t++) {
            int i = sc.nextInt();
            nums.add(i);
            m.put(i, -1);
        }

        sc.close();

        int max = nums.stream().max(Integer::compareTo).get();

        int subtotal = 0;

        for (int x = 1; x < max + 1; x++) {

            long normalRounded = normalRound(x);
            long chainRounded = chainRound(x);

            if (normalRounded != chainRounded) {
                subtotal++;
            }
            if (nums.contains(x)) {
                m.put(x, subtotal);
            }
        }

        nums.forEach(e -> System.out.println(m.get(e)));

    }
}
