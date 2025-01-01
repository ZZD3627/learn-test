package com.example;

/**
 * @ClassName InsertionSort
 * @Description 插入排序實現
 * @Author zhang zhengdong
 * @DATE 2024/12/30 14:51
 * @Version 1.0
 */
public class InsertionSort {
	/**
	 * 插入排序
	 * 原理：插入排序是一種簡單的排序算法，其工作原理是通過構建有序序列，對於未排序的數據，在已排序序列序列中從後向前掃描，找到響應的位置並插入。
	 * 它類似於人類按順序整理撲克牌的過程
	 * 步驟：
	 * 1.從第一個元素開始，該元素可以認為已被向前掃描
	 * 2.取出下一個元素，在已排序的元素序列中從後向前掃描
	 * 3.如果已排序的元素大於新元素，將已排序的元素向後移動一位(直接交換位置)
	 * 4.重複步驟3，直到已排序的元素小於或等於新元素的位置
	 * 5.將新元素插入到該位置
	 * 6.重複步驟2-5，直到所有元素均排序完畢
	 * 時間複雜度：
	 * 最壞情況：O(n^2)【當輸入數組是逆序排序時】
	 * 最佳情況：O(n)【當輸入數組已經是有序時】
	 * 評價情況：O(n^2)
	 * 空間複雜度：
	 * O(1)【原地排序】
	 * 穩定性：
	 * 穩定【不會改變相同元素的相對順序】
	 *
	 * 簡單點說：
	 * 	插入排序就是將需要排序的對象與前面的對象進行一個一個的比較，如果比前面的小則繼續向前比較，直到比到最前面的位置或者比到比自己小的位置，此過程
	 * 	即比較大小又替換位置
	 */

	public static void insertionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			//將大於key的元素向後移動
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}


	public static void main(String[] args) {
		int[] array = {5, 2, 4, 6, 1, 3};
		insertionSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}


}
