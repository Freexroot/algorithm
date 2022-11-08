package class06;

import utils.RandomValue;

import java.util.Arrays;

//给一个乱序的数组把它变成根堆
public class Code03_HeapSort {
    //时间复杂度为N的，从下向上排序
    public static void heapSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //大根堆
        for (int i = (arr.length - 1)/2+1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        //最大的数字移到最后，再调整成大根堆
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //向下沉
    private static void heapify(int[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            left = left + 1 < heapSize && heap[left] < heap[left + 1] ? (left + 1) : (left);
            if (heap[left] > heap[index]) {
                swap(heap, index, left);
            }
            index = left;
            left = index * 2 + 1;
        }
    }

    //时间复杂度为N*logN的，从上向下排序
    public static void heapSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        //最大的数字移到最后，再调整成大根堆
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //向上浮
    private static void heapInsert(int[] heap, int index) {
        while (heap[index] > heap[(index - 1) / 2]) {
            swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    private static void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int lenMax=1000;
        int valueMax=100000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            //ProgressBar.progressBar(10000, i);
            int arr1[] = RandomValue.randomIntArrayDisorder(lenMax, valueMax, false, false);
            int arr2[] = Arrays.copyOf(arr1, arr1.length);

            heapSort1(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("错误1");
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                return;
            }
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime - start;
        System.out.println("当前耗费时间：" + runTime + "ms");


        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            //ProgressBar.progressBar(10000, i);
            int arr1[] = RandomValue.randomIntArrayDisorder(lenMax, valueMax, false, false);
            int arr2[] = Arrays.copyOf(arr1, arr1.length);


            heapSort2(arr1);
            comparator(arr2);

            if (!isEqual(arr1, arr2)) {
                System.out.println("错误2");
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                return;
            }
        }
        endTime = System.currentTimeMillis();
        runTime = endTime - start;
        System.out.println("当前耗费时间：" + runTime + "ms");
    }
}