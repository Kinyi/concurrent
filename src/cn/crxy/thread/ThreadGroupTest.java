package cn.crxy.thread;

public class ThreadGroupTest {
	
	public static void main(String[] args) {
		Thread main = Thread.currentThread();
		ThreadGroup mainGroup = main.getThreadGroup();//获取该线程所在的线程组
		
		Thread t1 = new Thread(mainGroup, "myThread");
		t1.start();				  //获取该线程组中活动的线程数
		Thread[] arr = new Thread[mainGroup.activeCount()];
		mainGroup.enumerate(arr);//将线程组中活动的线程复制到指定数组中
		
		for (Thread thread : arr) {
			System.out.println(thread.getName());
		}
		
		ThreadGroup parent = mainGroup.getParent();
		parent.list();//打印线程组的所有信息
		
		mainGroup.list();//打印线程组的所有信息
	}
}
