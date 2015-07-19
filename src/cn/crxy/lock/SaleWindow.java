package cn.crxy.lock;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

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
			//加锁
			Lock lock = MyLock.LOCK;
			Condition cook_con = MyLock.COOK_CON;
			Condition sale_con = MyLock.SALE_CON;
			lock.lock();
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
					cook_con.signal();  //唤醒等待中的一条线程
				} else {
					System.out.println(Thread.currentThread().getName()
							+ ":卖完了。厨师赶紧做，我休息了...");
					try {
						sale_con.await();  //等待
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
		sale();
	}
}
