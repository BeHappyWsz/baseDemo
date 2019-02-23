package algorithm;

public class LRUTest {
	
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(5);
		cache.put("a", "1");
		cache.put("b", "2");
		cache.put("c", "3");
		System.out.println(cache.keys);
		cache.get("a");
		System.out.println(cache.keys);
		cache.put("d", "4");
		System.out.println(cache.keys);
		cache.put("e", "5");
		System.out.println(cache.keys);
		System.out.println(cache.get("f"));
		cache.put("f", "6");
		System.out.println(cache.keys);
		cache.put("f", "7");
		System.out.println(cache.keys);
		System.out.println(cache.get("f"));
	}

}
