package cn.crxy.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {

	public static final Lock LOCK = new ReentrantLock();
	//������Condition�ֱ����Cook��SaleWindow�������Ƿ��뿪��
	public static final Condition COOK_CON = LOCK.newCondition();
	public static final Condition SALE_CON = LOCK.newCondition();
	
	private MyLock(){}
}
