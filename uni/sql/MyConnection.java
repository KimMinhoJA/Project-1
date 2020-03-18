package kosta.uni.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	/**
	 * ����ŬDB�� �����Ѵ�. localhost ȣ��Ʈ, ora1/ora1������ �����Ѵ�.
	 * @return ������ Connection��ü�� ��ȯ, ���н� null
	 * @throws Exception JDBC����̹� �����̰ų� ���� ���� ���н� ���� �޽����� ���´�
	 */
	public static Connection getConnection() throws Exception {
		return getConnection("localhost", "kosta_uni", "uni");
	}

	/**
	 * ����ŬDB�� �����Ѵ�.
	 * @param host ������ DB IP
	 * @param user ������ ������
	 * @param password ������ ���� ��й�ȣ
	 * @return ������ Connection��ü�� ��ȯ, ���н� null
	 * @throws Exception JDBC����̹� �����̰ų� ���� ���� ���н� ���� �޽����� ���´�
	 */
	public static Connection getConnection(String host, String user, String password) throws Exception {
		Connection con = null;
		try {
			// jdbc����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("JDBC����̹� �ε� ����");
		}
		// db����
		String url = "jdbc:oracle:thin:@" + host + ":1521:xe";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new SQLException(user + "���� ���� ����");
		}
		return con;
	}

	/**
	 * 
	 * @param con
	 * @param rs
	 * @param stmt
	 * @throws Exception
	 */
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
			}
		}
	}
}
