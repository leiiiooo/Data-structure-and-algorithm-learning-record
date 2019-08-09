package thread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch demo
 * <p>
 * 也可以使用join实现当前线程等待线程结束之后继续执行
 * <p>
 * 一个线程调用countDown方法happen-before，另外线程调用await方法
 */
public class Test11 {
    static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) {
        Runnable runnableOne = () -> {
            System.out.println("runnable one run !");
            latch.countDown();
        };
        Runnable runnableTwo = () -> {
            System.out.println("runnable two run !");
            latch.countDown();
        };
        Thread threadOne = new Thread(runnableOne);
        Thread threadTwo = new Thread(runnableTwo);

        threadOne.start();
        threadTwo.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread run done!!!");
    }
}
