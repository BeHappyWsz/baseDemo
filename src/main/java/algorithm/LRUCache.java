package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * LRU-K模拟算法
 * Least recently used，最近最少使用
 * @author wsz
 * @date 2019年2月21日
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LRUCache<K,V> {

	static final int DEFAULT_RATE = 1;
	static final int MAXIMUN_RATE = 1 << 5;
	
	static final int DEFAULT_CAPACITY = 16;
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	@SuppressWarnings("unused")
    private static class Item<K, V, N> {
        K key;
        V value;
        N rate;
        
        Item(K key, V value, N rate) {
            this.key = key;
            this.value = value;
            this.rate = rate;
        }
        
		public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final N getRate()      { return rate; }
        public final void setRate(N rate)   {this.rate = rate;}
        public final String toString() { return key + "=" + value + "=" + rate; }
        
        public final Map castToMap() {
        	Map map = new HashMap(3);
        	map.put("key", key);
        	map.put("value", value);
        	map.put("rate", rate);
        	return map;
        }
    }
    
	/**
	 * 缓存容量
	 */
	private int capacity;
	
	/**
	 * 默认频率，访问记录rate次后加入到缓存列表中
	 */
	private int rate;
	
	/**
	 * 双向链表LinkedList模拟缓存列表
	 */
	private LinkedList cacheList;
	
	/**
	 * 缓存键值对
	 */
	private Map<K,V> map;
	
	public LRUCache() {
		this(DEFAULT_CAPACITY, DEFAULT_RATE);
	}

	public LRUCache(int initialCapacity) {
		this(initialCapacity, DEFAULT_RATE);
	}
	
	public LRUCache(int initialCapacity, int initRate) {
		if(initialCapacity < 0)
			throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
		if(initialCapacity > MAXIMUM_CAPACITY) {
			initialCapacity = MAXIMUM_CAPACITY;
		}
		if(initRate < 0)
			throw new IllegalArgumentException("Illegal initial rate: " + initRate);
		if(initRate > MAXIMUN_RATE) {
			initRate = MAXIMUN_RATE;
		}
		
		this.capacity = initialCapacity;
		this.rate = initRate;
		resize();
	}
	
	public V get(K key) {
		Item item = (Item) map.get(key);
		if(item != null) {
			int oldRate = (int) item.getRate();
			int newRate = oldRate + 1;
			item.setRate(newRate);
			map.put(key, (V) item);
			
			if(newRate >= rate) {
				//LinkedList非线程安全，直接加锁同步
				synchronized(cacheList) {
					if(cacheList.contains(key))
						cacheList.remove(key);
					cacheList.addFirst(key);
					if(size() > capacity) {
						cacheList.removeLast();;
					}
				}
			}
			return (V) item.getValue();
		}
		return null;
	}
	
	public V put(K key, V value) {
		V item = (V) new Item(key, value, 0);
		map.put(key, item);
		return item;
	}
	
	public void resize() {
		if(cacheList == null) {
			cacheList = new LinkedList<>();
		}
		if(map == null) {
			map = new HashMap<>(capacity);
		}
	}
	
	public int size() {
		return cacheList == null ? 0 : cacheList.size();
	}
	
	public List getItems() {
		List re = new ArrayList();
		Set<Entry<K, V>> entrySet = map.entrySet();
		for (Entry<K, V> entry : entrySet) {
			Item item = (Item) entry.getValue();
			re.add(item.castToMap());
		}
		return re;
	}
	public List getCacheList() {
		return cacheList;
	}
	
	@Override
	public String toString() {
		return "LRUCache [capacity=" + capacity + ", rate=" + rate + ", cacheList=" + cacheList + ", map=" + map + "]";
	}
}
