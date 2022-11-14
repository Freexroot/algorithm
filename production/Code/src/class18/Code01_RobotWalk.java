package class18;

/*
假设有排成一行的N个位置记为1~N，N一定大于或等于2
开始时机器人在其中的M位置上(M一定是1~N中的一个)
如果机器人来到1位置，那么下一步只能往右来到2位置；
如果机器人来到N位置，那么下一步只能往左来到N-1位置；
如果机器人来到中间位置，那么下一步可以往左走或者往右走；
规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
给定四个参数 N、M、K、P，返回方法数
*/
//暴力递归到动态规划
public class Code01_RobotWalk {
    //普通迭代.
    //N长度，M现在位置，K移动步数，P目的地
    public static int ways1(int N, int M, int K, int P) {
        return process1(N, M, K, P);
    }

    private static int process1(int n, int m, int k, int p) {
        if (k == 0) {
            return m == p ? 1 : 0;
        }
        if (m == 1) {
            return process1(n, m + 1, k - 1, p);
        }
        if (m == n) {
            return process1(n, m - 1, k - 1, p);
        }
        return process1(n, m + 1, k - 1, p) + process1(n, m - 1, k - 1, p);
    }

    //在解法1的基础上增加了 缓存机制
    //N长度，M现在位置，K移动步数，P目的地
    public static int ways2(int N, int M, int K, int P) {
        int[][] cache = new int[N + 1][K + 1];
        return process2(N, M, K, P, cache);
    }

    private static int process2(int n, int m, int k, int p, int[][] cache) {
        if (cache[m][k] != 0) {
            return cache[m][k];
        }
        int ans;
        if (k == 0) {
            System.out.println(m + "  " + p);
            ans = m == p ? 1 : 0;
        } else if (m == 1) {
            ans = process1(n, m + 1, k - 1, p);
        } else if (m == n) {
            ans = process1(n, m - 1, k - 1, p);
        } else {
            ans = process1(n, m + 1, k - 1, p) + process1(n, m - 1, k - 1, p);
        }
        cache[m][k] = ans;
        return ans;
    }

    //动态规划
    public static int ways3(int N, int M, int K, int P) {
        //纵为长度，横为步数
        int[][] cache = new int[N + 1][K + 1];
        cache[P][0]=1;//相遇的地方

        for (int i = 1; i <= K; i++) {
            //1
            cache[1][i]=cache[2][i-1];
            //2~n-1
            for (int i1 = 2; i1 < N; i1++) {
                cache[i1][i]=cache[i1-1][i-1]+cache[i1+1][i-1];
            }
            //n
            cache[N][i]=cache[N-1][i-1];

        }
        return cache[M][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(8, 5, 10, 7));
        System.out.println(ways2(8, 5, 10, 7));
        System.out.println(ways3(8, 5, 10, 7));
    }
}
