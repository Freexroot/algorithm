package class15;

//省份数量 https://leetcode.cn/problems/number-of-provinces/
public class Code01_FriendCircles {
    //解 并查集数组实现
    public int findCircleNum(int[][] isConnected) {
        int findCircleNum = 0;
        Union union = new Union(isConnected);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    union.union(i, j);
                }
            }
        }
        return union.getSize();
    }

    class Union {
        private int[] parents;
        private int[] size;
        private int sets;

        public Union(int[][] isConnected) {
            parents = new int[isConnected.length];
            size = new int[isConnected.length];

            for (int i = 0; i < isConnected.length; i++) {
                parents[i] = i;
                size[i] = 1;
                sets++;
            }
        }

        public int find(int a) {
            int[] arr = new int[parents.length];
            int i = 0;
            while (a != parents[a]) {
                arr[i++] = a;
                a = parents[a];
            }

            for (; i > 0; i--) {
                parents[arr[i]] = a;
            }
            return a;
        }

        public void union(int a, int b) {
            int aHead = find(a);
            int bHead = find(b);
            if (aHead != bHead) {
                int big = size[aHead] > size[bHead] ? aHead : bHead;
                int small = big == aHead ? bHead : aHead;
                sets--;
                parents[small] = big;
            }
        }

        public int getSize() {
            return sets;
        }
    }
}


/*
public class Code01_FriendCircles {
    //解 并查集map实现
    public int findCircleNum(int[][] isConnected) {
        int findCircleNum = 0;
        Union union = new Union(isConnected);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i+1; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    union.union(i, j);
                }
            }
        }
        return union.getSize();
    }

    class Union {
        class Node {
            int value;

            public Node(int value) {
                this.value = value;
            }
        }

        private HashMap<Integer, Node> nodes;
        private HashMap<Node, Node> parents;
        private int size;

        public Union(int[][] isConnected) {
            nodes = new HashMap();
            parents = new HashMap();

            for (int i = 0; i < isConnected.length; i++) {
                Node node = new Node(i);
                nodes.put(i, node);
                parents.put(node, node);
                size++;
            }

        }

        public Node find(Node node) {
            Stack<Node> stack = new Stack();
            while (node != parents.get(node)) {
                stack.add(node);
                node = parents.get(node);
            }

            while (!stack.isEmpty()) {
                parents.put(stack.pop(), node);
            }
            return node;
        }

        public void union(int a, int b) {
            Node aHead = find(nodes.get(a));
            Node bHead = find(nodes.get(b));
            if (aHead != bHead) {
                size--;
                parents.put(bHead, aHead);
            }
        }

        public int getSize() {
            return size;
        }
    }
}
*/
