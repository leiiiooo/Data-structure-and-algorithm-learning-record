package thread;

/*
 * 分析double check 初始化单例在多线程模型可能会产生的问题
 */

/**
 * fix2
 */
class DoubleCheckObjectFactory {
    static class DoubleCheckObjectHolder {
        static DoubleCheckObject instance = new DoubleCheckObject();
    }

    public static DoubleCheckObject getDoubleCheckObject() {
        return DoubleCheckObjectHolder.instance;
    }
}

class DoubleCheckObject {
    //fix1
//    private volatile static DoubleCheckObject instance;
    private static DoubleCheckObject instance;

    public static DoubleCheckObject getInstance() {
        /*
         * 多线程中，这里可能会有问题，代码读取到instance不为null
         * 但是此时instance可能还没有初始化
         *
         * 重排序导致
         *
         * tag这行初始化，伪代码如下
         * memory = alloc();//1
         * init(memory);//2
         * instance = memory;//3
         *
         * 在单线程中由于遵守,intra-thread semantics
         * 所以即使2、3发生了重排序也没有关系，
         * 但是对于多线程，如果发生了重排序，即1、3、2，
         * 由于instance优先指向了没有初始化的内存
         * 导致init这行获取到的判断结果是错误的。
         *
         * 解决方式：
         * fix1:将instance 使用volatile修饰，
         * 使用jmm的happens-before机制(1.5以后的增强volatile语义)做保障,2、3无法重排序
         * fix2:使用类初始化解决。JVM在类的初始化阶段(Class被加载之后，被线程调用之前），会执行类的初始化。
         * 在执行类的初始化期间，JVM会获得一把锁，这把锁可以同步多个线程对同一个类的初始化。
         * 实质，允许重排序，但不允许其他线程在初始化之前看到
         *
         */
        //init
        if (instance == null) {
            synchronized (DoubleCheckObject.class) {
                if (instance == null) {
                    //tag
                    instance = new DoubleCheckObject();
                }
            }
        }

        return instance;
    }
}

public class Test08 {
}
