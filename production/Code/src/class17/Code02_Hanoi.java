package class17;

//汉诺塔问题
//递归
public class Code02_Hanoi {
    //解法1：最直观的解法
    public static void hanoi1(int n) {
        leftToRight(n);
    }

    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("move 1 left to right");
        midToRight(n - 1);
    }

    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("move 1 mid to right");
        leftToRight(n - 1);
    }

    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("move 1 mid to left");
        rightToLeft(n - 1);
    }

    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("move 1 left to mid");
        rightToMid(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("move 1 right to mid");
        leftToMid(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("move 1 right to left");
        midToLeft(n - 1);
    }

    //解法2：六合一
    private static void hanoi2(int n) {
        func(n, "left", "right", "mid");
    }

    //移动的数量，起始位置，目标位置，不用管的位置
    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1 " + from + " to " + to);
            return;
        }
        func(n - 1, from, other, to);
        System.out.println("move 1 " + from + " " + to);
        func(n - 1, other, to, from);
    }

    //迭代法
    private static void hanoi3(int n) {
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi1(n);
        System.out.println("==================");
        hanoi2(n);
        System.out.println("==================");
        hanoi3(n);
        System.out.println("==================");

    }


}
