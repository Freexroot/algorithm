package class20;
/*
* 请同学们自行搜索或者想象一个象棋的棋盘，
然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
给你三个 参数 x，y，k
返回“马”从(0,0)位置出发，必须走k步
最后落在(x,y)上的方法数有多少种?
* */

public class Code02_HorseJump {
    //暴力递归
    private static int horse1(int x, int y, int k) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || k <= 0) {
            return 0;
        }
        return process(0, 0, x, y, k);
    }

    //a,b是当前位置，xy是目标位置，k是步数
    private static int process(int a, int b, int x, int y, int k) {
        if (a < 0 || a > 8 || b < 0 || b > 9) {
            return 0;
        }
        if (k == 0) {
            return (a == x && b == y) ? 1 : 0;
        }
        int ways = process(a + 2, b + 1, x, y, k - 1);
        ways += process(a + 1, b + 2, x, y, k - 1);
        ways += process(a - 1, b + 2, x, y, k - 1);
        ways += process(a - 2, b + 1, x, y, k - 1);
        ways += process(a - 2, b - 1, x, y, k - 1);
        ways += process(a - 1, b - 2, x, y, k - 1);
        ways += process(a + 1, b - 2, x, y, k - 1);
        ways += process(a + 2, b - 1, x, y, k - 1);
        return ways;
    }

    //动态规划

    public static int horse2(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int step = 1; step <= k; step++) { // 按层来
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    //每个方向都要判断越界
                    dp[i][j][step] = getValue(dp, i - 2, j + 1, step - 1) + getValue(dp, i - 1, j + 2, step - 1)
                            + getValue(dp, i + 1, j + 2, step - 1) + getValue(dp, i + 2, j + 1, step - 1)
                            + getValue(dp, i + 2, j - 1, step - 1) + getValue(dp, i + 1, j - 2, step - 1)
                            + getValue(dp, i - 1, j - 2, step - 1) + getValue(dp, i - 2, j - 1, step - 1);
                }
            }
        }
        return dp[0][0][k];
    }

    // 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
    public static int getValue(int[][][] dp, int i, int j, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int k = 10;
        System.out.println(horse1(x, y, k));
        System.out.println(horse2(x, y, k));
    }

}
