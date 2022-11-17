package class19;

import java.util.HashMap;

/*
* 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
返回需要至少多少张贴纸可以完成这个任务
例子：str= "babac"，arr = {"ba","c","abcd"}
ba + ba + c  3  abcd + abcd 2  abcd+ba 2
* */
// 本题测试链接：https://leetcode.cn/problems/stickers-to-spell-word

public class Code03_StickersToSpellWord {
    //暴力递归
    public static int minStickers1(String[] stickers, String target) {
        if (stickers.length == 0 || target == null || target == "") {
            return -1;
        }
        int ans = process1(stickers, target);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    public static int process1(String[] stickers, String target) {
        if (target.equals("")) {
            return 0;
        }
        String str = "";
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            str = minus(stickers[i], target);
            if (str.length() != target.length()) {
                min = Math.min(min, process1(stickers, str));
            }
        }
        //返回Integer.MAX_VALUE表示此次所有的贴纸都无效
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    //从target中剔除sticker
    public static String minus(String sticker, String target) {
        int[] chars = new int[26];
        char[] s = sticker.toCharArray();
        char[] t = target.toCharArray();
        for (int i = 0; i < target.length(); i++) {
            chars[(t[i] - 'a')]++;
        }

        for (int i = 0; i < sticker.length(); i++) {
            chars[(s[i] - 'a')]--;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < chars.length; ) {
            if (chars[i] > 0) {
                str.append((char) (i + 'a'));
                chars[i]--;
            } else {
                i++;
            }
        }
        return str.toString();
    }

    //优化递归
    public static int minStickers2(String[] stickers, String target) {
        if (stickers.length == 0 || target == null || target == "") {
            return -1;
        }
        //String转成int表达
        int[][] arr = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (int i1 = 0; i1 < stickers[i].length(); i1++) {
                arr[i][chars[i1] - 'a']++;
            }
        }

        int ans = process2(arr, target);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    public static int process2(int[][] stickers, String target) {
        if (target.equals("")) {
            return 0;
        }
        String str = "";
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            //如果首字母在stickers里
            if (stickers[i][target.charAt(0) - 'a'] > 0) {
                //删除贴纸中右的字母
                char[] tChar = target.toCharArray();
                int[] tInt = new int[26];
                for (int i1 = 0; i1 < target.length(); i1++) {
                    tInt[tChar[i1] - 'a']++;
                }

                StringBuilder stringBuilder = new StringBuilder();
                for (int i1 = 0; i1 < 26; i1++) {
                    if (tInt[i1] > 0) {
                        //可能减0
                        tInt[i1] -= stickers[i][i1];
                        for (int i2 = 0; i2 < tInt[i1]; i2++) {
                            stringBuilder.append((char) (i1 + 'a'));
                        }
                    }
                }
                str = stringBuilder.toString();
                min = Math.min(min, process2(stickers, str));
            }
        }
        //返回Integer.MAX_VALUE表示此次所有的贴纸都无效
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    //动态规划
    public static int minStickers3(String[] stickers, String target) {
        if (stickers.length == 0 || target == null || target == "") {
            return -1;
        }
        //String转成int表达
        int[][] arr = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (int i1 = 0; i1 < stickers[i].length(); i1++) {
                arr[i][chars[i1] - 'a']++;
            }
        }

        int ans = process3(arr, target, new HashMap<String, Integer>());
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    public static int process3(int[][] stickers, String target, HashMap<String, Integer> map) {
        if (target.equals("")) {
            return 0;
        }
        String str = "";
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            //如果首字母在stickers里
            if (stickers[i][target.charAt(0) - 'a'] > 0) {
                //删除贴纸中右的字母
                char[] tChar = target.toCharArray();
                int[] tInt = new int[26];
                for (int i1 = 0; i1 < target.length(); i1++) {
                    tInt[tChar[i1] - 'a']++;
                }

                StringBuilder stringBuilder = new StringBuilder();
                for (int i1 = 0; i1 < 26; i1++) {
                    if (tInt[i1] > 0) {
                        //可能减0
                        tInt[i1] -= stickers[i][i1];
                        for (int i2 = 0; i2 < tInt[i1]; i2++) {
                            stringBuilder.append((char) (i1 + 'a'));
                        }
                    }
                }
                str = stringBuilder.toString();
                //如果出现bu相同的str才进入递归，否则直接从map里取
                if (!map.containsKey(str)) {
                    map.put(str, process3(stickers, str, map));
                }
                min = Math.min(min, map.get(str));
            }
        }
        //返回Integer.MAX_VALUE表示此次所有的贴纸都无效
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static void main(String[] args) {
        System.out.println(minStickers1(new String[]{"with", "example", "science"}, "thehat"));
        System.out.println(minStickers2(new String[]{"with", "example", "science"}, "thehat"));
        System.out.println(minStickers3(new String[]{"with", "example", "science"}, "thehat"));
    }
}
