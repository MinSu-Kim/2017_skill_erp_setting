package erp_setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import erp_setting.Config;
import erp_setting.jdbc.DBCon;
import erp_setting.jdbc.JdbcUtil;

public class DataBaseDao {
	private PreparedStatement pstmt;
	
	private static DataBaseDao instance = new DataBaseDao();
	
	private DataBaseDao() {}

	public static DataBaseDao getInstance() {
		return instance;
	}

	public void createDatabase() {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("CREATE DATABASE " + Config.DB_NAME);
			pstmt.execute();
			System.out.printf("CREATE DATABASE(%s) Success! %n", Config.DB_NAME);
		} catch (SQLException e) {
			if (e.getErrorCode()==1007){
				System.out.printf("DATABASE(%s) Exist! %n", Config.DB_NAME);
				dropDatabase();
				createDatabase();
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private void dropDatabase() {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("DROP DATABASE IF EXISTS " + Config.DB_NAME);
			pstmt.execute();
			System.out.printf("DROP DATABASE(%s) Success! %n", Config.DB_NAME);
		} catch (SQLException e) {
			System.out.printf("DROP DATABASE(%s) Fail! %n", Config.DB_NAME);
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	public void selectUseDatabase() {
		try {
			Connection con = DBCon.getConnection();
			pstmt = con.prepareStatement("USE " + Config.DB_NAME);
			pstmt.execute();
//			System.out.printf("USE DATABASE(%s) Selected Success! %n", Config.DB_NAME);
		} catch (SQLException e) {
//			System.out.printf("USE DATABASE(%s) Selected Fail! %n", Config.DB_NAME);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}		
	}
	


}
