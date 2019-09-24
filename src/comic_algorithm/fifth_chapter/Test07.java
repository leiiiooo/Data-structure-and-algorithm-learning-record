package comic_algorithm.fifth_chapter;

import java.util.Stack;

public class Test07 {
    public static void main(String[] args) {
        title1();
    }


    private static void title1() {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);

        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }

    /**
     * 如何使用栈实现队列
     * <p>
     * 思路：使用俩个栈模拟队列这种数据结构
     * <p>
     * 入队的时间复杂度为：O(1)
     * 出队的平均时间复杂度也为O(1)
     */
    private static class MyStack {
        private final Stack<Integer> inStack = new Stack<>();
        private final Stack<Integer> outStack = new Stack<>();

        public void push(int value) {
            inStack.push(value);
        }

        public Integer pop() {
            if (!outStack.isEmpty()) {
                return outStack.pop();
            }

            if (inStack.isEmpty()) {
                return null;
            }

            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }

            return outStack.pop();
        }
    }
}
