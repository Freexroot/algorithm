package class17;

import java.util.Stack;

//逆序栈
public class Code05_ReverseStackUsingRecursive {
    //递归本身相当于一个栈
    public static void reverse(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        int bootom = f(stack);//拿到栈底
        reverse(stack);
        stack.add(bootom);
    }

    //返回栈底
    private static Integer f(Stack<Integer> stack) {
        if (stack.size() == 1) {
            return stack.pop();
        }
        int bottom, cur;
        cur = stack.pop();
        bottom = f(stack);
        stack.add(cur);
        return bottom;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);

        reverse(stack);
        for (Integer integer : stack) {
            System.out.println(integer);
        }
    }
}
