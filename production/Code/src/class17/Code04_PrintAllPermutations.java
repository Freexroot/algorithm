package class17;

import java.util.ArrayList;
import java.util.List;

//递归，全排列
public class Code04_PrintAllPermutations {
    //打印一个字符串的全部排列
    public static List<String> per(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        char[] chars = s.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        String pro = "";
        process1(chars, arrayList, 0);
        return arrayList;
    }

    private static void process1(char[] chars, ArrayList<String> arrayList, int index) {
        if (index == chars.length) {
            arrayList.add(String.valueOf(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, i, index);
            process1(chars, arrayList, index + 1);
            swap(chars, i, index);
        }
    }

    //打印一个字符串的全部排列，要求不要出现重复的排列
    public static List<String> perNoRepeat(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        char[] chars = s.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        String pro = "";
        process2(chars, arrayList, 0);
        return arrayList;
    }

    private static void process2(char[] chars, ArrayList<String> arrayList, int index) {
        if (index == chars.length) {
            arrayList.add(String.valueOf(chars));
            return;
        }
        boolean[] b = new boolean[256];

        for (int i = index; i < chars.length; i++) {
            if (!b[chars[i]]) {
                b[chars[i]] = true;
                swap(chars, i, index);
                process2(chars, arrayList, index + 1);
                swap(chars, i, index);
            }
        }
    }

    public static void swap(char[] c, int a, int b) {
        char t = c[a];
        c[a] = c[b];
        c[b] = t;
    }

    public static void main(String[] args) {
        String s = "abcc";
        List<String> per = per(s);
        List<String> perNoRepeat = perNoRepeat(s);

        for (String s1 : per) {
            System.out.println(s1);
        }
        System.out.println("=====================");
        for (String s1 : perNoRepeat) {
            System.out.println(s1);
        }
    }
}
