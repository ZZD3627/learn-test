package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @ClassName ComparatorTest
 * @Description 测试Comparator接口的sort方法
 * @Author zhang zhengdong
 * @DATE 2024/12/29 16:16
 * @Version 1.0
 */
public class ComparatorTest {

	/**
	 * Comparator比较排序，使用的是二分插入排序
	 * 代码如下：binarySort 方法
	 * 详细的排序过程解释：
	 * 代码功能：
	 * 输入参数：
	 * a待排序的数组
	 * lo:排序范围的起始索引（包括）
	 * hi:排序范围的结束索引(不包括)
	 * start:排序范围中第一个未排序的元素索引（lo<=start<=hi）
	 * 假设数组的前半部分[lo,start)是已排序，这段代码会将[start,hi)排序完成
	 * 工作原理：
	 * 通过二分查找（Binary search）快速找到每个待插入元素的插入位置，然后通过移动元素完成插入
	 * 详细的排序过程：
	 * 初始条件：
	 * 假设[lo,start)是已排序
	 * 从start开始，逐一处理后续的每个元素
	 * 处理每个元素
	 * 选择插入元素(pivot):当前需要插入的元素是a[start]
	 * 二分法找插入位置(left和right):
	 * 初始化：
	 * left = lo:二分法查找的起始点
	 * right = start:二分法查找的终止点
	 * 通过二分查找，确定pivot在已排序部分[lo,start)中的正确位置left（即比它小的元素的末尾位置）
	 * 二分查找逻辑：
	 * 每次取中间索引mid = (left + right)/2
	 * 如果pivot < a[mid]:
	 * 说明插入点在[left,mid)中，调整right=mid
	 * 否则：
	 * 说明插入点在[mid+1,right)中，调整left = mid +1
	 * 直到结束式，left == right，即插入的位置
	 * 插入到正确的位置：
	 * 计算需要移动的元素数：n = start - left
	 * 使用数组copy方法将[left,start)中的元素向右移动一位，为pivot腾出位置
	 * 当n == 1或n==2时，使用简单的赋值操作
	 * 当n>2时，使用System.arraycopy优化性能
	 * 将pivot插入到位置a[left]
	 * 重复步奏：递增start，对下一个元素执行同样的操作，直到start=hi
	 * 时间复杂度：
	 * 二分查找一次需要O(log n)次比较，总的二分查找时间复杂度为O(n log n)
	 * 一次插入操作最差需要移动O(n)个元素，总的插入时间复杂度为O(n^2)
	 * 空间复杂度：
	 * 此排序方法为一种原地排序算法，不需要额外的数组空间，除了递归调用栈外，额外的空间开销为常数级别，即O(1)
	 */


	@SuppressWarnings({"fallthrough", "rawtypes", "unchecked"})
	private static void binarySort(Object[] a, int lo, int hi, int start) {
		assert lo <= start && start <= hi;
		if (start == lo)
			start++;
		for (; start < hi; start++) {
			Comparable pivot = (Comparable) a[start];

			// Set left (and right) to the index where a[start] (pivot) belongs
			int left = lo;
			int right = start;
			assert left <= right;
			/*
			 * Invariants:
			 *   pivot >= all in [lo, left).
			 *   pivot <  all in [right, start).
			 */
			while (left < right) {
				int mid = (left + right) >>> 1;
				if (pivot.compareTo(a[mid]) < 0)
					right = mid;
				else
					left = mid + 1;
			}
			assert left == right;

			/*
			 * The invariants still hold: pivot >= all in [lo, left) and
			 * pivot < all in [left, start), so pivot belongs at left.  Note
			 * that if there are elements equal to pivot, left points to the
			 * first slot after them -- that's why this sort is stable.
			 * Slide elements over to make room for pivot.
			 */
			int n = start - left;  // The number of elements to move
			// Switch is just an optimization for arraycopy in default case
			switch (n) {
				case 2:
					a[left + 2] = a[left + 1];
				case 1:
					a[left + 1] = a[left];
					break;
				default:
					System.arraycopy(a, left, a, left + 1, n);
			}
			a[left] = pivot;
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();

		list.add(1);
		list.add(20);
		list.add(3);
		list.add(7);
		list.add(8);
		list.add(9);
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});

		for (Integer i : list) {
			System.out.println(i);
		}
	}
}
