package erp_setting.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcUtil {
	
	public static void close(Statement stmt){
		if (stmt != null){
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static void close(ResultSet rs){
		if (rs != null){
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static void close(ResultSet rs, Statement stmt){
		close(rs);
		close(stmt);
	}

	public static void close(InputStream is){
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
