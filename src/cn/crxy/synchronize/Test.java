package cn.crxy.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * ������ģ���ǣ������������������ڣ��ĸ���ʦ������һ��������ʦһ������
 * �������û�з��ˣ������ѳ�ʦ�������ˣ�������ϰ������ˣ������Ѵ������� 
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
