package cn.crxy.synchronizer;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * ������ģ���ǣ����ĸ�С�����Լ��ȥ��һ����ɽ����һ��Է������һ�𳪸�
 * ����ÿ���׶ζ���������һ�������֮����ܽ�����һ���׶εĻ
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		final CyclicBarrier cb = new CyclicBarrier(4);
		
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
						try {
							System.out.println(Thread.currentThread().getName()+ ":��ɽ...");
							TimeUnit.SECONDS.sleep(new Random().nextInt(3) + 2);
							System.out.println(Thread.currentThread().getName()+ ":����ɽ��...");
							cb.await();//�ȴ���ֱ����������Ϊ0Ϊֹ

							System.out.println(Thread.currentThread().getName()+ ":�Է�...");
							TimeUnit.SECONDS.sleep(new Random().nextInt(3) + 2);
							System.out.println(Thread.currentThread().getName()+ ":���극��...");
							cb.await();//�ȴ���ֱ����������Ϊ0Ϊֹ

							System.out.println(Thread.currentThread().getName()+ ":KTV...");
							TimeUnit.SECONDS.sleep(new Random().nextInt(3) + 2);
							System.out.println(Thread.currentThread().getName()+ ":�������...");
							cb.await();//�ȴ���ֱ����������Ϊ0Ϊֹ
							
							System.out.println(Thread.currentThread().getName()+ ":�ؼ���");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			}).start();
		}
	}
}
