package class19;

//背包问题
//https://www.lintcode.com/problem/125/
public class Code01_Knapsack {
    //暴力递归
    //有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
    public static int backPackII1(int m, int[] a, int[] v) {
        if (m < 0 || a.length <= 0 || v.length != a.length) {
            return 0;
        }
        return porcess1(m, a, v, a.length - 1);
    }

    private static int porcess1(int m, int[] a, int[] v, int index) {
        //不能小于等于，可能有0重量的物品
        if (m < 0) {
            return -1;
        }
        if (index < 0) {
            return 0;
        }

        int max = 0;
        int t;
        max = porcess1(m, a, v, index - 1);
        t = porcess1(m - a[index], a, v, index - 1);
        if (t != -1) {//不越界才执行
            max = Math.max(max, v[index] + t);
        }
        return max;
    }

    //动态规划
    public static int backPackII2(int m, int[] a, int[] v) {
        if (m < 0 || a.length <= 0 || v.length != a.length) {
            return 0;
        }
        //背包剩余重量，物品
        int[][] dp = new int[m + 1][a.length + 1];

        for (int i = 0; i <= m; i++) {
            //在解1的index<0，在这里改成index=a.length时越界，左右翻转了
            for (int j = a.length - 1; j >= 0; j--) {
                int max = dp[i][j + 1];
                int t = (i - a[j]) < 0 ? -1 : v[j] + dp[i - a[j]][j + 1];
                if (t != -1) {//不越界才执行
                    max = Math.max(max, t);
                }
                dp[i][j]=max;
            }
        }
        return dp[m][0];
    }

    public static void main(String[] args) {
        int a[] = new int[]{2, 3, 5, 7};
        int v[] = new int[]{1, 5, 2, 4};
        int m = 10;
        System.out.println(backPackII1(m, a, v));
        System.out.println(backPackII2(m, a, v));
    }
}
