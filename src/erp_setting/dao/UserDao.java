package erp_setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import erp_setting.Config;
import erp_setting.jdbc.DBCon;
import erp_setting.jdbc.JdbcUtil;

public class UserDao {
	private static UserDao instance = new UserDao();
	
	private UserDao() {}

	public static UserDao getInstance() {
		return instance;
	}

	public void createUserAndGrant(String dbName) {
		String sql = "grant select, insert, update, delete on " + dbName + ".* to ? identified by ?";
		PreparedStatement pstmt = null;
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Config.PJT_USER);
			pstmt.setString(2, Config.PJT_PASSWD);
			pstmt.execute();
			System.out.printf("Create User(%s) Success! %n", Config.PJT_USER);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}