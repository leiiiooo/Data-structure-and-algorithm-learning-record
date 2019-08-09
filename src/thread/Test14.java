package thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以使用Exchanger进行线程之间交换数据
 * <p>
 * 如果两个线程有一个没有执行exchange就会一直等待
 */
public class Test14 {
    static Exchanger<String> exchanger = new Exchanger<>();
    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        executorService.execute(() -> {
            try {
                exchanger.exchange("hello B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                String fromAnotherThread = exchanger.exchange("b");
                System.out.println("fromAnotherThread==>" + fromAnotherThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
