package class08;

public class Code03_CountSort_test01 {
    public static int hIndex(int[] citations) {
        if (citations.length == 1) {
            if (citations[0] == 0) {
                return 0;
            }
            return 1;
        }

        int arr[] = new int[10001];
        int len = citations.length;
        for (int i = 0; i < len; i++) {
            arr[citations[i]]++;
        }

        int t = (len % 2 == 0) ? len / 2 : len / 2 + 1;

        for (int i = 1; i <= len; i++) {
            if (arr[i] != 0) {
                if (arr[i] < t) {
                    t = t - arr[i];
                } else {
                    return i;
                }
            }
        }


        return 0;
    }

    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{0, 1}));
    }
}
