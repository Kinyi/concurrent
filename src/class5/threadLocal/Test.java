package class5.threadLocal;

public class Test {
	/**
	 * �����ӵ�ģ���ǣ�������С�����ֱ����������ν�գ�
	 * ���ǲ���С�����Լ��ı��ν�ն��ٴΣ���Ϊͬһ��
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Child()).start();
		}
	}
}
