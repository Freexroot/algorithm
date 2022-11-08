package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//加强堆—大根堆
//T一定要是非基础类型，不过可以包一层来解决
public class HeapGreater<T> {
    private Map<T, Integer> map; //反向索引表
    private ArrayList<T> heap; //堆
    private Integer heapSize; //从0开始计时
    private Comparator<? super T> comp; //对数器

    //参数是比较器
    HeapGreater(Comparator<? super T> c) {
        this.heapSize = 0;
        this.map = new HashMap<>();
        this.heap = new ArrayList<>();
        this.comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    //判断堆里有没有这个元素
    public boolean contain(T obj) {
        return map.containsValue(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public T pop() {
        if (heapSize == 0) {
            return null;
        }

        T obj = heap.get(0);
        T endObj = heap.get(--heapSize);
        map.remove(endObj);
        if (obj != endObj) {
            heap.set(0, endObj);
        }
        heapify(0);
        return obj;
    }

    public void push(T obj) {
        heap.add(obj);
        map.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    //向上整理
    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //向下整理
    public void heapify(int index) {

        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;//取左右树的最值
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;//自己节点和子树最值比较
            if (best == index) {//已经到最底下了，跳出
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    //改变对象里的值，地址不变而值变,重新调整新对象的位置
    public void resign(T obj) {
        heapInsert(map.get(obj));
        heapify(map.get(obj));
    }

    //删除任意值
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);//获得最后一个值
        int index = map.get(obj);//拿到这个值的位置
        map.remove(obj);
        heap.remove(--heapSize);

        if (obj != replace) {
            heap.set(index, replace);
            map.put(replace, index);
            resign(replace);
        }
    }

    //交换
    public void swap(int a, int b) {
        T obj1 = heap.get(a);
        T obj2 = heap.get(b);
        map.remove(obj1);
        map.remove(obj2);

        heap.set(a, obj2);
        heap.set(b, obj1);
        map.put(obj1, b);
        map.put(obj2, a);
    }
}
