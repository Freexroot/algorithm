package class21;

/*
* 给定5个参数，N，M，row，col，k
表示在N*M的区域上，醉汉Bob初始在(row,col)位置
Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
任何时候Bob只要离开N*M的区域，就直接死亡
返回k步之后，Bob还在N*M的区域的概率
* */
public class Code05_BobDie {
    public static double livePosibility1(int row, int col, int k, int N, int M) {
        return process(row, col, k, N, M) / Math.pow(4, k);
    }

    private static long process(int row, int col, int k, int n, int m) {
        if (row < 0 || row > n || col < 0 || col > m) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        long p1 = process(row - 1, col, k - 1, n, m);
        long p2 = process(row, col - 1, k - 1, n, m);
        long p3 = process(row + 1, col, k - 1, n, m);
        long p4 = process(row, col + 1, k - 1, n, m);
        return p1 + p2 + p3 + p4;
    }

    public static double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[k + 1][N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[0][i][j] = 1;
            }
        }
        for (int z = 1; z <= k; z++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[z][i][j] = pick(dp, N, M, i - 1, j, z - 1);
                    dp[z][i][j] += pick(dp, N, M, i + 1, j, z - 1);
                    dp[z][i][j] += pick(dp, N, M, i, j - 1, z - 1);
                    dp[z][i][j] += pick(dp, N, M, i, j + 1, z - 1);
                }
            }
        }

        return dp[k][row][col] / Math.pow(4, k);
    }

    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[rest][r][c];
    }

    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }
}
