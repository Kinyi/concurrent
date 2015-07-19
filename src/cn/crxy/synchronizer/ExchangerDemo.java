package cn.crxy.synchronizer;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * ������ģ���ǣ�һ���������ӣ�һ��������ӣ�˫�����Э��������õ�Ǯ���򷽵õ�����
 * 
 */
public class ExchangerDemo {

	public static void main(String[] args) {
		final Exchanger<String> exchanger = new Exchanger<String>();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName()+":������...");
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));
					String data1 = "����";
					String data2 = exchanger.exchange(data1);
					System.out.println(Thread.currentThread().getName()+"���õ���"+data2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("-->"+Thread.currentThread().getName()+":�����...");
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));
					String data1 = "10Ԫ";
					String data2 = exchanger.exchange(data1);
					System.out.println("-->"+Thread.currentThread().getName()+":�õ���"+data2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}