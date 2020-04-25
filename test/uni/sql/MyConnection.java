package kosta.uni.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	/**
	 * 오라클DB에 접속한다. localhost 호스트, ora1/ora1계정에 접속한다.
	 * @return 성공시 Connection객체를 반환, 실패시 null
	 * @throws Exception JDBC드라이버 오류이거나 계정 접속 실패시 오류 메시지를 갖는다
	 */
	public static Connection getConnection() throws Exception {
		return getConnection("localhost", "kosta_uni", "uni");
	}

	/**
	 * 오라클DB에 접속한다.
	 * @param host 접속할 DB IP
	 * @param user 접속할 계정명
	 * @param password 접속할 계정 비밀번호
	 * @return 성공시 Connection객체를 반환, 실패시 null
	 * @throws Exception JDBC드라이버 오류이거나 계정 접속 실패시 오류 메시지를 갖는다
	 */
	public static Connection getConnection(String host, String user, String password) throws Exception {
		Connection con = null;
		try {
			// jdbc드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("JDBC드라이버 로드 실패");
		}
		// db연결
		String url = "jdbc:oracle:thin:@" + host + ":1521:xe";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new SQLException(user + "계정 접속 실패");
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
