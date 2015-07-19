package cn.crxy.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * 本例的模型是：饭馆卖饭，三个窗口，四个厨师，窗口一边卖饭厨师一边做饭
 * 如果桌上没有饭了，就提醒厨师饭卖完了；如果桌上摆满饭了，就提醒窗口卖饭 
 *
 */
public class Test {

	public static void main(String[] args) {
		
		List<Food> foods = new ArrayList<Food>();
		for (int i = 0; i < 3; i++) {
			new Thread(new Cook(foods),"cook"+(i+1)).start();
		}
		
		for (int i = 0; i < 3; i++) {
			new Thread(new SaleWindow(foods),"sale"+(i+1)).start();
		}
	}
}
