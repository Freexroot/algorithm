package class06;

//大根堆的push和pop
public class Code02_Heap {
    public static class MyMaxHeap {
        private int heap[];
        private int heapSize = 0;//入堆数
        private final int limit;//最大值，可以用heap.length代替

        public MyMaxHeap() {
            this.limit = 10;
            heap = new int[limit];
        }

        public MyMaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public int pop() {
            if (heapSize == 0) {
                throw new RuntimeException("pead is null");
            }

            swap(heap, 0, heapSize - 1);
            heapSize--;
            heapify(heap, 0, heapSize);
            return heap[heapSize];
        }

        public void push(int value) {
            if (heapSize >= limit) {
                System.out.println("head is Full");
                return;
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize);
            heapSize++;
        }

        //向上浮
        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (heapSize - 1) / 2;
            }
        }

        //向下沉，index和heapSize交换
        private void heapify(int[] heap, int index, int heapSize) {
            int left = index * 2 + 1;//模拟树的右子节点
            //如果越界就退出
            while (left < heapSize) {
                left = left + 1 < heapSize && heap[left] < heap[left + 1] ? (left + 1) : (left);
                if (heap[left] > heap[index]) {
                    swap(heap, index, left);
                }
                index = left;
                left = index * 2 + 1;
            }
        }


        private void swap(int arr[], int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static class RightMaxHeap {

    }

    public static void main(String[] args) {
        MyMaxHeap myMaxHeap = new MyMaxHeap();
        myMaxHeap.push(1);
        myMaxHeap.push(3);
        myMaxHeap.push(2);
        myMaxHeap.push(6);
        myMaxHeap.push(4);
        System.out.println(myMaxHeap.pop());
    }
}
