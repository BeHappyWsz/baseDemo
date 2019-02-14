package collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * 比较ArrayList与LinkedList的增删效率
 * 1.ArrayList底层为数组实现，需要对数组进行拷贝复制；增删较低慢，查询较快
 * 2.LinkedList底层为双向链表实现；增删较快，查询较慢
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
  10W    12-7-5-18-8		    13-6-5-9-5
  50W    19-17-18-19-18         66-77-66-105-64
  100W   62-60-64-62-70         109-102-107-105-107
  500W   1375-1410-1345-1396    3136-3120-3142-3231
 */	
	
	int dataSize = 5000000;
	//顺序插入尾部
	public void time(List<Object> list) {
		long arrayS = System.currentTimeMillis();
		for(int i=0; i<dataSize;i++) {
			list.add(i);
		}
		long arrayE = System.currentTimeMillis();
		System.out.println(arrayE - arrayS);
	}
	
	//随机插入
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
	
	@Test
	public void addAndDelete() {
		randomInsert(new ArrayList<>(10));
		randomInsert(new LinkedList<>());
	}
}
