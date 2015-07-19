package cn.crxy.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ����ģ����ǻ��漼��������һ�β�ѯʱ����Ҫ�����ݿ��ж�ȡ���ݣ�Ȼ�󽫸����ݻ��浽һ�������У����ڶ��β�ѯ������ʱ��
 * ��Ӧ�ôӼ����н�������ȡ����
 * �����ж����߳�ͬʱ���в�ѯʱ����Ϊֻ��������ֵ��ʱ����Ҫ����ֻ����һ���߳�ͬʱ���У�������ȡֵʱ�������ö����߳�
 * ͬʱ���С����ʹ��synchronized������������ȡֵ��������ֵ����ǰ��getValue����ֻ����һ���߳̽��룬Ч��
 * ���ߡ�
 * ��ʱ�������ö�д����ȡֵ������ֵ���룬��������������������
 */
public class Cache {

	private static final Map<String, Object> CACHE = new HashMap<String, Object>();
	private static final ReadWriteLock RWL = new ReentrantReadWriteLock();

	public Object getValue(String key) {
		Object value = null;
		try {  //����try������������ʲô�쳣������ִ��finally��Ľ�������
			RWL.readLock().lock();    //���϶���
			value = CACHE.get(key);
			if (value == null) {
				try {
					RWL.readLock().unlock();//�ͷŶ���
					RWL.writeLock().lock(); //����д��
					if (value == null) {
						value = "xxx";//�����ݿ��в�ѯ��ֵ������ֵ��value
						CACHE.put(key, value);
					}
				} finally {
					RWL.writeLock().unlock();  //�ͷ�д��
					RWL.readLock().lock();     //���϶���
				}
			}
		} finally {
			RWL.readLock().unlock();
		}
		return value;
	}
}