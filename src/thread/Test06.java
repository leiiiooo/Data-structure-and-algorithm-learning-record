package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock和Condition实现生产者、消费者
 * <p>
 * Condition可以实现通知指定线程，和notifyAll不同。
 * <p>
 * 多线程交替打印，可以使用一个锁，两个condition
 * <p>
 * ReentrantReadWriteLock ReadLock可重入的多个线程同时read，
 * WriteLock不可以重入，同一时间
 * 只可以有一个线程write，读写也互斥，即写和其他都会产生互斥
 */
class Pt extends Thread {
    private final Condition cCondition;
    private final Condition pCondition;
    private final ReentrantLock lock;
    private final List data;

    public Pt(ReentrantLock lock, Condition pCondition, Condition cCondition, List data) {
        this.lock = lock;
        this.cCondition = cCondition;
        this.pCondition = pCondition;
        this.data = data;
    }

    @Override
    public void run() {
        super.run();
        //不要将锁的获取写在try中，一旦发生异常会导致，锁无缘无故的释放
        lock.lock();
        try {
            while (data.size() != 0) {
                pCondition.await();
            }
            data.add(new Object());
            System.out.println("=============>product one data.");
            cCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Ct extends Thread {
    private final Condition pCondition;
    private final Condition cCondition;
    private final ReentrantLock lock;
    private final List data;

    public Ct(ReentrantLock lock, Condition pCondition, Condition cCondition, List data) {
        this.lock = lock;
        this.pCondition = pCondition;
        this.cCondition = cCondition;
        this.data = data;
    }

    @Override
    public void run() {
        super.run();
        lock.lock();
        try {
            while (data.size() == 0) {
                cCondition.await();
            }
            data.remove(0);
            System.out.println("consumer one data.=============>");
            pCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class Test06 {
    public static void main(String[] args) {
        List data = new ArrayList();
        //默认是非公平锁
        ReentrantLock lock = new ReentrantLock();
        Condition pC = lock.newCondition();
        Condition cC = lock.newCondition();
        for (int i = 0; i < 50; i++) {
            Pt pt = new Pt(lock, pC, cC, data);
            pt.start();
            Ct ct = new Ct(lock, pC, cC, data);
            ct.start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
