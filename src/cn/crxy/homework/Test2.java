package cn.crxy.homework;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * �ڶ��⣺�ֳɳ����е�Test���еĴ����ڲ��ϵز������ݣ�Ȼ�󽻸�TestDo.doSome()����ȥ����
 * �ͺ����������ڲ��ϵز������ݣ��������ڲ����������ݡ��뽫����������10���߳������������߲��������ݣ�
 * ��Щ�����߶�����TestDo.doSome()����ȥ���д�����ÿ�������߶���Ҫһ����ܴ����꣬
 * ����Ӧ��֤��Щ�������߳�����������������ݣ�ֻ����һ�����������������һ�������߲����������ݣ�
 * ��һ����������˭�����ԣ���Ҫ��֤��Щ�������߳��õ�����������˳��ġ� ԭʼ�������£�
 */
public class Test2 {

	public static void main(String[] args) {
		//һ���������У�����ÿ�������������ȴ���һ���̵߳Ķ�Ӧ�Ƴ�����
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
		final Semaphore semaphore = new Semaphore(1);

		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						String output = TestDo1.doSome(queue.take());//��ȡ���Ƴ��˶��е�ͷ�����б�Ҫ��ȴ���һ���̲߳�����
						System.out.println(Thread.currentThread().getName() + ":" + output);
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		for (int i = 0; i < 10; i++) { // ���в��ܸĶ�
			String input = i + ""; // ���в��ܸĶ�
			
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			/*String output = TestDo1.doSome(input);
			System.out.println(Thread.currentThread().getName() + ":" + output);*/
		}
		
		
	}
}

// ���ܸĶ���TestDo1��
class TestDo1 {
	public static String doSome(String input) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String output = input + ":" + (System.currentTimeMillis() / 1000);
		return output;
	}
}