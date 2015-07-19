package cn.crxy.thread;
import java.lang.Thread;
import java.lang.ThreadGroup;
public class ThreadGroupDemo {

	public static void main(String[] args) {
		
		ThreadGroup group = new ThreadGroup("demo");
		
		for (int i = 0; i < 3; i++) {
			new Thread(group,new Runnable() {
				public void run() {
					System.out.print("");
				}
			}).start();
		}
		group.list();
		int count = group.activeCount();//����߳����л���߳�
		System.out.println(count);
		Thread threads[] = new Thread[group.activeCount()];
		group.enumerate(threads);//����ǰ�߳����л���̸߳��Ƶ�һ���߳�������ȥ�� 
		
		for (Thread thread : threads) {
			System.out.println(thread.getName());
		}
		ThreadGroup parent = group.getParent();
		
		parent.list();
		ThreadGroup parent2 = parent.getParent();
		parent2.list();
		
		
	}
}
