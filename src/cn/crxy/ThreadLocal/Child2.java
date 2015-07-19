package cn.crxy.ThreadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Child2 implements Runnable {

	private String name;
	private Transformer2 transformer;

	public Child2(String name, Transformer2 transformer) {
		this.name = name;
		this.transformer = transformer;
	}

	public Child2() {
	}

	public void play() {
		// transformer = new Transformer();//每一次玩都会新创建一个对象。想要每一个线程有且仅有一个对象

		transformer = Transformer2.getInstance();
		System.out
				.println(Thread.currentThread().getName() + ":" + transformer);
		Random ran = new Random();
		int num = ran.nextInt(100);
		System.out.println(Thread.currentThread().getName() + ":设置为擎天柱" + num);
		transformer.setShape("擎天柱" + num);
		try {
			TimeUnit.SECONDS.sleep(ran.nextInt(3));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String shape = transformer.getShape();
		System.out.println("-->" + Thread.currentThread().getName() + ":获取为"
				+ shape);

	}

	@Override
	public void run() {
		play();

	}

}
