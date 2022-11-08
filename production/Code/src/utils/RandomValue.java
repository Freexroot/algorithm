package utils;

public class RandomValue {
    //生成线段数组
    //minLine,MaxLine 线段的说下限。lenLine段数
    public static int[][] randomLines(int minLine, int MaxLine, int lenLine) {
        int[][] line = new int[lenLine][2];

        for (int i = 0; i < lenLine; i++) {
            if (Math.random() < 0.5) {
                minLine = minLine + (int) (Math.random() * 10);
            }
            //如果越界了，直接弹出
            if (minLine >= MaxLine) {
                int[][] line1 = new int[i][2];
                for (int i1 = 0; i1 < line1.length; i1++) {
                    line1[i1][0] = line[i1][0];
                    line1[i1][1] = line[i1][1];
                }
                return line1;
            }

            line[i][0] = minLine;
            line[i][1] = minLine + 1 + (int) (Math.random() * (MaxLine - minLine));


        }
        return line;
    }

    //随机生成String
    public static String randomString(int lenMax, String newStr) {
        String str = "";
        //打乱正确的答案
        if (!"".equals(newStr) && Math.random() < 0.3) {
            for (int i = 0; i < newStr.length(); i++) {
                if (Math.random() > 0.5) {
                    str = str + newStr.charAt(i);
                } else {
                    str = newStr.charAt(i) + str;
                }
            }
        }

        int len = (int) (Math.random() * lenMax);
        for (int i = 0; i < len; i++) {
            str = str + (char) ('a' + (int) (Math.random() * 26));
        }
        return str;
    }

    //随机生成int无序数组
    // negativeNum==true只有正值
    // fixedLen==true：数组长度等于lenMax
    public static int[] randomIntArrayDisorder(int lenMax, int valueMax, boolean negativeNum, boolean fixedLen) {
        int len = lenMax;
        //false就随机长度
        if (!fixedLen) {
            len = (int) (Math.random() * lenMax);
        }
        //长度为0返回空数组
        if (len == 0) {
            return new int[]{};
        }
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            if (negativeNum || Math.random() > 0.5) {
                arr[i] = (int) (Math.random() * valueMax);
            } else {
                arr[i] = -(int) (Math.random() * valueMax);
            }
        }
        return arr;
    }

    //随机生成int有序数组
    // negativeNum==true只有正值
    // fixedLen==true：数组长度等于lenMax
    public static int[] randomIntArrayOrder(int lenMax, int valueMax, boolean negativeNum, boolean fixedLen) {
        int len = lenMax;
        //false就随机长度
        if (!fixedLen) {
            len = (int) (Math.random() * lenMax);
        }
        //长度为0返回空数组
        if (len == 0) {
            return new int[]{};
        }
        int[] arr = new int[len];

        //数组第一位赋值
        if (negativeNum || Math.random() > 0.5) {
            arr[0] = (int) (Math.random() * valueMax);
        } else {
            arr[0] = -(int) (Math.random() * valueMax);
        }

        for (int i = 1; i < len; i++) {
            arr[i] = arr[i - 1] + (int) (Math.random() * valueMax / len);
        }
        return arr;
    }

    //二叉树
    public static class Node {
        int value;
        Node left;
        Node right;

        Node() {
        }

        Node(int v) {
            value = v;
        }
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }
}
