package string;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import annotation.Desc;
/**
 * 对比String、StringBuilder、StringBuffer
 * @author wsz
 * @date 2019年2月27日
 */
public class StringCode {
	
	int num = 100000;
	
/**
 * String
 * 1.final类型不可被继承,成员变量为final char value[],不可变,可通过反射修改原值
 * 2.线程不安全
 * 3.对String对象的任何改变都不影响到原对象，相关的change操作都会生成新的对象;
 * 		在循环修改时，会生成很多中间对象,影响性能
 * @throws Exception 
 * @throws NoSuchFieldException 
 */

	@Test
	@Desc("通过反射修改String的内部final char value[]")
	public void reflectString() throws NoSuchFieldException, Exception {
		String str = "123456789";
		System.out.println(str);
		Field declaredField = String.class.getDeclaredField("value");
		//改变value属性的访问权限 
		declaredField.setAccessible(true);
		//获取s对象上的value属性的值  
	    char[] value = (char[]) declaredField.get(str);  
	    value[0] = '9';  
	    System.out.println(str);
	}
/**
 * StringBuilder
 * 1.修改操作在原对象上进行,效率较String偏高
 * 2.线程不安全
 * 3.继承AbstractStringBuilder
 */

/**
 * StringBuffer
 * 1.修改操作在原对象上进行,效率与StringBuilder类似
 * 2.线程安全,成员方法用synchronized修饰
 * 3.继承AbstractStringBuilder
 */
	
/**
 * 性能测试对比
 * 		ms			  1w             5w               10w
 *  String         209-224-259  3229-3135-3127	 13935-14655-14076
 *  StringBuilder  0-1-1		2-2-1			  4-4-3
 *  StringBuffer   1-1-1		3-2-3			  4-3-5
 */
	//叠加测试
	@Test
	public void efficientTest() {
		eString();
		eStringBuilder();
		eStringBuffer();
	}
	
	public void eString() {
		String str = "";
		long start = System.currentTimeMillis();
		for(int i=0; i<num; i++) {
			str = str+i;
		}
		long end = System.currentTimeMillis();
		System.out.println("String:"+(end-start));
	}
	
	public void eStringBuilder() {
		StringBuilder sBuilder = new StringBuilder();
		long start = System.currentTimeMillis();
		for(int i=0; i<num; i++) {
			sBuilder.append(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("StringBuilder:"+(end-start));
	}
	
	public void eStringBuffer() {
		StringBuffer sBuffer = new StringBuffer();
		long start = System.currentTimeMillis();
		for(int i=0; i<num; i++) {
			sBuffer.append(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("StringBuffer:"+(end-start));
	}
	
	int threadSize = 5000;
	//线程安全测试 多线程情况下，偶尔出现sBuilder.length < sBuffer.length = threadSize
	@Test
	public void synTest() {
		final CountDownLatch latch = new CountDownLatch(threadSize);
		final StringBuilder sBuilder = new StringBuilder();
		final StringBuffer sBuffer = new StringBuffer();
		for(int i=0; i < threadSize; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					sBuilder.append("a");
					sBuffer.append("a");
					latch.countDown();
				}
			}).start();
		}
		try {
			latch.await();
			System.out.println(sBuilder.length());
			System.out.println(sBuffer.length());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
