package cn.crxy.homework;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws Exception {
//		System.out.println(System.currentTimeMillis());
		/*ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+":boom!");
			}
		}, 1, 1, TimeUnit.SECONDS);*/
		
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		for (int i = 0; i < 4; i++) {
			queue.put(i+"");
		}
//		System.out.println(queue.size());
//		int size = queue.size();
		for (int i = 0; i < 4; i++) {
			System.out.println(queue.take());
		}
	}

}
