package algorithm.bloomFilters;
/**
 * 布隆过滤器测试
 * @author wsz
 * @date 2019年2月26日
 */

import org.junit.Test;

public class BoolmTest {

	@Test
	public void test() {
		int size = 1 << 15;
		BoolmFilter filter = new BoolmFilter(size);
		int num = 1 << 10;
		for(int i = 0; i < num; i++) {
			filter.put( i + "" );
		}
		//结果存在一定的误判
		System.out.println(filter.check("-1"));
		System.out.println(filter.check("0"));
		System.out.println(filter.check("999"));
		System.out.println(filter.check("15151515"));
	}
}
