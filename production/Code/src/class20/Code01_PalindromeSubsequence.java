package class20;
/*
给定一个字符串str，返回这个字符串的最长回文子序列长度
比如 ： str = “a12b3c43def2ghi1kpm”
最长回文子序列是“1234321”或者“123c321”，返回长度7
* */
// 测试链接：https://leetcode.cn/problems/longest-palindromic-subsequence/

public class Code01_PalindromeSubsequence {

    //暴力递归
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process1(s.toCharArray(), 0, s.length() - 1);
    }

    //返回l~r之间的最大的回文数
    private static int process1(char[] arr, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l + 1 == r) {
            return arr[l] == arr[r] ? 2 : 1;
        }
        //这里没有写第4种可能性，int p4 = process1(arr, l + 1, r - 1); 因为可以约掉
        int p1 = process1(arr, l + 1, r);
        int p2 = process1(arr, l, r - 1);
        int p3 = arr[l] == arr[r] ? 2 + process1(arr, l + 1, r - 1) : 0;
        return Math.max(p1, Math.max(p2, p3));
    }

    //动态规划-范围模型
    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] arr = s.toCharArray();
        int[][] dp = new int[N][N];

        //小技巧，同时初始化2条对角线
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = arr[i] == arr[i + 1] ? 2 : 1;
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                int p1 = dp[i + 1][j];
                int p2 = dp[i][j - 1];
                int p3 = arr[i] == arr[j] ? 2 + dp[i + 1][j - 1] : 0;

                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s = "iefkjbakjfbgajdfiqhtw";
        System.out.println(longestPalindromeSubseq1(s));
        System.out.println(longestPalindromeSubseq2(s));
    }
}
