package com.example;

import java.util.Comparator;

/**
 * @ClassName MergeSort
 * @Description 歸並排序
 * @Author zhang zhengdong
 * @DATE 2024/12/30 15:31
 * @Version 1.0
 */
public class MergeSort {

	/**
	 * 原理：
	 * 歸並排序時一鐘基於分治思想的排序算法。它將數組分成兩個子數組，分別進行排序，然後將已排序的子數組合併，最終得到排序結果
	 * <p>
	 * 詳細的講解：
	 * 分割：将数组从中间分割成两个数组
	 * 递归排序：递归地对每个子数组进行排序，直到每个子数组包含一个元素
	 * 合并：将排序号的两个子数组合并成一个有序的数组
	 * <p>
	 * 分割
	 * [5, 2, 4, 6, 1, 3]
	 * /           \
	 * [5, 2, 4]     [6, 1, 3]
	 * /   \         /    \
	 * [5] [2, 4]   [6]   [1, 3]
	 * /   \           /  \
	 * [2]  [4]        [1]  [3]
	 * <p>
	 * 合并
	 * 合并 2和4 {此处直接将2与4进行比较，2小于4，并且2后面也没有值，则2排在4的前面，由于4后面没有值，则直接结束}
	 * [2,4]
	 * 合并 1和3 {此处直接将1与2进行比较，1小于3，并且1后面没有值，则1排在3的前面，由于3后面也没有值，则直接结束}
	 * [1,3]
	 * 合并[2,4]和5 {此处是将5跟2直接比较，5比2大再跟4进行比较，5比4大则排在4的后面，由于5后面没有其他值则直接将结束}
	 * [2,4,5]
	 * 合并[1,3]和6 {此处将6与1进行比较，6比1大，则6再与3进行比较，6又比3大并且是最好一个值，则直接将6放在3的后面}
	 * [1,3,6]
	 * [2,4,5]和[1,3,6]
	 * 2和1进行比较，1比2小，并且2是数组的第一个位置，则1直接排在2的前面，再将3与2进行比较，3比2大则直接将2排在1的后面，然后再将4与3进行比较，
	 * 3小于4则直接排在2的后面，再将6与4进行比较，由于4比6小则直接排在3的后面，此时则将5与6进行比较，5比6小则排在4的后面，由于5是最后一个数，则
	 * 直接将6排到5的后面
	 * [1, 2, 3, 4, 5, 6]
	 * <p>
	 * 归并排序的时间和空间复杂度
	 * 归并排序的时间复杂度主要分为两个部分：分割和合并
	 * 分割阶段：
	 * 在每次递归调用时，我们将数组分成两个子数组，直到每个子数组只有一个元素。这个过程的深度为log n，其中n时待排序数组的长度
	 * 由于每层递归都需要对数组进行划分，因此递归的深度时log(n)
	 * 合并阶段：
	 * 在每次合并操作中，我们将两个已排序的子数组合并成一个更大的有序子数组。每次合并需要遍历所以的元素，因此合并的时间复杂度为O(n)
	 * 每一层递归的合并操作总共需要O(n)的时间，因此无论递归的深度如何，合并操作总共需要O(n)时间
	 * 综上所述：
	 * 最坏情况：O(n log n)
	 * 平均情况：O(n log n)
	 * 最好情况：O(n log n)
	 * 归并排序的时间复杂度时稳定的，不会收到输入数据分布的影响，因此即使时在最坏情况下，她的性能也保持不变
	 * <p>
	 * 空间复杂度：
	 * 归并排序的空间复杂度主要由以下几个方面构成：
	 * 递归调用栈的空间：
	 * 归并排序采用递归方式实现，每次递归时会将当前的数组划分成两个子数组，递归的深度时log(n)。因此，递归调用栈占用的空间为O(log n)
	 * 临时数组的空间：
	 * 在合并两个已排序的子数组时，我们需要一个临时数组来存储合并后的结果。为了合并两个子数组，我们需要一个额外的数组，
	 * 这个数组的大小与待排序数组的大小相同，因此临时数组的空间复杂度为O(n)
	 */

	public static void main(String[] args) {
		Person[] people = {
				new Person("Alice", 30),
				new Person("Bob", 25),
				new Person("Charlie", 35),
				new Person("Bob", 20),
				new Person("Charlie", 15)
		};

		//對Person數組進行歸並排序，按照年齡升序排列
		mergeSort(people, 0, people.length - 1, Comparator.comparingInt(Person::getAge));

		System.out.println("排序結果：");
		for (Person person : people) {
			System.out.println(String.format("%s : %s", person.getName(), person.getAge()));
		}
	}

	public static <T> void mergeSort(T[] array, int left, int right, Comparator<T> comparator) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(array, left, mid, comparator);
			mergeSort(array, mid + 1, right, comparator);
			merge(array, left, mid, right, comparator);
		}
	}

	public static <T> void merge(T[] array, int left, int mid, int right, Comparator<T> comparator) {
		T[] temp = (T[]) new Object[right - left + 1];
		int i = left, j = mid + 1, k = 0;
		while (i <= mid && j <= right) {
			if (comparator.compare(array[i], array[j]) <= 0) {
				temp[k++] = array[i++];
			} else {
				temp[k++] = array[j++];
			}
		}

		while (i <= mid) {
			temp[k++] = array[i++];
		}
		while (j <= right) {
			temp[k++] = array[j++];
		}
		for (int p = 0; p < temp.length; p++) {
			array[left + p] = temp[p];
		}
		// 将 temp 中的元素拷贝回原数组
		System.arraycopy(temp, 0, array, left, temp.length);
	}
}


// 定义 Person 类
class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}