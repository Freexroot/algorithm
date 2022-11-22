package class22;

import utils.ProgressBar;

/*
给定3个参数，N，M，K
怪兽有N滴血，等着英雄来砍自己
英雄每一次打击，都会让怪兽流失[0~M]的血量
到底流失多少？每一次在[0~M]上等概率的获得一个值
求K次打击之后，英雄把怪兽砍死的概率
* */
public class Code01_KillMonster {
    public static double killMonster(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return 0;
        }
        return porcess(N, M, K) / Math.pow(M + 1, K);
    }

    private static double porcess(int n, int m, int k) {
        if (n <= 0) {
            return Math.pow(m + 1, k);
        }
        if (k == 0) {
            return 0;
        }
        int ways = 0;
        for (int i = 0; i <= m; i++) {
            ways += porcess(n - i, m, k - 1);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 0; i <= K; i++) {
            dp[0][i] = (int) Math.pow(M + 1, i);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                int ways = 0;
                for (int k = 0; k <= M; k++) {
                    //负值的情况也要加进去，而不是跳过
                    if (i - k < 0) {
                        ways += (int) Math.pow(M + 1, j - 1);
                    } else {
                        ways += dp[i - k][j - 1];
                    }
                }
                dp[i][j] = ways;
            }
        }
        return dp[N][K] / Math.pow(M + 1, K);
    }

    //优化写法
    public static double dp2(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 0; i <= K; i++) {
            dp[0][i] = (int) Math.pow(M + 1, i);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                //负值的情况也要加进去，而不是跳过
                if (i - M < 0) {
                    dp[i][j] += (int) Math.pow(M + 1, j - 1);
                } else {
                    dp[i][j] += dp[i - M][j - 1];
                }
            }
        }
        return dp[N][K] / Math.pow(M + 1, K);
    }


    public static void main(String[] args) {
        int lenMax = 1000;
        int ran = 10;
        System.out.println("open");
        for (int i = 0; i < lenMax; i++) {
            ProgressBar.progressBar(lenMax, i);
            int n = (int) (Math.random() * ran);
            int m = (int) (Math.random() * ran);
            int k = (int) (Math.random() * ran);

            double p1 = killMonster(n, m, k);
            double p2 = dp1(n, m, k);
            double p3 = dp2(n, m, k);
            if (p1 != p2 && p1 != p3) {
                System.out.println("error");
                System.out.println(n + "  " + m + "  " + k);
                System.out.println(p1);
                System.out.println(p2);
                System.out.println(p3);
                return;
            }
        }
        System.out.println("ok");
    }
}
