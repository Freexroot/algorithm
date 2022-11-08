package utils;

//进度条，输入总数和现在数字
public class ProgressBar {
    public static void progressBar(double timeMax, double timeNow) {
        //改数字可以改变进度条长度
        char[] t = new char[100];

        for (int i = 0; i < t.length; i++) {
            if (i < (int) timeNow / timeMax * t.length - 1)
                t[i] = '*';
            else
                t[i] = '-';
        }
        //结尾处理
        if (timeMax == timeNow) t[t.length - 1] = '*';
        //输出
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]);
        }
        System.out.println(" " + (int) (timeNow / timeMax * 100) + "%");
    }
}
