 

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

    public static int delta(int N) {
        int count = 0;

        for (int x = 2; x <= N; x++) {
            long normalRounded = normalRound(x);
            long chainRounded = chainRound(x);

            if (normalRounded != chainRounded) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {

            int N = sc.nextInt();

            System.out.println(delta(N));
        }

        sc.close();
    }
}
