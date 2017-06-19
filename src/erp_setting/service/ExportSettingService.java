package erp_setting.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import erp_setting.Config;
import erp_setting.dao.DataBaseDao;
import erp_setting.jdbc.DBCon;
import erp_setting.jdbc.JdbcUtil;

public class ExportSettingService implements ServiceInterface{
	
	@Override
	public void initSetting() {
		DataBaseDao dao = DataBaseDao.getInstance();
		dao.selectUseDatabase();
		String sql = "select * from %s";
		checkBackupDir();
		for(String tableName : Config.EXPORT_TABLE_NAME){
			executeExportData(sql, tableName);
		}		
		JOptionPane.showMessageDialog(null, "ERP 데이터베이스 백업 완료~!");
	}
	
	private void checkBackupDir() {
		File backupDir = new File(Config.EXPORT_DIR);
		if (backupDir.exists()){
			backupDir.delete();
			System.out.printf("%s Delete Success! %n",backupDir.getName());
		}else{
			backupDir.mkdir();
			System.out.printf("%s make dir Success! %n",Config.EXPORT_DIR);
		}
	}

	public void executeExportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(sql, tableName));
			exportData(rs, tableName);
		} catch (SQLException e) {
			System.out.printf("error %d : %s %n", e.getErrorCode(), e.getMessage());
		} finally {
			JdbcUtil.close(stmt);
		}
	}
	
	public void exportData(ResultSet rs, String tableName) {
		try {
			StringBuilder sb = new StringBuilder();
			int colCnt = rs.getMetaData().getColumnCount(); // 컬럼의 개수
			int lineCount = 0;
			while (rs.next()) {
				for (int i = 1; i <= colCnt; i++) {
					sb.append(rs.getObject(i) + ","); // 필드사이 구분 [,] 찍어줌
				}
				sb.replace(sb.length() - 1, sb.length(), ""); // 마지막라인 [,] 제거
				
				sb.append("\r\n");
				lineCount++;
			}

			File file = new File(Config.EXPORT_DIR);
			if (!file.exists()) {
				file.mkdir();
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(Config.EXPORT_DIR + tableName + ".txt"))) {
				bw.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.printf("Export Table(%s) %d Rows Success! %n",tableName, lineCount);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}