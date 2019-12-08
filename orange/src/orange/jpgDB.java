package orange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jpgDB {

	public static Connection getMySQLConnection() {
		Connection conn = null;
		try {
			//mysqlconnector 6.xx 이상부터 servertimezone, useSSL 써야함
			//classforName도 사라짐
			String url = "jdbc:mysql://localhost:3306/jpg?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
			String user = "root";
			String password = "0000";
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			System.out.println("데이터베이스 접속 정보가 올바르지 않습니다.");
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {
		if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}
	public static void close(Statement stmt) {
		if(stmt != null) { try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}
	public static void close(ResultSet rs) {
		if(rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}
}









