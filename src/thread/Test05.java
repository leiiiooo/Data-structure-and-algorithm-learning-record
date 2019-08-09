package thread;

/**
 * join 等待线程对象销毁
 * <p>
 * join:让父线程等待子线程结束之后才能继续运行
 * <p>
 * 当我们调用某个线程的这个方法时，这个方法会挂起调用线程，直到被调用线程结束执行，调用线程才会继续执行。
 */
class Thread01 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("thread in");
            Thread.sleep(5000);
            System.out.println("thread out");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Test05 {
    public static void main(String[] args) {
        try {
            Thread01 thread01 = new Thread01();
            thread01.start();
            System.out.println("" + System.currentTimeMillis());
            //如果其他地方没有加锁的话，就是2秒会返回当前线程，继续后面代码执行
            thread01.join(2000);
            //synchronized方法所以主线程就持有了这个对象锁
            System.out.println("main end");
            System.out.println("" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
