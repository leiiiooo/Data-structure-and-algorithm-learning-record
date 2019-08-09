package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用CAS实现线程安全计数器
 * <p>
 * 可能会有的问题：
 * 1、ABA（即一个值先A在B在A，这样无法判断出），
 * 使用AtomicStampedReference可以解决，通过前后不同的索引值比对解决。
 * 2、循环时间长开销大。（如果JVM，支持pause指令的话效率可以提高）。
 * 3、只能保证一个共享变量的原子性。可以通过AtomicReference，
 * 将多个对象绑到一起做原子处理，然后在分开读取。
 * <p>
 * 除了偏向锁，JVM实现锁的所有方式都是，循环CAS
 */
class Counter {
    public AtomicInteger atomicInteger = new AtomicInteger(0);

    public void logSafeCount() {
        for (; ; ) {
            int i = atomicInteger.get();
            boolean tag = atomicInteger.compareAndSet(i, ++i);
            if (tag) {
                break;
            }
        }
    }
}

public class Test07 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        List<Thread> datas = new ArrayList();
        for (int i = 0; i < 10; i++) {
            datas.add(new Thread(() -> {
                for (int i1 = 0; i1 < 13; i1++) {
                    counter.logSafeCount();
                }
            }));
        }

        for (Thread data : datas) {
            data.start();
        }

        for (Thread data : datas) {
            try {
                data.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("----------main thread done-------------");
        System.out.println("counter.atomicInteger.get()=====>" + counter.atomicInteger.get());
    }
}
