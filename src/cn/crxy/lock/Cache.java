package cn.crxy.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 本例模拟的是缓存技术，当第一次查询时，需要从数据库中读取数据，然后将该数据缓存到一个集合中；当第二次查询该数据时，
 * 就应该从集合中将该数据取出。
 * 而当有多条线程同时进行查询时，因为只有在设置值的时候需要控制只能有一条线程同时进行，而当在取值时，可以让多条线程
 * 同时进行。如果使用synchronized方法，不管是取值还是设置值，当前的getValue方法只能有一条线程进入，效率
 * 不高。
 * 此时可以利用读写锁将取值和设置值分离，独立加锁来解决这个问题
 */
public class Cache {

	private static final Map<String, Object> CACHE = new HashMap<String, Object>();
	private static final ReadWriteLock RWL = new ReentrantReadWriteLock();

	public Object getValue(String key) {
		Object value = null;
		try {  //无论try代码块里出现了什么异常，都会执行finally里的解锁操作
			RWL.readLock().lock();    //锁上读锁
			value = CACHE.get(key);
			if (value == null) {
				try {
					RWL.readLock().unlock();//释放读锁
					RWL.writeLock().lock(); //锁上写锁
					if (value == null) {
						value = "xxx";//从数据库中查询出值，并赋值给value
						CACHE.put(key, value);
					}
				} finally {
					RWL.writeLock().unlock();  //释放写锁
					RWL.readLock().lock();     //锁上读锁
				}
			}
		} finally {
			RWL.readLock().unlock();
		}
		return value;
	}
}