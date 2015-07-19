package cn.crxy.synchronize;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Cook implements Runnable{
	private static final int MAXSIZE = 10;
	private static int num = 1;

	private List<Food> foods;

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public Cook() {}

	public Cook(List<Food> foods) {
		super();
		this.foods = foods;
	}
	
	private void produce(){
		while (true) {
			synchronized (SaleWindow.class) {
				if (foods.size() < MAXSIZE) {
					Food food = new Food(num++ + "");
					foods.add(food);
					System.out.println(Thread.currentThread().getName()
							+ ":做好了第" + food.getId() + "号饭");
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//					SaleWindow.class.notify();   //随机唤醒一条等待的线程
					SaleWindow.class.notifyAll();//唤醒所有等待的线程
				} else {
					System.out.println(Thread.currentThread().getName()
							+ ":饭桌已满。窗口赶紧卖，我休息了...");
					try {
						SaleWindow.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void run() {
		produce();
	}
}
