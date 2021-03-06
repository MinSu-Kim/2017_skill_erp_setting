package erp_setting.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import erp_setting.Config;
import erp_setting.dao.DataBaseDao;
import erp_setting.dao.TableDao;
import erp_setting.dao.UserDao;
import erp_setting.jdbc.DBCon;
import erp_setting.jdbc.JdbcUtil;

public class InitSettingService{

	public void initSetting() {
		createDataBase();	// 데이터베이스를 생성
		createTable(); 		// 해당 데이터베이스에서 테이블 생성
		createUser(); 		// 해당 데이터베이스 사용자 추가
//		new ImportSettingService().new LoadPost().start();
		loadPost();
		JOptionPane.showMessageDialog(null, "ERP 데이터베이스 초기화 완료~!");
	}

	private void createDataBase() {
		DataBaseDao.getInstance().createDatabase();
		DataBaseDao.getInstance().selectUseDatabase();
	}

	private void createTable() {
		TableDao dao = TableDao.getInstance();
		for (int i = 0; i < Config.CREATE_TABLE_SQL.length; i++) {
			dao.createTable(Config.CREATE_TABLE_SQL[i]);
		}
	}

	private void createUser() {
		UserDao.getInstance().createUserAndGrant(Config.DB_NAME);
	}
	
	private void loadPost(){
		File file = new File(Config.IMPORT_DIR+"Post");
		File[] fileNames = file.listFiles();
		String sql = "LOAD data LOCAL INFILE '%s' INTO table  post   character set 'euckr'  fields TERMINATED by '|' IGNORE 1 lines "
				+ "(@zipcode, @sido, @d, @sigungu , @d, @eupmyun, @d,  @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @courtdong, @ri ,@admindong, @d, @zibun1, @d, @zibun2, @d, @d) "
				+ "set zipcode=@zipcode, sido=@sido, sigungu=@sigungu ,eupmyun=@eupmyun, doro=@doro, building1=@building1, building2=@building2, courtdong=@courtdong, ri=@ri,admindong=@admindong, zibun1=@zibun1, zibun2=@zibun2";
		for(File f:fileNames){
			executeImportData(String.format(sql,f.getAbsolutePath().replace("\\", "/")), f.getName());
		}

	}

	private void executeImportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection();
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.printf("Import Table(%s) %d Rows Success! %n",tableName, stmt.getUpdateCount());
			
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
