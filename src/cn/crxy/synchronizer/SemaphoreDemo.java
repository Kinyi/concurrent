package cn.crxy.synchronizer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 本例的模型是饭馆里有5张桌子，有50位顾客在门外等待，而每次只能进去5人(假设每个人坐一张桌子)
 * 如果使用synchronized很难控制访问共享内容的线程数
 */
public class SemaphoreDemo {

	public static void main(String[] args) {

		final Semaphore semaphore = new Semaphore(5);
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();// 获得许可
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()
							+ ":来了，现在有" + (5 - semaphore.availablePermits())
							+ "名顾客");             //获取当前可用的允许数
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("-->" + Thread.currentThread().getName()
							+ ":走了");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					semaphore.release();// 释放许可
				}
			}).start();
			;
		}
	}
}
