package class5.threadLocal;

public class Test {
	/**
	 * 本例子的模型是：有三个小孩，分别玩三个变形金刚，
	 * 但是不管小孩玩自己的变形金刚多少次，都为同一个
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Child()).start();
		}
	}
}
