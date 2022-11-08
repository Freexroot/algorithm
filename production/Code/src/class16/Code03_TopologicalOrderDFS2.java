package class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// OJ链接：https://www.lintcode.com/problem/topological-sorting
//拓扑排序，点次，类似深度优先搜索
public class Code03_TopologicalOrderDFS2 {
    // Definition for Directed graph
    static class DirectedGraphNode {
        long label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    //交下面这个类
    //节点+点次
    public static class Record {
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }

    //找到每一个点可以通过点的数量，然后从大到小的排序就是拓扑排序
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> nodeSum = new HashMap<>();
        //把所有节点的点次找到
        for (DirectedGraphNode dir : graph) {
            f(dir, nodeSum);
        }
        //把写了点次的节点类Record.放入ArrayList类中并排序
        ArrayList<Record> ansRecord = new ArrayList<>(nodeSum.values());
        ansRecord.sort(new myComparator());

        //把排好序的结果导入
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : ansRecord) {
            ans.add(record.node);
        }
        return ans;
    }

    //输入起点dir，让dir的点次放入nodeSum中
    public Record f(DirectedGraphNode dir, HashMap<DirectedGraphNode, Record> nodeSum) {
        //如果已经存在点次就跳过
        if (nodeSum.containsKey(dir)) {
            return nodeSum.get(dir);
        }
        //把所有子节点的点次找到，并计算点次的累加和
        long sum = 1;
        for (DirectedGraphNode neighbor : dir.neighbors) {
            sum += f(neighbor, nodeSum).nodes;
        }
        Record ans = new Record(dir, sum);
        nodeSum.put(dir, ans);
        return ans;
    }


    public class myComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
        }
    }
}
