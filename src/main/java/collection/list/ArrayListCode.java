package collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
/**
 * 1.底层为数组Object[] elementData，无初始容量构造参数时为EMPTY_ELEMENTDATA，初始容量构参为0时DEFAULTCAPACITY_EMPTY_ELEMENTDATA
 * 2.无参构造初始化时size=0，初次add()/addAll(Collection c)会判断elementData是否为DEFAULTCAPACITY_EMPTY_ELEMENTDATA然后取默认的容量DEFAULT_CAPACITY=10或者自动更新size+=c.length
 * 3.initialCapacity初始容量有参构造，elementData长度为initialCapacity
 * 4.集合Collection构造参数初始化时，转为Object[]，判断集合.length>0，直接使用Arrays.copyOf复制到elementData
 * 5.扩容1.5倍，int newCapacity = oldCapacity + (oldCapacity >> 1);
 */
	
/**
 * add：size+1，判断是否需要扩容，(不)扩容后直接新增到size+1下标位
 * get
 * remove
 * set
 */
	List<Object> list = new ArrayList<>();
	public static void main(String[] args) {
		int[] elementData = {12,254,547,455};
 		elementData = Arrays.copyOf(elementData, 6);
 		System.arraycopy(elementData, 2, elementData, 3, 2);
 		for(int i : elementData) {
 			System.out.print(i + " ");
 		}
	}
}
