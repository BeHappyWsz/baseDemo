package collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * 比较ArrayList与LinkedList的效率
 * 1.ArrayList底层为数组实现，需要对数组进行拷贝复制、数据移位；增删较低慢，查询较快；
 * 		get(index)-O(1)
 * 		add(E)-O(1)
 * 		add(index,E)-O(n)
 * 		remove()-O(n)
 * 2.LinkedList底层为双向链表实现；增删较快，查询较慢；
 * 		get()-O(n)，涉及到链表遍历
 * 		add(E)-O(1)，直接添加到链表结尾
 * 		add(index,E)-O(n)，涉及到链表遍历与指针指向修改
 * 		remove()-O(1)，针对确定的节点为O(1)。但在源码中需要先遍历获取到当前节点则为O(n)
 * @author wsz
 * @date 2019年2月13日
 */

public class ListEfficiency {
/*
 *    顺序新增时消耗时间，单位微秒
 		   ArrayList    		 LinkedList
  10W    12-7-5-18-8		    13-6-5-9-5
  50W    19-17-18-19-18         66-77-66-105-64
  100W   62-60-64-62-70         109-102-107-105-107
  500W   1375-1410-1345-1396    3136-3120-3142-3231
 */
	
/*
 *    随机插入消耗时间，单位微秒
 		   ArrayList    		 LinkedList
  1W     12-11-9-10-11	      131-155-145-158-190
  2W	 19-21-24-21-22		  615-731-712-622-648
  5W     90-86-87             8208-7789-7668
 */	
	
	@Test
	public void testDemo() {
		randomGet(new ArrayList<>(10));
		randomGet(new LinkedList<>());
	}
	
	int dataSize = 5000000;
	//顺序插入尾部
	public void orderInsert(List<Object> list) {
		long arrayS = System.currentTimeMillis();
		for(int i=0; i<dataSize;i++) {
			list.add(i);
		}
		long arrayE = System.currentTimeMillis();
		System.out.println(arrayE - arrayS);
	}
	
	//随机插入：
	public void randomInsert(List<Object> list) {
		long arrayS = System.currentTimeMillis();
		Random random = new Random();
		for(int i=1; i <= dataSize;i++) {
			int r = random.nextInt(i);
			list.add(r, i);
		}
		long arrayE = System.currentTimeMillis();
		System.out.println(arrayE - arrayS);
	}
	
	//随机获取：测试结果差异不大
	public void randomGet(List<Object> list) {
		for(int i=1; i <= dataSize;i++) {
			list.add(i);
		}
		Random random = new Random();
		int r = random.nextInt(dataSize);
		long arrayS = System.currentTimeMillis();
		System.out.print("random:"+r+" data:"+list.get(r)+"  time:");
		long arrayE = System.currentTimeMillis();
		System.out.println(arrayE - arrayS);
	}
}
