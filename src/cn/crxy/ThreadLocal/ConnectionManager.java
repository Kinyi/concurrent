package cn.crxy.ThreadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * ThreadLocal 占内存 -- 空间换时间
 * 内存泄露？--》WeakReference（弱引用）
 * 
 * 即使使用了弱引用技术，也应该在实际开发中手动调用remove()释放内存;
 * 
 */

public class ConnectionManager {

	private static ThreadLocal<Connection> maps = new ThreadLocal<Connection>();
	
	public Connection getInstance(){
		Connection conn = maps.get();
		
		if(conn == null) {
			try {
				conn = DriverManager.getConnection("");//获取Connection对象
				maps.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
