import java.lang.reflect.Array;

public class Solution {
    public static void main(String[] args){
        int[] arr = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if(left >= right){
            return;
        }

        int pivot = partition(arr, left, right);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(arr[pivot]);

        quickSort(arr, left, pivot-1);
        quickSort(arr, pivot+1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        swap(arr, left, mid);

        int pivot = arr[left];
        int l = left;
        int r = right;

        while(l < r){
            while(arr[r] > pivot) r--;
            while(l < r && arr[l] <= pivot) l++;

            swap(arr, l, r);
        }
        arr[left] = arr[l];
        arr[l] = pivot;

        return l;
    }


    private static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
