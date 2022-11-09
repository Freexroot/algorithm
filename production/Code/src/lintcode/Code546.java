package lintcode;

import java.util.Arrays;
import java.util.Comparator;

//https://www.lintcode.com/problem/846/?showListFe=true&page=1&tagIds=356&pageSize=50
public class Code546 {
    /**
     * @param array: the input array
     * @return: the sorted array
     */
    public int[][] multiSort(int[][] array) {
        // Write your code here
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : (o2[1] - o1[1]);
            }
        });
        return array;
    }
}
