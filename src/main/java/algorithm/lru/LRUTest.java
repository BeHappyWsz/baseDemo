package algorithm.lru;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import annotation.Desc;
/**
 * LRU-K 测试
 * @author wsz
 * @date 2019年2月21日
 */
public class LRUTest {

	@Test
	@Desc("默认LRU-1")
	public void lruTest() {
		LRUCache<Object, Object> cache = new LRUCache<Object, Object>(5);
		cache.put("a", "1");
		cache.put("b", "2");
		cache.put("c", "3");
		System.out.println(cache.getCacheList());
		cache.get("a");
		System.out.println(cache.getCacheList());
		cache.put("d", "4");
		System.out.println(cache.getCacheList());
		cache.put("e", "5");
		System.out.println(cache.getCacheList());
		System.out.println(cache.get("f"));
		cache.put("f", "6");
		System.out.println(cache.getCacheList());
		cache.put("f", "7");
		System.out.println(cache.getCacheList());
		System.out.println(cache.get("f"));
	}
	
	@Test
	@Desc("LRU-K测试")
	public void kLRUTest() throws InterruptedException {
		
		LRUCache<Integer, Integer> synCache = new LRUCache<Integer, Integer>(10, 5);
		Random random = new Random();
		int range = 20;
		
		for(int i=0; i < range; i++) {
			synCache.put(i, i);
		}
		for(int k=0; k < 80; k++) {
			synCache.get(random.nextInt(range));
		}
		
		for (Object obj : synCache.getItems()) {
			System.out.println(obj);
		}
		System.out.println(synCache.getCacheList());
	}
	
	@Test
	@Desc("测试并发情况")
	public void synTest() throws Exception {
		for(int j=0; j<20; j++) {
			cache.put(j, j);
		}
		//array有界阻塞队列
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueSize);
		//100个任务线程
		ThreadPoolExecutor service = new ThreadPoolExecutor(queueSize/2, taskSize, 10, TimeUnit.SECONDS, queue);
		for(int i=0; i< taskSize; i++) {
			service.execute(new SynTask());
		}
		service.shutdown();
		service.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println(cache.getItems());
		System.out.println(cache.getCacheList());
	}
	LRUCache<Integer, Integer> cache = new LRUCache<Integer, Integer>(20,5);
	Random randon = new Random();
	int queueSize = 20;
	int taskSize = 100;
	
	class SynTask implements Runnable{
		@Override
		public void run() {
			cache.get(randon.nextInt(20));
		}
	}
}
