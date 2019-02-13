package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import annotation.Desc;

/**
 * 没有线程安全问题的例子
 * 1.ThreadLocal分配SimpleDateFormat实例
 * 2.ThreadPoolExecutor生成线程池进行测试
 * @author wsz
 * @date 2019年2月13日
 */
public class NormalTest {
	
	private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	class ThreadObj implements Runnable{
		@Override
		public void run() {
			for(int i = 0;i < 20; i++) {
				try {
					System.out.println(dateFormat.get().parse("2019-02-13 15:00:00"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	@Desc("使用ThreadLocal确保每一个线程用于独立的SimpleDateFormat实例")
	public void normalTest() throws InterruptedException {
		//保存等待执行任务的阻塞队列(有界数组实现)，任务对象必须实现Runnable
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(20);
		ThreadPoolExecutor service= new ThreadPoolExecutor(
				20,		//corePoolSize线程池中核心线程的数量,运行中的最大线程数
				100,	//maximumPoolSize线程池中允许的最大线程数
				3,		//keepAliveTime线程空闲(存活)的时间,线程执行完毕后存活的时间,没有立即销毁,当线程数大于corePoolSize时有效
				TimeUnit.SECONDS,	//keepAliveTime单位
				queue	//workQueue用来保存等待执行的任务的阻塞队列，等待的任务必须实现Runnable接口
				);
		for(int i=0; i<20; i++) {
			service.execute(new ThreadObj());
		}
		//执行完毕关闭线程
		service.shutdown();
		//线程将一直阻塞直到所有线程在shutdown之后执行完毕，或者超时，或者线程被中断
		service.awaitTermination(30, TimeUnit.SECONDS);
	}
	
}
