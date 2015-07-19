package cn.crxy.ThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 变形金刚
 * 
 * 想要每一个线程有且仅有一个对象
 */
public class Transformer2 {
	//private static Map<Thread, Transformer2> maps = new HashMap<Thread, Transformer2>();
	
	private static ThreadLocal<Transformer2> maps = new ThreadLocal<Transformer2>();
	private String shape;//形状

	
	private Transformer2(){}
	
	public static Transformer2 getInstance(){
		Transformer2 instance = maps.get();
		if(instance == null){
			instance = new Transformer2();
			maps.set(instance);
			
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
