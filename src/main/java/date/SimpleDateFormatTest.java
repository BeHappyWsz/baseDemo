package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import annotation.Desc;

/**
 * 验证SimpleDateFormat的线程安全性和替代方法
 * @author wsz
 * @date 2019年2月13日
 */
public class SimpleDateFormatTest {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	class ThreadObj implements Runnable{
		@Override
		public void run() {
			for(int i = 0;i < 20; i++) {
				try {
					System.out.println(sdf.parse("2019-02-13 15:00:00"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	@Desc("在多线程下，SimpleDateFormat实例被共享，调用方法会出现异常")
	public void exTest() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(50);
		for(int i=0; i<20; i++) {
			service.execute(new ThreadObj());
		}
		//执行完毕关闭线程
		service.shutdown();
		//线程将一直阻塞直到所有线程在shutdown之后执行完毕，或者超时，或者线程被中断
		service.awaitTermination(30, TimeUnit.SECONDS);
	}

}
