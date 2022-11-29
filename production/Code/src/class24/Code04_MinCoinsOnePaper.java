package class24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/*
动态规划中利用窗口内最大值或最小值更新结构做优化（难）
arr是货币数组，其中的值都是正数。再给定一个正数aim。
每个值都认为是一张货币，
返回组成aim的最少货币数
注意：因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
*/
public class Code04_MinCoinsOnePaper {
    //暴力递归1
    public static int minCoinsOnePaper1(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }
        int p = process1(arr, 0, aim);
        return p == Integer.MAX_VALUE ? 0 : p;
    }

    private static int process1(int[] arr, int index, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (index == arr.length) {
            return Integer.MAX_VALUE;
        }
        int p1 = process1(arr, index + 1, aim);
        int p2 = Integer.MAX_VALUE;
        int next = process1(arr, index + 1, aim - arr[index]);
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        return Math.min(p1, p2);
    }

    //暴力递归2-数据合并
    public static int minCoinsOnePaper2(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        //排序
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int i = 0;
        int[] key = new int[map.size()];
        int[] var = new int[map.size()];
        //分类储存
        for (Integer integer : map.keySet()) {
            key[i] = integer;
            var[i] = map.get(integer);
            i++;
        }
        int p = process2(key, var, 0, aim);
        return p == Integer.MAX_VALUE ? 0 : p;
    }

    private static int process2(int[] key, int[] var, int index, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (index == key.length) {
            return Integer.MAX_VALUE;
        }
        int p1 = Integer.MAX_VALUE;
        for (int i = 0; i <= var[index]; i++) {
            int next = process2(key, var, index + 1, aim - (i * key[index]));
            if (next != Integer.MAX_VALUE) {
                p1 = Math.min(p1, i + next);//i表示当前用的个数
            }
        }
        return p1;
    }

    //动态规划
    // dp2时间复杂度为：O(arr长度) + O(货币种数 * aim * 每种货币的平均张数)
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int index = 0;
        int[] key = new int[map.size()];
        int[] var = new int[map.size()];
        for (Integer integer : map.keySet()) {
            key[index] = integer;
            var[index] = map.get(integer);
            index++;
        }

        int n = key.length;
        int[][] dp = new int[n + 1][aim + 1];

        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }

        // 这三层for循环，时间复杂度为O(货币种数 * aim * 每种货币的平均张数)
        for (index = n - 1; index >= 0; index--) {
            for (int j = 0; j <= aim; j++) {

                int p1 = Integer.MAX_VALUE;
                for (int i = 0; i <= var[index]; i++) {
                    if (j >= i * key[index]) {
                        int next = dp[index + 1][j - (i * key[index])];
                        if (next != Integer.MAX_VALUE) {
                            p1 = Math.min(p1, i + next);//i表示当前用的个数
                        }
                    }
                }
                dp[index][j] = p1;
            }
        }

        return dp[0][aim] == Integer.MAX_VALUE ? 0 : dp[0][aim];
    }

    // dp3时间复杂度为：O(arr长度) + O(货币种数 * aim)
    // 优化需要用到窗口内最小值的更新结构
    public static int dp3(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int index = 0;
        int[] key = new int[map.size()];//面值
        int[] var = new int[map.size()];//张数
        for (Integer integer : map.keySet()) {
            key[index] = integer;
            var[index] = map.get(integer);
            index++;
        }

        int n = key.length;
        int[][] dp = new int[n + 1][aim + 1];

        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        //种数
        for (int i = n - 1; i >= 0; i--) {
            for (int mod = 0; mod < Math.min(aim + 1, key[i]); mod++) {
                // 当前面值 X
                // mod  mod + x   mod + 2*x   mod + 3 * x
                LinkedList<Integer> w = new LinkedList<>();
                w.add(mod);
                dp[i][mod] = dp[i + 1][mod];
                for (int r = mod + key[i]; r <= aim; r += key[i]) {
                    while (!w.isEmpty() && (dp[i + 1][w.peekLast()] == Integer.MAX_VALUE
                            || dp[i + 1][w.peekLast()] + compensate(w.peekLast(), r, key[i]) >= dp[i + 1][r])) {
                        w.pollLast();
                    }
                    w.addLast(r);
                    //根据张数，减去窗口的左边的值
                    int overdue = r - key[i] * (var[i] + 1);
                    if (w.peekFirst() == overdue) {
                        w.pollFirst();
                    }
                    dp[i][r] = dp[i + 1][w.peekFirst()] + compensate(w.peekFirst(), r, key[i]);
                }
            }
        }

        return dp[0][aim] == Integer.MAX_VALUE ? 0 : dp[0][aim];
    }

    //使用了多少张
    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }

    //下面是为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value)+1;//是0的话有方法dp2()bug
        }
        return arr;
    }

    public static void main(String[] args) {
        int value = 100;
        int lenMax = 50000;
        System.out.println("run code");
        for (int i = 0; i < lenMax; i++) {
            int len = (int) (Math.random() * 20);
            int[] arr = randomArray(len, value);
            int ram = (int) (Math.random() * 2 * value);
            int b1 = minCoinsOnePaper1(arr, ram);
            int b2 = minCoinsOnePaper2(arr, ram);
            int b3 = dp2(arr, ram);
            int b4 = dp3(arr, ram);
            if (b1 != b2 || b2 != b3 || b3 != b4 || b4 != b1) {
                System.out.println(Arrays.toString(arr) +":"+ram);
                System.out.println(b1);
                System.out.println(b2);
                System.out.println(b3);
                System.out.println(b4);
                dp2(arr, ram);
                dp3(arr, ram);
                System.out.println("error");
                return;
            }
        }
        System.out.println("end code");
    }
}
