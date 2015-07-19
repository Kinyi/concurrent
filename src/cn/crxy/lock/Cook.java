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
			//����
			Lock lock = MyLock.LOCK;
			Condition cook_con = MyLock.COOK_CON;
			Condition sale_con = MyLock.SALE_CON;
			lock.lock();
				if (foods.size() < MAXSIZE) {
					Food food = new Food(num++ + "");
					foods.add(food);
					System.out.println(Thread.currentThread().getName()
							+ ":�����˵�" + food.getId() + "�ŷ�");
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sale_con.signal();  //���ѵȴ��е�һ���߳�
				} else {
					System.out.println(Thread.currentThread().getName()
							+ ":�������������ڸϽ���������Ϣ��...");
					try {
						cook_con.await();  //�ȴ�
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			//�ͷ���
			lock.unlock();
		}
	}

	@Override
	public void run() {
		produce();
	}
}
