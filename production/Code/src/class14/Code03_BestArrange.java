package class14;

import java.util.Arrays;
import java.util.Comparator;

/*
一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。给你每一个项目开始的时间和结束的时间
你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。返回最多的宣讲场次。
* */
public class Code03_BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //暴力，迭代
    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return porcess(programs, 0, 0);
    }

    //sum是会议次数，timeLine是上一次会议结束的时间
    private static int porcess(Program[] programs, int sum, int timeLine) {
        if (programs.length == 0) {
            return sum;
        }
        int max=sum;//一定要设max值，不然值会累积的越来越大
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                max = Math.max(max, porcess(copyButExcept(programs, i), sum + 1, programs[i].end));
            }
        }
        return max;
    }

    private static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }

    //贪心
    public static int bestArrange2(Program[] programs) {
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });
        int sum = 0;
        Program pro = null;
        for (Program program : programs) {
            if (sum == 0) {
                pro = program;
                sum++;
            } else if (pro.end <= program.start) {
                pro = program;
                sum++;
            }

        }
        return sum;
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
                bestArrange1(programs);
                bestArrange2(programs);
            }
        }
        System.out.println("finish!");
    }
}
