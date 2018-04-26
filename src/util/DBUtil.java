
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类，这个类的作用是初始化驱动，并且提供一个getConnection用于获取连接。
 * Creater:@我没有三颗心脏
 * Data:2018年4月17日22:11:50
 */
public class DBUtil {

	static String ip = "127.0.0.1";
	static int port = 3306;
	static String database = "student";
	static String encoding = "UTF-8";
	static String loginName = "root";
	static String password = "root";

	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接数据库并返回连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
		return DriverManager.getConnection(url, loginName, password);
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());

	}

}