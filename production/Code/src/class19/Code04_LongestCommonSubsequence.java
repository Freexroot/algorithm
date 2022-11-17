package class19;

/*
* 给定两个字符串str1和str2，
返回这两个字符串的最长公共子序列长度
比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
最长公共子序列是“123456”，所以返回长度6
* */
// 链接：https://leetcode.cn/problems/longest-common-subsequence/

public class Code04_LongestCommonSubsequence {
    public static int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text2 == null || "".equals(text1) || "".equals(text2)) {
            return 0;
        }
        return porcess1(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }

    public static int porcess1(char[] text1, char[] text2, int a, int b) {
        if (a == 0 && b == 0) {
            return text1[a] == text2[b] ? 1 : 0;
        }
        if (a == 0) {
            //只要找到一个相同就可以返回了
            return text1[a] == text2[b] ? 1 : porcess1(text1, text2, a, b - 1);
        } else if (b == 0) {
            return text1[a] == text2[b] ? 1 : porcess1(text1, text2, a - 1, b);
        } else {
            //拆成4种可能性，其中的最大值就是答案
            int p1 = porcess1(text1, text2, a - 1, b);
            int p2 = porcess1(text1, text2, a, b - 1);
            int p3 = text1[a] == text2[b] ? 1 + porcess1(text1, text2, a - 1, b - 1) : 0;

            return Math.max(p1, Math.max(p2, p3));
        }
    }

    //动态规划
    public static int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null || "".equals(text1) || "".equals(text2)) {
            return 0;
        }
        int N1 = text1.length();
        int N2 = text2.length();
        int[][] dp = new int[text1.length()][text2.length()];
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        dp[0][0] = t1[0] == t2[0] ? 1 : 0;
        for (int i = 1; i < N2; i++) {
            dp[0][i] = t1[0] == t2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < N1; i++) {
            dp[i][0] = t1[i] == t2[0] ? 1 : dp[i - 1][0];
        }
        int p1, p2, p3;
        for (int i = 1; i < N1; i++) {
            for (int j = 1; j < N2; j++) {
                p1 = dp[i - 1][j];
                p2 = dp[i][j - 1];
                p3 = t1[i] == t2[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N1 - 1][N2 - 1];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence1("a12b3c456d", "1ef23ghi4j56k"));
        System.out.println(longestCommonSubsequence2("a12b3c456d", "1ef23ghi4j56k"));
    }
}
