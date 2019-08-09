package thread;

/**
 * 调用setTag(false) 程序能否停止
 * <p>
 * test code: 无法停止
 * Test02 test02 = new Test02();
 * test02.run();
 * //因为一直在处理while循环，所以无法停止
 * //解决办法是使用volatile+Runnable(或者Thread)
 * //volatile使得线程每次取变量都是强制从公共堆栈中取最新的值
 * System.out.println("stop-------------");
 * test02.setTag(false);
 */
public class Test02 {
    private volatile boolean tag = true;

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public void run() {
        while (tag) {
            System.out.println("in--->");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
