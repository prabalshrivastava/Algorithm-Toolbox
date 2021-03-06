import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }
    private static long getFibonacciHugeFast(long n, long m) {
        ArrayList<Integer> fibs= new ArrayList<>();
        ArrayList<Integer> mods = new ArrayList<>();

        // Nothing to do if n = 0
        if (n == 0){
            return 0;
        }

        // Set up index 0 & 1
        mods.add(0, 0);
        mods.add(1, 1);
        fibs.add(0, 0);
        fibs.add(1, 1);
        //System.out.printf("%d, %d, ", mods.get(0), mods.get(1));

        for (int i = 2; true; i++){
            fibs.add(i, mods.get(i - 1) + mods.get(i - 2));
            mods.add(i, fibs.get(i) % m);
            //System.out.printf("%d, ", mods.get(i));

            // check if pattern repeats 0, 1
            if (mods.get(i - 1) == 0 && mods.get(i) == 1){
                mods.remove(i);
                mods.remove(i -1);
                break;
            }
        }

        //System.out.println("PISANO PERIOD LENGTH " + mods.size());
        int remainder = (int) (n % mods.size());
        return fibs.get(remainder) % m;

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeFast(n, m));
    }
}

