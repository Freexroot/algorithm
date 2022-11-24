package class23;

/*
* 给定一个正数数组arr，请把arr中所有的数分成两个集合
如果arr长度为偶数，两个集合包含数的个数要一样多
如果arr长度为奇数，两个集合包含数的个数必须只差一个
请尽量让两个集合的累加和接近
返回最接近的情况下，较小集合的累加和
* */
public class Code02_SplitSumClosedSizeHalf {
    public static int splitSumClosedSizeHalf(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        sum /= 2;
        int n = arr.length;
        //奇数
        if ((n & 1) == 1) {
            return Math.min(process(arr, 0, n / 2, sum), process(arr, 0, n / 2 + 1, sum));
        } else {
            return process(arr, 0, n / 2, sum);
        }

    }

    //在arr中返回长度为picks并且尽可能接近sum的数
    private static int process(int[] arr, int index, int picks, int sum) {
        if (picks == 0) {
            return 0;
        }
        if (index == arr.length) {
            return 0;
        }
        int p1 = process(arr, index + 1, picks, sum);
        int p2 = 0;
        if (sum >= arr[index]) {
            p2 = arr[index] + process(arr, index + 1, picks - 1, sum - arr[index]);
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        sum /= 2;//总数的一半
        int n = arr.length;
        int m = (n + 1) / 2;//不管奇数偶数都是一样的

        int[][][] dp = new int[n + 1][m + 1][sum + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int picks = 1; picks <= m; picks++) {
                for (int i = 0; i <= sum; i++) {
                    int p1 = dp[index + 1][picks][i];
                    int p2 = 0;
                    if (i >= arr[index]) {
                        p2 = arr[index] + dp[index + 1][picks - 1][i - arr[index]];
                    }
                    dp[index][picks][i] = Math.max(p1, p2);
                }
            }
        }

        //奇数
        if ((n & 1) == 1) {
            return Math.min(dp[0][n / 2][sum], dp[0][n / 2 + 1][sum]);
        } else {
            return dp[0][n / 2][sum];
        }
    }

    //下面是为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = splitSumClosedSizeHalf(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
