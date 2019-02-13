package collection.list;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ArrayList源码分析
 * @author wsz
 * @date 2019年2月13日
 */
public class ArrayListCode {

	/**
	 * 数组拷贝复制用到的方法
	 * 1.Arrays.copyOf(int[] original,int newLength)
	 * 2.System.arraycopy(Object src,int srcPos,Object dest,int destPos,int length)
	 */
	ArrayList<Object> list = new ArrayList<>();
	
	public static void main(String[] args) {
		int[] elementData = {12,254,547,455};
 		elementData = Arrays.copyOf(elementData, 6);
 		System.arraycopy(elementData, 2, elementData, 3, 2);
 		for(int i : elementData) {
 			System.out.print(i + " ");
 		}
	}
}
