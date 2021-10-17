import java.util.concurrent.atomic.AtomicInteger;

public class CustomLock {
    private final int n;
    private final AtomicInteger[] level;
    private final AtomicInteger[] victim;

    public CustomLock(int n) {
        this.n = n;
        level = new AtomicInteger[n];
        victim = new AtomicInteger[n];

        for (int i = 0; i < n; i++) {
            level[i] = new AtomicInteger(-1);
            victim[i] = new AtomicInteger(-1);
        }
    }

    public void lock(int i) {
        for (int L = 1; L < n; L++) {
            level[i].set(L);
            victim[L].set(i);

            while (condition(i, L) && victim[L].get() == i) {
            }
        }
    }

    private boolean condition(int i, int currentLvl) {
        for (int k = 0; k < n; k++)
            if (k != i && level[k].get() >= currentLvl)
                return true;
        return false;
    }

    public void unlock(int i) {
        level[i] = new AtomicInteger(0);
    }
}
