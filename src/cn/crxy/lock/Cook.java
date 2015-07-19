package cn.crxy.lock;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Cook implements Runnable{
	private static final int MAXSIZE = 1;
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
			//加锁
			Lock lock = MyLock.LOCK;
			Condition cook_con = MyLock.COOK_CON;
			Condition sale_con = MyLock.SALE_CON;
			lock.lock();
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
					sale_con.signal();  //唤醒等待中的一条线程
				} else {
					System.out.println(Thread.currentThread().getName()
							+ ":饭桌已满。窗口赶紧卖，我休息了...");
					try {
						cook_con.await();  //等待
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			//释放锁
			lock.unlock();
		}
	}

	@Override
	public void run() {
		produce();
	}
}
