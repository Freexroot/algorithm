package class22;

/*
给定一个正数n，求n的裂开方法数，
规定：后面的数不能比前面的数小
比如4的裂开方法有：
1+1+1+1、1+1+2、1+3、2+2、4
5种，所以返回5
* */
public class Code03_SplitNumber {

    public static int splitNumber(int n) {
        return porcess(1, n);
    }

    private static int porcess(int index, int n) {
        if (n == 0) {
            return 1;
        }
        if (index > n) {
            return 0;
        }

        int ways = 0;
        for (int i = index; i <= n; i++) {
            ways += porcess(i, n - i);
        }
        return ways;
    }

    public static int dp1(int n) {
        //减数，剩下的数
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;//i=i时一定是1
        }

        for (int i = n-1; i > 0; i--) {
            for (int j = i+1; j <= n; j++) {

                int ways = 0;
                for (int k = i; k <= j; k++) {
                    ways += dp[k][j - k];
                }
                dp[i][j] = ways;

            }
        }
        return dp[1][n];
    }

    public static int dp2(int n) {
        //减数，剩下的数
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;//i=i时一定是1
        }

        for (int i = n-1; i > 0; i--) {
            for (int j = i+1; j <= n; j++) {
                dp[i][j]=dp[i+1][j];
                if(i<=j){
                    dp[i][j]+=dp[i][j-i];
                }

                int ways = 0;
                for (int k = i; k <= j; k++) {
                    ways += dp[k][j - k];
                }
                dp[i][j] = ways;

            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        for (int i = 1; i < 40; i++) {
            int p1 = splitNumber(i);
            int p2 = dp1(i);
            int p3 = dp2(i);
            if(p1!=p2&&p1!=p3){
                System.out.println(p1+" "+p2);
                return;
            }
        }

    }


}
