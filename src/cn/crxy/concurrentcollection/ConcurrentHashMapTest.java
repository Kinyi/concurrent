package cn.crxy.concurrentcollection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//测试3中并发集合的读写效率
public class ConcurrentHashMapTest {

	public static void main(String[] args) {
		
		final Map<Integer, Integer> hm = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		final Map<Integer, Integer> ht = new Hashtable<Integer, Integer>();
		final Map<Integer, Integer> chm = new ConcurrentHashMap<Integer, Integer>();
		putMap(hm);
		putMap(ht);
		putMap(chm);
		
	}

	private static void putMap(final Map<Integer, Integer> hm) {
		long begin = System.currentTimeMillis();
		for (int k = 0; k < 100; k++) {
			for (int i = 0; i < 1000; i++) {
				final int key = i;
				new Thread(new Runnable() {

					@Override
					public void run() {
						for (int j = 0; j < 1000; j++) {
							hm.put(key, j);
						}
					}
				}).start();
			}
		}
		long end = System.currentTimeMillis();
		
		System.out.println(end - begin);
	}
}
