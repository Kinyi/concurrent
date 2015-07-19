package cn.crxy.synchronize;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SaleWindow implements Runnable{

	private List<Food> foods;

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public SaleWindow() {
	}

	public SaleWindow(List<Food> foods) {
		super();
		this.foods = foods;
	}

	public void sale() {
		while (true) {
			synchronized (SaleWindow.class) {
				if (foods.size() > 0) {
					try {
						Food food = foods.get(0);
						System.out.println(Thread.currentThread().getName()
								+ ":卖出了第" + food.getId() + "号饭");
						TimeUnit.MILLISECONDS.sleep(100);
						foods.remove(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//					SaleWindow.class.notify();   //随机唤醒一条等待的线程
					SaleWindow.class.notifyAll();//唤醒所有等待的线程
				} else {
					System.out.println(Thread.currentThread().getName()
							+ ":卖完了。厨师赶紧做，我休息了...");
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
		sale();
	}
}
