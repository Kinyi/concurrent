package cn.crxy.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * �����̳߳صĴ������ʹ��
 */
public class ThreadPoolDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int processors = Runtime.getRuntime().availableProcessors();// ��ü�����м����ں�
		// System.out.println("pro : " + processors);

		ExecutorService fixedThreadPool = Executors
				.newFixedThreadPool(processors * 100);// �̶��̸߳������̳߳�
		// System.out.println(fixedThreadPool);
		for (int i = 0; i < 10; i++) {
			fixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());// pool-1-thread-1
				}
			});
		}

		fixedThreadPool.shutdown();// ���̳߳عر�

		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();// �����̳߳أ�������
		for (int i = 0; i < 100; i++) {
			cachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		cachedThreadPool.shutdown();

		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();// ��һ�̳߳�,��Զ�����һ���߳�
		for (int i = 0; i < 10; i++) {
			final int j = i;
			singleThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					/*
					 * if(j == 3) throw new RuntimeException("���쳣��...");
					 */
					System.out.println(Thread.currentThread().getName() + ":"+ j);
				}
			});
		}
		singleThreadPool.shutdown();

		ScheduledExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(5);//�̶��������̳߳أ�����ִ����ʱ����Ҳ����ִ�д��з���ֵ������

		FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("hello");
				return Thread.currentThread().getName();
			}
		});

		scheduledThreadPool.submit(ft);
		String result = ft.get();
		System.out.println("result : "+result);
		
		scheduledThreadPool.schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" : boom!");
			}
		}, 3, TimeUnit.SECONDS);
	}
}