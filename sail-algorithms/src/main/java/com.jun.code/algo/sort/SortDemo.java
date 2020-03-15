package algo.sort;

import java.util.Random;

/**
 * 1.冒泡排序
 * 2.选择排序
 * 3.快排
 * 4.二分法查找
 */
public class SortDemo {
	public static void main(String[] args) {

		////一百的数组
		//Random ran = new Random();
		//int[] arr = new int[100];
		//for (int i = 0; i < 100; i++) {
		//	arr[i] = ran.nextInt(500);
		//}
		////小数组
		////int[] a = {1,7,0,4,8,5,7,0};
		//
		//// 执行操作
		//long startTime = System.currentTimeMillis();
		//quickSort(arr,0,arr.length-1);
		//long endTime = System.currentTimeMillis();
		//System.out.println(endTime - startTime);
		//////打印数组
		//for (int x : arr) {
		//	System.out.print(x + " ");
		//}
		////二分法查找
		//System.out.println();
		//System.out.println(binarySearch(arr,14));
	}

	/**
	 * 快排
	 * 时间复杂度O(nlogn)
	 */
	public static void quickSort(int[] arr, int left, int right) {
		// 结束递归的条件
		if(left > right){
			return;
		}

		int i = left;
		int j = right;
		int baseNum = arr[left];
		int temp;

		while(i != j){
			//注意先从左边找
			while(j > i && arr[j] > baseNum){
				j--;
			}
			while (j > i && arr[i] <= baseNum){
				i++;
			}
			//此时说明停下来,交换两个数位置
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		//跳出while循环说明两个指针相遇,此时基准数归位
		arr[left] = arr[i];
		arr[i] = baseNum;
		//继续递归调用
		quickSort(arr, left, i - 1);
		quickSort(arr, i + 1, right);
	}


	/**
	 * 选择排序
	 * 先求出小的数放前面
 	 */

	public static void selectSort(int[] arr) {
		int temp;
		for(int i=0;i<arr.length-1;i++){
			for(int j=i+1;j<arr.length;j++){
				if(arr[i]>arr[j]){
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	/**
	 * 冒泡排序
	 * 先求出大的数放后面
	 *
 	 */

	public static void bubbleSort(int[] arr) {
		int temp;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = 0; j < arr.length-1-i; j++) {
				if(arr[j]>arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}


	/**
	 * 二分法查找
	 * 时间复杂度O(logn)
	 */
	public static int  binarySearch(int[] arr, int key){
		int min = 0;
		int max = arr.length - 1;
		int mid;
		while(min <= max){
			 mid = (max + min)/2;
			if(key > arr[mid]){
				min = mid + 1;
			}else if(key < arr[mid]){
				max = mid - 1;
			}else{
				return mid;
			}
		}
		return -1;
	}
}
