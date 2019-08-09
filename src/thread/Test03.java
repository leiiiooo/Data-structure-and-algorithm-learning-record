package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单实例：使用sleep和while(true) 实现线程通信
 * <p>
 * test code:
 * MyList list = new MyList();
 * ThreadA threadA = new ThreadA(list);
 * threadA.start();
 * ThreadB threadB = new ThreadB(list);
 * threadB.start();
 * <p>
 * try {
 * Thread.sleep(100);
 * } catch (InterruptedException e) {
 * e.printStackTrace();
 * }
 */
class MyList {
    List<Integer> data = new ArrayList<>();

    public void add(Integer integer) {
        data.add(integer);
    }

    public int size() {
        return data.size();
    }
}

class ThreadA extends Thread {
    private final MyList list;

    public ThreadA(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        super.run();

        for (int i = 0; i < 5; i++) {
            list.add(i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadB extends Thread {
    private final MyList list;

    public ThreadB(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                if (list.size() == 5) {
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 使用wait和notify处理
 * <p>
 * object的方法，同一把锁
 * <p>
 * test code:
 * try {
 * Object lock = new Object();
 * <p>
 * Thead3 thead3 = new Thead3(lock);
 * thead3.start();
 * <p>
 * Thread.sleep(100);
 * <p>
 * Thread4 thread4 = new Thread4(lock);
 * thread4.start();
 * } catch (InterruptedException e) {
 * e.printStackTrace();
 * }
 */
class Thead3 extends Thread {
    private final Object lock;

    public Thead3(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();

        synchronized (lock) {
            try {
                lock.wait();
                System.out.println("the result---> print ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Thread4 extends Thread {
    private final Object lock;

    public Thread4(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();

        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                System.out.println("i------>" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            lock.notify();

            System.out.println("add data ok");
        }
    }
}

/**
 * wait 设置时间，锁会自动回来吗？
 * <p>
 * 如果先调用notify在调用wait，这个时候wait仍然会阻塞
 * <p>
 * 如果另一个持有锁的没有释放锁，这个时候其实也
 * 不会自动执行接下来的代码，还是要抢锁
 * <p>
 * test code:
 * Object lock = new Object();
 * Thead5 thead5 = new Thead5(lock);
 * thead5.setPriority(Thread.MAX_PRIORITY);
 * thead5.start();
 * <p>
 * try {
 * Thread.sleep(100);
 * } catch (InterruptedException e) {
 * e.printStackTrace();
 * }
 * <p>
 * Thead6 thead6 = new Thead6(lock);
 * thead6.setPriority(Thread.MIN_PRIORITY);
 * thead6.start();
 */

class Thead5 extends Thread {
    private final Object lock;

    public Thead5(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();

        synchronized (lock) {
            try {
                lock.wait(2000);
                System.out.println("wait endddddddddddddd");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Thead6 extends Thread {
    private final Object lock;

    public Thead6(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();

        synchronized (lock) {
            try {
                Thread.sleep(3000);
                System.out.println("Sleep enddddddddddd");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

/**
 * 多生产者和多消费者模式(包括一个也是如此)，core:使用while+notifyAll
 * 容易出现假死状态，即所有的线程都wait，出现的原因是
 * 使用notify的时候，有可能唤醒的是相同功能的类，比如：生产者唤醒了生产者(解决的方法是使用，notifyAll)
 * <p>
 * 一个生产者，一个消费者交替打印，如果这里变成5个p，5个c，
 * 5个p到生产完成5个c才能消费的话，
 * 可以考虑增加volatile修饰的标记位，
 * 同时使用while判断标记位，加synchronize锁进行处理
 * <p>
 * List data = new ArrayList();
 * <p>
 * Object lock = new Object();
 * <p>
 * for (int i = 0; i < 10; i++) {
 * P p = new P(lock, data);
 * p.start();
 * }
 * <p>
 * try {
 * Thread.sleep(100);
 * } catch (InterruptedException e) {
 * e.printStackTrace();
 * }
 * <p>
 * for (int i = 0; i < 10; i++) {
 * C c = new C(lock, data);
 * c.start();
 * }
 **/
class P extends Thread {
    private final Object lock;
    private final List data;

    public P(Object lock, List data) {
        this.lock = lock;
        this.data = data;
    }

    @Override
    public void run() {
        super.run();

        synchronized (lock) {
            try {
                while (data.size() != 0) {
                    lock.wait();
                }
                lock.notifyAll();
                data.add(new Object());
                System.out.println("produce a data");
            } catch (InterruptedException e) {

            }
        }
    }
}

class C extends Thread {
    private final Object lock;
    private final List data;

    public C(Object lock, List data) {
        this.lock = lock;
        this.data = data;
    }

    @Override
    public void run() {
        super.run();

        synchronized (lock) {

            try {
                while (data.size() == 0) {
                    lock.wait();
                }
                lock.notifyAll();
                System.out.println("consumer a data");
                data.remove(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}