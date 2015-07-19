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
								+ ":�����˵�" + food.getId() + "�ŷ�");
						TimeUnit.MILLISECONDS.sleep(100);
						foods.remove(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//					SaleWindow.class.notify();   //�������һ���ȴ����߳�
					SaleWindow.class.notifyAll();//�������еȴ����߳�
				} else {
					System.out.println(Thread.currentThread().getName()
							+ ":�����ˡ���ʦ�Ͻ���������Ϣ��...");
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
