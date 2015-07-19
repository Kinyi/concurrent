package cn.crxy.synchronizer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 本例的模型是：四个运动员站在起跑线上，等待起跑裁判发布起跑命令。裁判发布命令后，四个运动员开始起跑。
 * 等到最后一个运动员到达终点后，宣布成绩的裁判就会宣布成绩
 * 本例中使用的CountDownLatch只会作用于所涉及的线程，不同于使用join作用于主线程，造成其他线程阻塞
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws Exception {
		final CountDownLatch cdl1 = new CountDownLatch(1);
		final CountDownLatch cdl2 = new CountDownLatch(4);
		
		Thread t = new Thread(new Runnable() {//发布命令的裁判
			
			@Override
			public void run() {
				
				System.out.println("预备");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for (int i = 3; i >= 1 ; i--) {
					System.out.println(i+"...");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("跑！");
				cdl1.countDown();//计数器减1
			}
		});
		t.start();
//		t.join();//使用join方法可以把该线程加入到main线程中，优先执行当前线程，其他所有线程得等到该线程执行完才能执行；但会造成线程阻塞
		
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {//运动员
				
				@Override
				public void run() {
					try {
						cdl1.await();//等待计数器变为0
						System.out.println(Thread.currentThread().getName()+":起跑");
						TimeUnit.SECONDS.sleep(new Random().nextInt(3)+3);
						System.out.println(Thread.currentThread().getName()+":到达终点！");
						cdl2.countDown();//计数器减1
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}).start();
		}
		
		new Thread(new Runnable() {//宣布成绩的裁判
			
			@Override
			public void run() {
				try {
					cdl2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("宣布成绩！");
			}
		}).start();
	}
}
