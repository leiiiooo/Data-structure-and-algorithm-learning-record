package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环屏障
 * <p>
 * 可以通过构造函数设置线程屏障的数目，线程通过调用
 * 线程屏障的await方法，告诉他我已经到了。数量达到之后，
 * 在无序执行后面的语句
 * <p>
 * 相比较CountDownLatch更加强大，CountDownLatch只能使用一次，
 * 但是CyclicBarrier可以使用reset进行重置。CyclicBarrier也提供了isBroken
 * 方法判断，使用它的线程是否发生了中断，还可以通过getNumberWaiting获取当前
 * 阻塞的线程数量
 */
public class Test12 {
    /**
     * 也可以使用这种构造函数，
     * 传入一个可以在都到达屏障数量的时候
     * 需要执行的action
     */
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("do me first-->"));

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " -----> prepare ok");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ------> done");

        }).start();

        try {
            System.out.println(Thread.currentThread().getName() + " -----> prepare ok");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ------> done");

    }
}
