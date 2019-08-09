package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore(信号量)控制同时访问特定资源的线程数量
 * <p>
 * 比如说，当前有30个线程并发，但是只允许同时有十个线程进行访问
 */
public class Test13 {
    static Semaphore semaphore = new Semaphore(10);
    static ExecutorService executorService = Executors.newFixedThreadPool(30);

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("save data");
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        executorService.shutdown();
    }
}
