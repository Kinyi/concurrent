package class5.threadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Child implements Runnable{

	private Transformer transformer;  //����ÿ��С����ӵ�б��ν��

	void play() {
		while (true) {//ѭ�����ڼ����Ƿ���ͬһ������
			try {
				transformer = Transformer.getInstance(); //��ȡ���ν�ն�������ֻ��һ��
				System.out.println(Thread.currentThread().getName() + ":"+ transformer);//�Ƚ��Ƿ���ͬһ������
				String str = "������" + new Random().nextInt(10);
				System.out.println(Thread.currentThread().getName()+ ":�����ν�����ó�:" + str);
				transformer.setType(str);
				TimeUnit.SECONDS.sleep(2);
				String type = transformer.getType();
				System.out.println(Thread.currentThread().getName()+ ":��ȡ���ı��ν����:" + type);
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
