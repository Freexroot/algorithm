package class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

//贪心，在字符串数组里，排列组合出ASCII值最小的组合
public class Code05_LowestLexicography {
    //贪心，比较器
    public static String lowestLexicography1(String[] strings) {
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        String str = "";
        for (int i = 0; i < strings.length; i++) {
            str += strings[i];
        }
        return str;
    }


    //递归
    public static String lowestLexicography2(String[] strings) {
        if(strings.length==0||strings==null){
            return "";
        }
        return porcess(strings);
    }

    public static String porcess(String[] strings) {
        if (strings.length == 1) {
            return strings[0];
        }

        String strMax = "";
        int iMax=0, jMax=1;

        for (int i = 0; i < strings.length; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (strMax.compareTo(strings[i] + strings[j]) > 0||strMax.compareTo(strings[j] + strings[i]) > 0) {
                    strMax=strings[i] + strings[j];
                    iMax=i; jMax=j;
                }
            }
        }
        return strMax+porcess(mergeStr(strings, iMax, jMax));
    }
    // 暴力
    public static String lowestLexicography3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }
    // strs中所有字符串全排列，返回所有可能的结果
    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    public static String[] mergeStr(String[] str, int i, int j) {
        String[] str1 = new String[str.length - 1];
        str1[0] = str[i] + str[j];
        int j1 = 1;
        for (int i1 = 0; i1 < str.length; i1++) {
            if (i1 != i && i1 != j)
                str1[j1] = str[i1];
        }
        return str1;
    }

    //随机生成字符串数组，数组长度,单个字符串长度
    public static String[] RandomString(int maxLen, int maxValLen) {
        String[] strings = new String[(int) (Math.random() * maxLen)];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = "";
            for (int i1 = 0; i1 < maxValLen; i1++) {
                strings[i] = strings[i] + (Math.random() > 0.8 ? "" : (char) ('a' + (int) (Math.random() * 26)));
            }
        }

        return strings;
    }

    public static void main(String[] args) {
        int maxLen = 4;//数组长度
        int maxValLen = 7;//单个字符串长度
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            String[] strings = RandomString(maxLen, maxValLen);
            String str1 = lowestLexicography1(strings);
            String str2 = lowestLexicography2(strings);
            if (!str1.equals(str2)) {
                System.out.println("Oops!");
                lowestLexicography2(strings);
            }
        }
        System.out.println("finish!");
    }
}
