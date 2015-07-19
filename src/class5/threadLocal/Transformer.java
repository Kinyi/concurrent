package class5.threadLocal;

public class Transformer {
	
	private String type;
	private static ThreadLocal<Transformer> maps = new ThreadLocal<Transformer>();
	
	private Transformer(){}
	//�Ѵ���������з�װ��ʹÿ���߳�����ֻ��һ������
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
