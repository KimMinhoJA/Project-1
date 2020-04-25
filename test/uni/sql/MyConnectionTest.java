package kosta.uni.sql;

import java.sql.Connection;

public class MyConnectionTest {

	public static void main(String[] args) {
		try {
			Connection con = MyConnection.getConnection();
			MyConnection.close(con, null, null);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
