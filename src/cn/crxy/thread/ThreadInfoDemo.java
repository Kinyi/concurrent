package cn.crxy.thread;

import java.util.concurrent.TimeUnit;

public class ThreadInfoDemo {
	public static void main(String[] args) {
		
		final Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//Thread.sleep(1000*60);
					TimeUnit.SECONDS.sleep(1);
					TimeUnit.MINUTES.sleep(1);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				/*synchronized (ThreadInfoDemo.class) {
					try {
						ThreadInfoDemo.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}*/
				
				
				System.out.println("hello");
			}
		});
		t.start();
		
		System.out.println(t.getName());
		System.out.println(t.getPriority());//优先级
		System.out.println(t.getState());//线程的状态 线程一共有6中状态
										//NEW(新建)  TERMINATED(死亡) RUNNABLE(正在运行) BLOCKED(阻塞) 
										// TIMED_WAITING(定时等待) WAITING(等待)
	}	
}
