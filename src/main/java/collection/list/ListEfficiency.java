package collection.list;

import java.util.ArrayList;
import java.util.LinkedList;

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
 		   ArrayList    		   LinkedList
  10W    82-153-51-86-52		36-42-35-37-35
  50W    145-131-140-164-130    80-86-83-111-78
  100W
  500W
 
 */
	
	
	int dataSize = 500000;
	
	@Test
	public void addAndDelete() {
		//ArrayList增加数据
		ArrayList<String> array = new ArrayList<>();
		long arrayS = System.currentTimeMillis();
		for(int i=0; i<dataSize;i++) {
			array.add(i+"");
		}
		long arrayE = System.currentTimeMillis();
		System.out.println(arrayE - arrayS);
		//LinkedList增加数据
		LinkedList<String> link = new LinkedList<>();
		long linkS = System.currentTimeMillis();
		for(int i=0; i<dataSize;i++) {
			link.add(i+"");
		}
		long linkE = System.currentTimeMillis();
		System.out.println(linkE - linkS);
	}
}
