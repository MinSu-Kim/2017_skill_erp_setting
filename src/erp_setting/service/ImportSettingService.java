package erp_setting.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import erp_setting.Config;
import erp_setting.dao.DataBaseDao;
import erp_setting.dao.TableDao;
import erp_setting.jdbc.DBCon;
import erp_setting.jdbc.JdbcUtil;

public class ImportSettingService implements ServiceInterface{
	
	@Override
	public void initSetting() {
		DataBaseDao.getInstance().selectUseDatabase();
		TableDao.getInstance().setForeignKeyCheck(0);
		for(String tableName : Config.EXPORT_TABLE_NAME){
			executeImportData(String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE %s character set 'UTF8' fields TERMINATED by ','", Config.getFilePath(tableName), tableName), tableName);
		}
		TableDao.getInstance().setForeignKeyCheck(1);	
		JOptionPane.showMessageDialog(null, "ERP 데이터베이스 복원 완료~!");
	}

	protected static void executeImportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection();
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.printf("Import Table(%s) %d Rows Success! %n",tableName, stmt.getUpdateCount());
			System.out.println(sql);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.err.println("중복데이터 존재");
			}
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
		}
	}
	
}

