package cn.crxy.synchronizer;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * 本例的模型是：一个人卖包子，一个人买包子，双方达成协议后，卖方得到钱，买方得到包子
 * 
 */
public class ExchangerDemo {

	public static void main(String[] args) {
		final Exchanger<String> exchanger = new Exchanger<String>();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName()+":卖包子...");
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));
					String data1 = "包子";
					String data2 = exchanger.exchange(data1);
					System.out.println(Thread.currentThread().getName()+"：拿到了"+data2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("-->"+Thread.currentThread().getName()+":买包子...");
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));
					String data1 = "10元";
					String data2 = exchanger.exchange(data1);
					System.out.println("-->"+Thread.currentThread().getName()+":拿到了"+data2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}