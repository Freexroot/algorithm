package class21;

/*
给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
返回最小距离累加和
* */
//https://leetcode.cn/problems/0i0mDW/
public class Code01_MinPathSum {
    //暴力递归
    public static int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        return process1(0, 0, grid);
    }

    private static int process1(int x, int y, int[][] grid) {
        if (x >= grid.length || y >= grid[0].length) {
            return -1;
        }
        if (y == grid[0].length - 1 && x == grid.length - 1) {
            return grid[x][y];
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        int t1 = process1(x + 1, y, grid);
        int t2 = process1(x, y + 1, grid);
        if (t1 != -1) {
            p1 = grid[x][y] + t1;
        }
        if (t2 != -1) {
            p2 = grid[x][y] + t2;
        }

        return Math.min(p1, p2);
    }

    //动态规划
    public static int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        int dp[][] = new int[N][M];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[N - 1][M - 1];
    }

    //优化-一维数组实现
    public static int minPathSum3(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        int dp1[] = new int[N];

        dp1[0]  = grid[0][0];
        for (int i = 1; i < N; i++) {
            dp1[i] = dp1[i - 1] + grid[i][0];
        }

        for (int i = 1; i < M; i++) {
            dp1[0] = dp1[0] + grid[0][i];
            for (int j = 1; j < N; j++) {
                dp1[j] = grid[j][i] + Math.min(dp1[j-1],dp1[j]);
            }
        }

        return dp1[N - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,2,3},{4,5,6}};
        System.out.println(minPathSum1(grid));
        System.out.println(minPathSum2(grid));
        System.out.println(minPathSum3(grid));
    }
}
