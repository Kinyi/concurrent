package cn.crxy.thread;

public class ThreadGroupTest {
	
	public static void main(String[] args) {
		Thread main = Thread.currentThread();
		ThreadGroup mainGroup = main.getThreadGroup();//��ȡ���߳����ڵ��߳���
		
		Thread t1 = new Thread(mainGroup, "myThread");
		t1.start();				  //��ȡ���߳����л���߳���
		Thread[] arr = new Thread[mainGroup.activeCount()];
		mainGroup.enumerate(arr);//���߳����л���̸߳��Ƶ�ָ��������
		
		for (Thread thread : arr) {
			System.out.println(thread.getName());
		}
		
		ThreadGroup parent = mainGroup.getParent();
		parent.list();//��ӡ�߳����������Ϣ
		
		mainGroup.list();//��ӡ�߳����������Ϣ
	}
}
