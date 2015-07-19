package class5.threadLocal;

public class Transformer {
	
	private String type;
	private static ThreadLocal<Transformer> maps = new ThreadLocal<Transformer>();
	
	private Transformer(){}
	//把创建对象进行封装，使每个线程有且只有一个对象
	public static Transformer getInstance(){
		Transformer instance = maps.get();
		if (instance == null) {
			instance = new Transformer();
			maps.set(instance);
		}
		return instance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
