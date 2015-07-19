package cn.crxy.ThreadLocal;

public class Test {

	public static void main(String[] args) {
		
		for (int i = 0; i < 3; i++) {
			new Thread(new Child2()).start();
		}
	}
}
