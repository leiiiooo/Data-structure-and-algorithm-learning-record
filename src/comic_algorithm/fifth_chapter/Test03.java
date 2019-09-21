package comic_algorithm.fifth_chapter;

import java.util.Stack;

public class Test03 {
    public static void main(String[] args) throws Exception {
        MinStack minStack = new MinStack();
        minStack.push(4);
        minStack.push(9);
        minStack.push(7);
        minStack.push(3);
        minStack.push(8);
        minStack.push(5);

        System.out.println("min==>" + minStack.getMin());

        minStack.pop();
        minStack.pop();
        minStack.pop();

        System.out.println("min==>" + minStack.getMin());
    }

    /**
     * 实现一个栈，该栈带有出栈（pop）、入栈（push）、
     * 取最小元素（getMin）3个方法。
     * 要保证这3个方法的时间复杂度都是O（1）。
     * <p>
     * 思路：使用一个辅助栈来帮助记录当前最小值，
     * 以及之前过往的最小值
     */
    private static class MinStack {
        Stack<Integer> dataStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        /**
         * 当前要插入的元素小于辅助栈，栈顶元素
         *
         * @param data
         */
        public void push(Integer data) {
            if (minStack.isEmpty() || data < minStack.peek()) {
                minStack.push(data);
            }
            dataStack.push(data);
        }

        public void pop() throws Exception {
            if (dataStack.isEmpty()) {
                throw new Exception();
            }

            if (minStack.isEmpty()) {
                throw new Exception();
            }

            if (dataStack.peek() == minStack.peek()) {
                minStack.pop();
            }

            dataStack.pop();
        }

        /**
         * 获取当前栈中最小的元素
         *
         * @return
         */
        public Integer getMin() throws Exception {
            if (minStack.isEmpty()) {
                throw new Exception();
            }

            return minStack.peek();
        }
    }
}
