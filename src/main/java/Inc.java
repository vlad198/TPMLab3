import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

//@AllArgsConstructor
public class Inc implements Runnable {
    private int pid;
    private CustomLock lock;
    volatile private Contor contor;
    //    volatile private AtomicInteger[] count;
    private int count = 0;

    public Inc(int pid, CustomLock lock, Contor contor) {
        this.pid = pid;
        this.lock = lock;
        this.contor = contor;
    }

    public int getCount() {
        return count;
    }

    //    volatile private int[] count;

    @Override
    public void run() {
        while (true) {
            lock.lock(pid);

            try {
                int value = contor.getValue();
                if (value == 100_000) {
                    break;
                }

                count++;
                contor.setValue(value + 1);
//                count[pid].set(count[pid].get() + 1);

//                count++;

//            count[pid] = count[pid] + 1;
            } finally {
                lock.unlock(pid);
            }
        }
        System.out.println("Pid: " + pid + " count: " + count);
    }
}
