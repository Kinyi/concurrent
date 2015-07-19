package class5.threadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Child implements Runnable{

	private Transformer transformer;  //代表每个小孩都拥有变形金刚

	void play() {
		while (true) {//循环用于检验是否是同一个对象
			try {
				transformer = Transformer.getInstance(); //获取变形金刚对象，有且只有一个
				System.out.println(Thread.currentThread().getName() + ":"+ transformer);//比较是否是同一个对象
				String str = "擎天柱" + new Random().nextInt(10);
				System.out.println(Thread.currentThread().getName()+ ":将变形金刚设置成:" + str);
				transformer.setType(str);
				TimeUnit.SECONDS.sleep(2);
				String type = transformer.getType();
				System.out.println(Thread.currentThread().getName()+ ":获取到的变形金刚是:" + type);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		play();
	}
}
