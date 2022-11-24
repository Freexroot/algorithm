package class23;

/*
* 给定一个正数数组arr，
请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
返回最接近的情况下，较小集合的累加和
* */
public class Code01_SplitSumClosed {
    public static int splitSumClosed(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return process(arr, 0, sum / 2);
    }

    //返回靠近累加和一半的小集合
    private static int process(int[] arr, int index, int sum) {
        if (arr.length == index) {
            return 0;
        }

        int p1 = process(arr, index + 1, sum);
        int p2 = 0;
        if (arr[index] <= sum) {
            p2 = arr[index] + process(arr, index + 1, sum - arr[index]);
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
        sum/=2;
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j <= sum; j++) {

                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (arr[i] <= j) {
                    p2 = arr[i] + process(arr, i + 1, j - arr[i]);
                }

                dp[i][j]= Math.max(p1, p2);
            }
        }

        return dp[0][sum];
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
            int ans1 = splitSumClosed(arr);
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
