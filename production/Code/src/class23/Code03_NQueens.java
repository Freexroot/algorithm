package class23;

/*
* N皇后问题是指在N*N的棋盘上要摆N个皇后，
要求任何两个皇后不同行、不同列， 也不在同一条斜线上
给定一个整数n，返回n皇后的摆法有多少种。
n=1，返回1
n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
n=8，返回92
* */
public class Code03_NQueens {

    public static int nQueens1(int n) {
        if (n < 1) {
            return 0;
        }
        return process1(new int[n], 0);
    }

    private static int process1(int[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[index] = i;
            if (isValid(arr, index, i)) {
                sum += process1(arr, index + 1);
            }
        }
        return sum;
    }

    public static boolean isValid(int arr[], int i, int j) {
        for (int i1 = 0; i1 < i; i1++) {
            if (arr[i] == arr[i1] || Math.abs(i1 - i) == Math.abs(arr[i1] - j)) {
                return false;
            }
        }
        arr[i] = j;
        return true;
    }


    private static int nQueens2(int n) {
        if (n < 1) {
            return 0;
        }
        n = (1 << n) - 1;
        return process2(n, 0, 0, 0);
    }

    // limit : 0....0 1 1 1 1 1 1 1 表示几皇后
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    private static int process2(int limit, int lower, int left, int right) {
        if (lower == limit) {
            return 1;
        }
        //其中的1表示可以填的位置
        int a = limit & (~(left | right | lower));
        int res = 0;//累积数
        while (a != 0) {
            int t = a & (~a + 1);//提取末尾的1
            a = a - t;//消除末尾的1
            res += process2(limit, lower | t, (left | t) << 1,
                    (right | t) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 31;
        for (int i = 0; i < n; i++) {
            System.out.println(nQueens1(i) + "  " + nQueens2(i));
        }
    }


}
