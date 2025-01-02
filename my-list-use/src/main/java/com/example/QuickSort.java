package com.example;

/**
 * @ClassName QuickSort
 * @Description 快速排序
 * @Author zhang zhengdong
 * @DATE 2024/12/31 9:42
 * @Version 1.0
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] array = {8, 3, 1, 7, 0, 10, 2};

		System.out.println("Original Array");
		printArray(array);
		quickSort(array, 0, array.length - 1);

		System.out.println("Sorted Array:");
		printArray(array);

	}

	/**
	 * 快速排序的过程：
	 * 	选择最后一个元素为基准元素【选择基准元素的方法有多种，可以选择开始，也可以选择中间，当前使用的是选择最后一个元素】
	 * 	第一次划分：
	 * 		从数组的左端开始，寻找小于基准的元素，如果找到小于基准的元素则直接将这个元素与此次数组开始遍历的元素位置进行替换，并记录下进行替换的元
	 * 		素位置直至此次数组遍历至最后一个元素，然后将此元素与上面被记录下进行替换的元素位置进行替换，记录下此处被替换元素的位置
	 * 	将左半边元素继续进行替换
	 * 		替换规则继续如上，选择最后一个元素为基准元素，继续执行上面的步奏
	 * 	将右半边的元素进行替换
	 * 		替换规则如上，选择最后一个元素为基准元素，继续执行上面的步奏
	 * 	理解：选择最后一个元素为基准元素，如果比这个元素大则暂时不动，如果比这个元素小则直接替换掉之前比基准元素大的那个位置，然后再将这个被替换后的
	 * 	位置记录，再次分成左右边，按照上面的规则执行，直到没法再次进行遍历
	 *
	 *时间复杂度：
	 * 平均时间复杂度：
	 * 	在理想情况下，每次划分操作将数组近似地分成大小相等的两部分，由于每次划分需要对所有元素进行一次比较，时间复杂度为O(n).递归深度为log2(n),
	 * 	因此平均时间复杂度为O(n log n)
	 * 最坏时间复杂度：
	 * 	在最坏情况下（例如：每次选择的基准元素都是当前子数组的最大或最小值），没次划分只能减少一个元素，导致递归深度达到n.此时，总的比较次数为1
	 * 	+2+...+(n-1),即O(n^2)
	 * 最好时间复杂度：
	 * 	与平均情况类似，最好情况下每次都能将数组平分，时间复杂度仍为O(n log n)
	 *
	 *空间复杂度：
	 * 	递归调用栈空间：
	 * 		平均情况：递归深度为log2(n),因此空间复杂度为O(log n)
	 * 		最坏情况：递归深度为n，空间复杂度为O(n)
	 * 	原地排序：
	 * 		快速排序是一种原地排序算法，不需要额外的数组空间，除了递归调用栈外，额外的空间开销为常数级别，即O(1)
	 *
	 */
	/**
	 * 快速排序的主方法
	 *
	 * @param array 要排序的数组
	 * @param low   左边界
	 * @param high  右边界
	 */
	private static void quickSort(int[] array, int low, int high) {

		if (low < high) {
			//划分数组，并返回基准元素的位置
			int pivotIndex = partition(array, low, high);

			// 递归地对左边部分排序
			quickSort(array, low, pivotIndex - 1);

			// 递归地对右边部分排序
			quickSort(array, pivotIndex + 1, high);
		}

	}

	/**
	 * 划分数组，并返回基准元素的位置
	 *
	 * @param array 要划分的数组
	 * @param low   左边界
	 * @param high  右边界
	 * @return 基准元素的新位置
	 */
	private static int partition(int[] array, int low, int high) {
		//选择最好一个元素作为基准
		int pivot = array[high];

		//i表示小于基准的子数组的尾部索引
		int i = low - 1;

		//遍历数组，将小于基准的元素移到左边
		for (int j = low; j < high; j++) {
			if (array[j] < pivot) {
				i++;
				//交互array[i]和array[j]
				swap(array, i, j);
			}
		}
		//将基准元素放到正确的位置
		swap(array, i + 1, high);

		printArray(array);
		return i + 1;
	}

	/**
	 * 交换数组中的两个元素
	 *
	 * @param array 数组
	 * @param i     第一个元素的索引
	 * @param j     第二个元素的索引
	 */
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}


	/**
	 * 打印数组
	 *
	 * @param arr 要打印的数组
	 */
	private static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
}
