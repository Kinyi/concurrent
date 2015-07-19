package cn.crxy.synchronizer;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 本例的模型是：有四个小伙伴相约出去先一起爬山，在一起吃饭，最后一起唱歌
 * 但是每个阶段都必须等最后一个人完成之后才能进行下一个阶段的活动
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		final CyclicBarrier cb = new CyclicBarrier(4);
		
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
						try {
							System.out.println(Thread.currentThread().getName()+ ":爬山...");
							TimeUnit.SECONDS.sleep(new Random().nextInt(3) + 2);
							System.out.println(Thread.currentThread().getName()+ ":爬完山了...");
							cb.await();//等待，直到计数器变为0为止

							System.out.println(Thread.currentThread().getName()+ ":吃饭...");
							TimeUnit.SECONDS.sleep(new Random().nextInt(3) + 2);
							System.out.println(Thread.currentThread().getName()+ ":吃完饭了...");
							cb.await();//等待，直到计数器变为0为止

							System.out.println(Thread.currentThread().getName()+ ":KTV...");
							TimeUnit.SECONDS.sleep(new Random().nextInt(3) + 2);
							System.out.println(Thread.currentThread().getName()+ ":唱完歌了...");
							cb.await();//等待，直到计数器变为0为止
							
							System.out.println(Thread.currentThread().getName()+ ":回家了");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			}).start();
		}
	}
}
