package class5.newThread;

import java.util.concurrent.TimeUnit;

/*public */class Test1 {

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		},"�߳�һ").start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		},"�̶߳�").start();
	}
}

public class Test{
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println("һ:"+i);
		}
		
		
		for (int i = 0; i < 10; i++) {
			System.out.println("��:"+i);
		}
	}
}
