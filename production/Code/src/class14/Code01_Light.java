package class14;

/*给定一个字符串str，只由‘×’和‘.’两种字符构成。

‘×’表示墙，不能放灯，也不需要点亮
‘.’ 表示居民点，可以放灯，需要点亮
如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
*/
public class Code01_Light {
    //循环
    public static int minLight1(String strings) {
        if (strings == null || strings.length() == 0) {
            return 0;
        }
        int lightMin = 0;
        for (int i = 0; i < strings.length(); ) {
            if ('X' == strings.charAt(i)) {
                i++;
            } else {
                lightMin++;
                if (i == strings.length() - 1) {//越界处理
                    break;
                }
                if ('X' == strings.charAt(i + 1)) {
                    i += 2;
                } else {
                    i += 3;
                }
            }
        }
        return lightMin;
    }

    //递归
    public static int minLight2(String road) {
        return 0;
    }

    // 更简洁的解法
    // 两个X之间，数一下.的数量，然后除以3，向上取整
    // 把灯数累加
    public static int minLight3(String road) {
        char[] str = road.toCharArray();
        int cur = 0;
        int light = 0;
        for (char c : str) {
            if (c == 'X') {
                light += (cur + 2) / 3;
                cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
    }

    public static String randomString(int lenMax) {
        int len = (int) (Math.random() * lenMax);
        String str = "";
        double probability = Math.random();
        for (int i = 0; i < len; i++) {
            if (Math.random() > probability) {
                str = str + "X";
            } else {
                str = str + ".";
            }
        }
        return str;
    }

    public static void main(String[] args) {
        int lenMax = 100;
        int testTime = 1000;
        System.out.println();
        for (int i = 0; i < testTime; i++) {
            String s = randomString(lenMax);
            if (minLight1(s) != minLight3(s)) {
                minLight1(s);
                minLight3(s);
                System.out.println("no");
            }
        }
        System.out.println("yes");
    }
}
