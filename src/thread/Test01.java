package thread;

/**
 * 利用sleep写一个死锁
 * <p>
 * test code:
 * Test01 testOne = new Test01("a");
 * new Thread(testOne).start();
 * Test01 testTwe = new Test01("b");
 * new Thread(testTwe).start();
 * try {
 * Thread.sleep(1000);
 * } catch (InterruptedException e) {
 * e.printStackTrace();
 * }
 * <p>
 * suspend 在进行暂停的时候没有释放对应的资源，而是占有着资源进入睡眠状态，容易引发死锁。
 * 同样，stop在终结一个线程时候，无法保证线程资源的正常释放，通常是没有给予线程完成资源释放
 * 的机会，导致程序工作在不确定的情况下。
 */
public class Test01 implements Runnable {
    private final String tag;

    private Object lockOne = new Object();
    private Object lockTwo = new Object();

    public Test01(String tag) {
        this.tag = tag;
    }

    @Override
    public void run() {
        if (tag.equalsIgnoreCase("a")) {
            synchronized (lockOne) {
                System.out.println(Thread.currentThread().getName() + "  in 1");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockTwo) {

                }
                System.out.println(Thread.currentThread().getName() + "  out 1");
            }
        } else {
            synchronized (lockTwo) {
                System.out.println(Thread.currentThread().getName() + "  in 2");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockOne) {

                }
                System.out.println(Thread.currentThread().getName() + "  out 2");
            }
        }
    }
}
