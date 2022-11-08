package utils;

//运算平均时间
public class DataTest {
    long runTime;
    long start;
    long endTime;
    long count=0;
    //开始
    public void startTime() {
        this.start = System.currentTimeMillis();
        count++;
    }
    //结束
    public void endTime() {
        this.endTime = System.currentTimeMillis();
        this.runTime +=this.endTime-this.start;
    }
    //开始计算
    public void runTime() {
        System.out.println("当前耗费时间：" + runTime/count + "ms");
    }
}
