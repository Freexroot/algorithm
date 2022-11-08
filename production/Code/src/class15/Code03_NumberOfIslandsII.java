package class15;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//305. 岛屿数量 II
public class Code03_NumberOfIslandsII {
    //并查集
    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        int[][] matrix = new int[m][n];
        List<Integer> list = new ArrayList<>();
        Union union = new Union(m, n);
        for (int i = 0; i < positions[0].length; i++) {
            list.add(union.connect(positions[0][i], positions[1][i]));
        }
        return list;
    }

    static class Union {
        int[] patents;
        int[] size;
        int[] help;
        int sets;
        private final int row;//行数
        private final int col;

        public Union(int m, int n) {
            int len = m * n;
            patents = new int[len];
            size = new int[len];
            help = new int[len];
            sets = 0;
            row = m;
            col = n;
        }

        public int connect(int a, int b) {
            int i = index(a, b);
            if (size[i] == 0) {//判断是否反复添加重复值
                size[i] = 1;
                patents[i] = i;
                sets++;
                union(a, b, a + 1, b);
                union(a, b, a, b + 1);
                union(a, b, a - 1, b);
                union(a, b, a, b - 1);
            }
            return sets;
        }

        public int find(int a) {
            int i = 0;
            while (a != patents[a]) {
                help[i++] = a;
                a = patents[a];
            }
            for (i--; i > 0; i--) {
                patents[help[i]] = a;
            }
            return a;
        }

        public void union(int a1, int b1, int a2, int b2) {
            //越界
            if (a1 < 0 || a1 == row || a2 < 0 || a2 == row || b1 < 0 || b1 == col || b2 < 0 || b2 == col) {
                return;
            }
            int aIndex = index(a1, b1);
            int bIndex = index(a2, b2);
            int aHead = find(aIndex);
            int bHead = find(bIndex);
            if (size[bIndex] == 0 || aHead == bHead) {
                return;
            }
            sets--;
            int big = size[aIndex] > size[bIndex] ? aHead : bHead;
            int small = big == aHead ? bHead : aHead;
            patents[small] = big;
            size[big] += size[small];
        }

        //二维下标转一维下标
        public int index(int i, int j) {
            return i * col + j;
        }
    }

    //抄的 并查集 数组
    public static List<Integer> numIslands22(int m, int n, int[][] positions) {
        UnionFind1 uf = new UnionFind1(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < positions[0].length; i++) {
            ans.add(uf.connect(positions[0][i], positions[1][i]));

        }
        return ans;
    }

    public static class UnionFind1 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind1(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }

    }
    //随机生成数组
    public static int[][] ran(int maxLen, int m, int n) {
        int arr[][] = new int[2][(int) (Math.random() * maxLen)];

        for (int i = 0; i < arr[0].length; i++) {
            arr[0][i] = (int) (Math.random() * m);
            arr[1][i] = (int) (Math.random() * n);
        }
        return arr;
    }
    //复制数组
    public static int[][] copy(int[][] arr) {
        int[][] arr1 = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = 0; i1 < arr[0].length; i1++) {
                arr1[i][i1] = arr[i][i1];
            }
        }
        return arr1;
    }
    //比较数组
    public static boolean arrCompare(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int len = 10000;
        System.out.println("run code");
        for (int i = 0; i < len; i++) {
            int m = (int) (Math.random() * 500) + 1;
            int n = (int) (Math.random() * 500) + 1;
            int nm = (int) (Math.random() * (m * n));
            int[][] arr = ran(nm, m, n);
            int[][] arr1 = copy(arr);

            List<Integer> list1 = numIslands21(m, n, arr);
            List<Integer> list2 = numIslands22(m, n, arr);

            if (!arrCompare(list1, list2)) {
                numIslands21(m, n, arr);
                System.out.println("error code ");
            }
        }
        System.out.println("end code");
    }
}
