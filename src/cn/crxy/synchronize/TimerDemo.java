package cn.crxy.synchronize;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerDemo {

	public static void main(String[] args) {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("boom!");
			}
		}, 3000, TimeUnit.SECONDS.toMillis(1));
	}

}
