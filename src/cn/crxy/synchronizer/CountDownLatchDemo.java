package cn.crxy.synchronizer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ������ģ���ǣ��ĸ��˶�Ավ���������ϣ��ȴ����ܲ��з�������������з���������ĸ��˶�Ա��ʼ���ܡ�
 * �ȵ����һ���˶�Ա�����յ�������ɼ��Ĳ��оͻ������ɼ�
 * ������ʹ�õ�CountDownLatchֻ�����������漰���̣߳���ͬ��ʹ��join���������̣߳���������߳�����
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws Exception {
		final CountDownLatch cdl1 = new CountDownLatch(1);
		final CountDownLatch cdl2 = new CountDownLatch(4);
		
		Thread t = new Thread(new Runnable() {//��������Ĳ���
			
			@Override
			public void run() {
				
				System.out.println("Ԥ��");
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
				System.out.println("�ܣ�");
				cdl1.countDown();//��������1
			}
		});
		t.start();
//		t.join();//ʹ��join�������԰Ѹ��̼߳��뵽main�߳��У�����ִ�е�ǰ�̣߳����������̵߳õȵ����߳�ִ�������ִ�У���������߳�����
		
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {//�˶�Ա
				
				@Override
				public void run() {
					try {
						cdl1.await();//�ȴ���������Ϊ0
						System.out.println(Thread.currentThread().getName()+":����");
						TimeUnit.SECONDS.sleep(new Random().nextInt(3)+3);
						System.out.println(Thread.currentThread().getName()+":�����յ㣡");
						cdl2.countDown();//��������1
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}).start();
		}
		
		new Thread(new Runnable() {//�����ɼ��Ĳ���
			
			@Override
			public void run() {
				try {
					cdl2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("�����ɼ���");
			}
		}).start();
	}
}
