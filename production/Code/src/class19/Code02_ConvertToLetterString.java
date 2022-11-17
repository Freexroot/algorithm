package class19;

/*
规定1和A对应、2和B对应、3和C对应...26和Z对应
那么一个数字字符串比如"111”就可以转化为:
"AAA"、"KA"和"AK"
给定一个只有数字字符组成的字符串str，返回有多少种转化结果
* */
public class Code02_ConvertToLetterString {
    //递归
    private static int number(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process(s.toCharArray(), 0);
    }

    private static int process(char[] chars, int i) {
        if (chars.length == i) {
            return 1;
        }
        //如果i位置是0，意味着本次组合无效
        if (chars[i] == '0') {
            return 0;
        }
        //i位置是字母
        int a = process(chars, i + 1);
        //i和i+1位置是字母
        if (i + 1 < chars.length && chars[i] - '0' * 10 + chars[i + 1] - '0' <= 26) {
            a = +process(chars, i + 2);
        }
        return a;
    }

    private static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                //i位置是字母
                int a = dp[i + 1];
                //i和i+1位置是字母
                if (i + 1 < chars.length && chars[i] - '0' * 10 + chars[i + 1] - '0' <= 26) {
                    a = +dp[i + 2];
                }
                dp[i] = a;
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int len = 10000;
        int StrLen = 10000;

        for (int i = 0; i < len; i++) {
            String s = "" + (int) (Math.random() * StrLen);
            int ans0 = number(s);
            int ans1 = dp(s);
            if (ans0 != ans1) {
                System.out.println(ans0);
                System.out.println(ans1);
            }
        }
    }
}
