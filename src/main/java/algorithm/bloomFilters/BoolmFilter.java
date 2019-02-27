package algorithm.bloomFilters;
/**
 * 判断一个元素是否存在一个集合中
 * 布隆过滤器:存在一定的判断误差，size/数据量 越大误判率则变低
 * 1.定义size长度的字节数组array，初始化所有位置为0
 * 2.put：key经过n次hash函数并求余数，对应余数array位置置1
 * 3.check：key经过n次一样的函数求余，获取n位结果，结果全部为1则数据key可能存在数据集中；存在一个为0则一定不存在数据集中
 * 预计数据量N，错误率F，位数组长度L，hash次数K；计算网址：https://krisives.github.io/bloom-calculator/
 * K ≈ 0.7*(L/N)  F = 0.6185^(L/N)
 * 原理解析：http://www.cnblogs.com/allensun/archive/2011/02/16/1956532.html
 * @author wsz
 * @date 2019年2月26日
 */
public class BoolmFilter {
	//1024
	static final int DEFAULT_SIZE = 1 << 10;
	//1048576
	static final int MAXIMUN_SIZE = 1 << 20;
	
	private int size;
	
	private byte[] byteArray;
	
	public BoolmFilter() {
		this(DEFAULT_SIZE);
	}
	
	public BoolmFilter(int size) {
		if(size < 0)
			throw new IllegalArgumentException("Illegal initial size: " + size);
		if(size > MAXIMUN_SIZE) {
			size = MAXIMUN_SIZE;
		}
		this.size = size;
		byteArray = new byte[size];
	}
	
	public void put(String key) {
		int a = key.hashCode();
		int b = hashCode1(key);
		int c = hashCode2(key);
		int d = hashCode3(key);
		
		byteArray[a % size] = 1;
		byteArray[b % size] = 1;
		byteArray[c % size] = 1;
		byteArray[d % size] = 1;
	}
	
	public boolean check(String key) {
		int a = byteArray[key.hashCode() % size];
		int b = byteArray[hashCode1(key) % size];
		int c = byteArray[hashCode2(key) % size];
		int d = byteArray[hashCode3(key) % size];
		if(a == 1 && b == 1 && c == 1&& d == 1) {
			return true;
		}
		return false;
	}
	
	private int hashCode1(String key) {
		int hash = 0;
		for(int i=0; i < key.length(); i++) {
			hash = 33 * hash + key.charAt(i);
		}
		return Math.abs(hash);
	}
	
	private int hashCode2(String key) {
		final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
	}

	private int hashCode3(String key) {
		int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
	}
}
