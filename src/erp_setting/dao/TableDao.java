package erp_setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import erp_setting.jdbc.DBCon;
import erp_setting.jdbc.JdbcUtil;

public class TableDao {
	private PreparedStatement pstmt;
	
	private static TableDao instance = new TableDao();

	private TableDao() {}

	public static TableDao getInstance() {
		return instance;
	}

	public void createTable(String sql) {
		Connection con = DBCon.getConnection();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
			System.out.printf("CREATE TABLE(%s) Success! %n",sql.substring(13, sql.indexOf("(")).trim());
		} catch (SQLException e) {
			System.out.printf("CREATE TABLE(%s) Fail! %n",	sql.substring(13, sql.indexOf("(")).trim());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

	}
	
	public void setForeignKeyCheck(int isCheck){
		try{
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("SET FOREIGN_KEY_CHECKS = ?");
			pstmt.setInt(1, isCheck);
			pstmt.execute();
//			System.out.printf("SET FOREIGN_KEY_CHECKS(%s) Success!%n", isCheck==0?"False":"True");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
	}

}
