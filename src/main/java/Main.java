import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        example(1);
//        example(2);
        example(4);
//        example(8);
    }

    private static void example(int n) throws InterruptedException {
        long start = System.currentTimeMillis();

        Contor contor = new Contor();
        CustomLock lock = new CustomLock(n);
        //// var 1
        AtomicInteger[] count = new AtomicInteger[n];

        for (int i = 0; i < n; i++)
            count[i] = new AtomicInteger(0);

        //// var 2
//        int[] count = new int[n];

        List<Thread> threadList = new LinkedList<>();
        for (int i = 0; i < n; i++)
            threadList.add(new Thread(new Inc(i, lock, contor)));

        for (int i = 0; i < n; i++)
            threadList.get(i).start();

        long stop = System.currentTimeMillis();

        for(int i=0;i<n;i++)
            threadList.get(i).join();

        System.out.println("######################################");
        System.out.println("n = " + n + "..." + contor + "..." + (stop - start));
//        System.out.println(Arrays.toString(c));
//        System.out.println(Arrays.stream(count).reduce(0,(s,t) -> s+t);
//        System.out.println("Sum: " + Arrays.stream(count).reduce(Integer::sum).getAsInt());
    }
}
