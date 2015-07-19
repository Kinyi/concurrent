package cn.crxy.ThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 变形金刚
 * 
 * 想要每一个线程有且仅有一个对象
 */
public class Transformer {
	private static Map<Thread, Transformer> maps = new HashMap<Thread, Transformer>();
	private String shape;//形状

	
	private Transformer(){}
	
	public static Transformer getInstance(){
		Transformer instance = maps.get(Thread.currentThread());
		if(instance == null){
			instance = new Transformer();
			maps.put(Thread.currentThread(), instance);
			
		}
		return instance;
		
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	
	
}
