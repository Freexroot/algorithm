package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//递归，子序列
public class Code03_PrintAllSubsquences {
    //打印一个字符串的全部子序列
    public static ArrayList<String> sub(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        char[] chars = s.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        String pro = "";
        process1(chars, arrayList, 0, pro);
        return arrayList;
    }

    //输入参数，接收返回结果，下标，下标前缀
    private static void process1(char[] chars, ArrayList<String> arrayList, int index, String pro) {
        if (index == chars.length) {
            arrayList.add(pro);
            return;
        }
            process1(chars, arrayList, index + 1, pro);
            process1(chars, arrayList, index + 1, pro + chars[index]);
    }

    //打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    public static ArrayList<String> subsNoRepeat(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        char[] chars = s.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> set =new HashSet();
        String pro = "";
        process2(chars, set, 0, pro);
        for (String s1 : set) {
            arrayList.add(s1);
        }
        return arrayList;
    }

    //输入参数，接收返回结果，下标，下标前缀
    private static void process2(char[] chars, Set<String> arrayList, int index, String pro) {
        if (index == chars.length) {
            arrayList.add(pro);
            return;
        }
        process2(chars, arrayList, index + 1, pro);
        process2(chars, arrayList, index + 1, pro + chars[index]);
    }
    public static void main(String[] args) {
        String s = "abc";
        ArrayList<String> sub = sub(s);
        ArrayList<String> subsNoRepeat = subsNoRepeat(s);

        for (String s1 : sub) {
            System.out.println(s1);
        }
        System.out.println("============");
        for (String s1 : subsNoRepeat) {
            System.out.println(s1);
        }
    }
}
