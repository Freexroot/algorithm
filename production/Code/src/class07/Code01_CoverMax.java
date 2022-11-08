package class07;

import utils.DataTest;
import utils.ProgressBar;
import utils.RandomValue;

import java.util.PriorityQueue;

//线段最大重合数
//加强堆
public class Code01_CoverMax {
    //暴力
    public static int maxCover1(int[][] lines) {
        //求线段左右边界
        int maxLine = Integer.MIN_VALUE;
        int minLine = Integer.MAX_VALUE;
        for (int i = 0; i < lines.length; i++) {
            minLine = Integer.min(lines[i][0], minLine);
            maxLine = Integer.max(lines[i][1], maxLine);
        }
        int sum = 0;
        for (double i = minLine + 0.5; i < maxLine; i++) {
            int count = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] < i && lines[j][1] > i) {
                    count++;
                }
            }
            sum = Integer.max(count, sum);
        }
        return sum;
    }

    //加强堆,N*logN
    public static int maxCover2(int[][] lines) {
        //小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int sum = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i][0]) {
                heap.poll();
            }
            heap.add(lines[i][1]);
            sum = Integer.max(sum, heap.size());
        }
        return sum;
    }


    public static void main(String[] args) {
        DataTest dataTest1 = new DataTest();
        DataTest dataTest2 = new DataTest();

        for (int i = 0; i < 100; i++) {
            ProgressBar.progressBar(100,i);
            int[][] ints = RandomValue.randomLines((int) (Math.random() * 10), (int) (Math.random() * 10000), 1000);
            dataTest1.startTime();
            int i1 = maxCover1(ints);
            dataTest1.endTime();
            dataTest2.startTime();
            int i2 = maxCover2(ints);
            dataTest2.endTime();
            if(i1!=i2){
                System.out.println(i1+" "+i2+" "+(i1==i2));
                return;
            }
        }
        //平均实际
        dataTest1.runTime();
        dataTest2.runTime();
    }
}
