package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * LRU模拟算法
 * @author wsz
 * @date 2019年2月21日
 */
public class LRUCache {

	static final int DEFAULT_CAPACITY = 2^5;
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	/**
	 * 缓存容量
	 */
	int capacity;
	
	/**
	 * 模拟缓存列表
	 */
	List<Object> keys;
	
	/**
	 * 缓存键值对
	 */
	Map<Object,Object> map;
	
	public LRUCache() {
		this(DEFAULT_CAPACITY);
	}

	public LRUCache(int initialCapacity) {
		if(initialCapacity < 0)
			throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
		if(initialCapacity > MAXIMUM_CAPACITY) {
			initialCapacity = MAXIMUM_CAPACITY;
		}
		this.capacity = initialCapacity;
		resize();
	}
	
	public Object get(Object key) {
		Object object = map.get(key);
		if(object != null) {
			int indexOf = keys.indexOf(key);
			keys.remove(indexOf);
			keys.add(0, key);
			return object;
		}else {
			return -1;
		}
	}
	
	public Object put(Object key, Object value) {
		Object object = map.get(key);
		if(object != null && keys.contains(key)) {
			int indexOf = keys.indexOf(key);
			if(indexOf > -1) {
				keys.remove(indexOf);
			}
		}
		map.put(key, value);
		keys.add(0, key);
		if(size() > capacity) {
			Object del = keys.get(keys.size()-1);
			keys.remove(keys.size()-1);
			map.remove(del);
		}
		return object;
	}
	
	public void resize() {
		if(keys == null) {
			keys = new ArrayList<>(capacity);
		}
		if(map == null) {
			map = new HashMap<>(capacity);
		}
	}
	
	public int size() {
		return keys.size();
	}
}
