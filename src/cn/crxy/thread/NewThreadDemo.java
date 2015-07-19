package cn.crxy.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class NewThreadDemo {

	
	public static void main(String[] args) throws Exception {
		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				
				System.out.println("hello");
				return "world";
			}
		});
		//method1();
		//method2();
		method3(task);
		String result = task.get();
		System.out.println(result);
		
		
	}

	private static void method1() {
		new Thread(){
			@Override
			public void run() {
				System.out.println("hello");
			}
		}.start();
	}
	
	private static void method2() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("hello");
			}
		}).start();
	}
	
	private static void method3(FutureTask<String > task) {
		
		
		
		new Thread(task).start();
	}
}
