package algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/**
 * LRU模拟算法
 * Least recently used，最近最少使用
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
	 * 双向链表LinkedList模拟缓存列表
	 */
	LinkedList<Object> keys;
	
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
			keys.remove(key);
			keys.addFirst(key);
			return object;
		}else {
			return -1;
		}
	}
	
	public Object put(Object key, Object value) {
		Object object = map.get(key);
		if(object != null && keys.contains(key)) {
			keys.remove(key);
		}
		map.put(key, value);
		keys.addFirst(key);
		if(size() > capacity) {
			Object del = keys.removeLast();;
			map.remove(del);
		}
		return object;
	}
	
	public void resize() {
		if(keys == null) {
			keys = new LinkedList<>();
		}
		if(map == null) {
			map = new HashMap<>(capacity);
		}
	}
	
	public int size() {
		return keys.size();
	}
}
