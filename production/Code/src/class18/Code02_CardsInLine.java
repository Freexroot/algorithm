package class18;

/*
* 给定一个整型数组arr，代表正数值不同的纸牌排成一条线
玩家A和玩家B依次拿走每张纸牌
规定玩家A先拿，玩家B后拿
但是每个玩家每次只能拿走最左或最右的纸牌
玩家A和玩家B都绝顶聪明
请返回最后获胜者的分数
* */
//暴力递归到动态规划
public class Code02_CardsInLine {
    public static int win1(int[] arr) {
        int fSum = f(arr, 0, arr.length - 1);
        int gSum = g(arr, 0, arr.length - 1);
        return Math.max(fSum, gSum);
    }

    //先手
    private static int f(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int p1 = arr[l] + g(arr, l + 1, r);
        int p2 = arr[r] + g(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    //后手
    private static int g(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int p1 = f(arr, l + 1, r);
        int p2 = f(arr, l, r - 1);
        return Math.min(p1, p2);
    }

    //在解法1的基础上增加了 缓存机制
    private static int win2(int[] arr) {
        int[][] fMap = new int[arr.length][arr.length];
        int[][] gMap = new int[arr.length][arr.length];
        //因为会相互调用，所以要放入两个变量负责先后手
        int fSum = f2(arr, 0, arr.length - 1, fMap, gMap);
        //第二次调用就很快，因为已经缓存好了
        int gSum = g2(arr, 0, arr.length - 1, fMap, gMap);
        return Math.max(fSum, gSum);
    }

    //先手
    private static int f2(int[] arr, int l, int r, int[][] fMap, int[][] gMap) {
        if (fMap[l][r] != 0) {
            return fMap[l][r];
        }
        int ans;
        if (l == r) {
            ans = arr[l];
        } else {
            int p1 = arr[l] + g2(arr, l + 1, r, fMap, gMap);
            int p2 = arr[r] + g2(arr, l, r - 1, fMap, gMap);
            ans = Math.max(p1, p2);
        }
        fMap[l][r] = ans;
        return ans;
    }

    //后手
    private static int g2(int[] arr, int l, int r, int[][] fMap, int[][] gMap) {
        if (gMap[l][r] != 0) {
            return gMap[l][r];
        }
        int ans;
        if (l == r) {
            ans = 0;
        } else {
            int p1 = f2(arr, l + 1, r, fMap, gMap);
            int p2 = f2(arr, l, r - 1, fMap, gMap);
            ans = Math.min(p1, p2);
        }
        gMap[l][r] = ans;
        return ans;
    }

    //动态规划
    private static int win3(int[] arr) {
        int[][] fMap = new int[arr.length][arr.length];
        int[][] gMap = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            fMap[i][i] = arr[i];
        }

        for (int i = 1; i < arr.length; i++) {
            int x = 0, y = i;
            while (y < arr.length) {
                fMap[x][y] = Math.max(arr[y] + gMap[x][y - 1], arr[x] + gMap[x + 1][y]);
                gMap[x][y] = Math.min(fMap[x + 1][y], fMap[x][y - 1]);
                x++;
                y++;
            }
        }
        return Math.max(fMap[0][arr.length - 1], gMap[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 7, 1, 4, 9, 11, 14, 1, 4, 10};
        System.out.println(win1(arr));
        System.out.println("========");
        System.out.println(win2(arr));
        System.out.println("========");
        System.out.println(win3(arr));

    }


}
