package cn.crxy.synchronizer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ������ģ���Ƿ�������5�����ӣ���50λ�˿�������ȴ�����ÿ��ֻ�ܽ�ȥ5��(����ÿ������һ������)
 * ���ʹ��synchronized���ѿ��Ʒ��ʹ������ݵ��߳���
 */
public class SemaphoreDemo {

	public static void main(String[] args) {

		final Semaphore semaphore = new Semaphore(5);
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();// ������
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()
							+ ":���ˣ�������" + (5 - semaphore.availablePermits())
							+ "���˿�");             //��ȡ��ǰ���õ�������
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("-->" + Thread.currentThread().getName()
							+ ":����");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					semaphore.release();// �ͷ����
				}
			}).start();
			;
		}
	}
}
