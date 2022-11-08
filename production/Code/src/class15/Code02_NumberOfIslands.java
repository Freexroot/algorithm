package class15;

//https://leetcode.cn/problems/number-of-islands/
public class Code02_NumberOfIslands {
    //感染
    public int numIslands1(char[][] grid) {
        int size = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    size++;
                    infected(grid, i, j);
                }
            }
        }
        return size;
    }

    public void infected(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        infected(grid, i + 1, j);
        infected(grid, i, j + 1);
        infected(grid, i - 1, j);
        infected(grid, i, j - 1);
    }

    //并查集,数组
    public int numIslands(char[][] grid) {
        Union union = new Union(grid);
        int iIndex, jIndex, zIndex, a, b;
        //最右一列
        for (int i = 1; i < grid.length; i++) {
            if (grid[i - 1][grid[0].length - 1] == '1' && grid[i][grid[0].length - 1] == '1') {//要先判断在合并，不然会报错
                iIndex = union.index(grid, i - 1, grid[0].length - 1);
                jIndex = union.index(grid, i, grid[0].length - 1);
                union.union(grid, iIndex, jIndex);
            }
        }
        //最下一行
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[grid.length - 1][i - 1] == '1' && grid[grid.length - 1][i] == '1') {
                iIndex = union.index(grid, grid.length - 1, i - 1);
                jIndex = union.index(grid, grid.length - 1, i);
                union.union(grid, iIndex, jIndex);
            }
        }
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = 0; j < grid[0].length - 1; j++) {
                iIndex = union.index(grid, i, j);
                jIndex = union.index(grid, i, j + 1);
                zIndex = union.index(grid, i + 1, j);
                if (grid[i][j] == '1' && grid[i][j + 1] == '1')
                    union.union(grid, iIndex, jIndex);//自己和右

                if (grid[i][j] == '1' && grid[i + 1][j] == '1')
                    union.union(grid, iIndex, zIndex);//自己和下

            }
        }
        return union.sets;
    }

    class Union {
        int[] parents;
        int[] size;
        int[] help;//辅助数组
        int sets = 0;

        public Union(char[][] grid) {
            parents = new int[grid.length * grid[0].length];
            size = new int[parents.length];
            help = new int[parents.length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        sets++;
                        int t = index(grid, i, j);
                        parents[t] = t;
                        size[t] = 1;
                    }
                }
            }
        }

        //二维下标转一维下标
        public int index(char[][] grid, int i, int j) {
            return i * grid[0].length + j;
        }

        public int find(int a) {
            int i = 0;
            while (a != parents[a]) {
                //System.out.println(help.length+",i="+i+",a="+a+",parents="+parents[a]);
                help[i++] = a;
                a = parents[a];
            }

            for (i--; i > 0; i--) {//记得i--
                parents[help[i]] = a;
            }
            return a;
        }

        //传入二维坐标的一维表示
        public void union(char[][] arr, int i, int j) {
            int iHead = find(i);
            int jHead = find(j);
            if (iHead != jHead) {
                sets--;
                int big = size[iHead] > size[jHead] ? iHead : jHead;
                int small = big == iHead ? jHead : iHead;
                parents[small] = big;
                size[big] += size[small];
            }
        }
    }

}

class test {
    public static char[][] ran(int maxLen) {
        int a = (int) (Math.random() * maxLen) + 1;
        int b = (int) (Math.random() * maxLen) + 1;
        double c = Math.random();
        char arr[][] = new char[a][b];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (Math.random() > c) {
                    arr[i][j] = '1';
                } else {
                    arr[i][j] = '0';
                }
            }
        }
        return arr;
    }

    public static char[][] copy(char[][] arr) {
        char[][] arr1 = new char[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = 0; i1 < arr[0].length; i1++) {
                arr1[i][i1] = arr[i][i1];
            }
        }
        return arr1;
    }

    public static void main(String[] args) {
        int len = 10000;
        System.out.println("run code");
        for (int i = 0; i < len; i++) {
            char[][] arr = ran(5);
            char[][] arr1 = copy(arr);

            Code02_NumberOfIslands x = new Code02_NumberOfIslands();

            int d1 = x.numIslands(arr);
            int d2 = x.numIslands1(arr1);
            if (d1 != d2) {
                x.numIslands(arr);
                System.out.println("error code " + d1 + " " + d2);

            }
        }
        System.out.println("end code");
    }
}