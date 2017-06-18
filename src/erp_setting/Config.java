package erp_setting;

public class Config {
	
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String CONFIT_RESOURCE_PATH ="resources/config-jdbc.properties";
	
	public static final String DB_NAME = "skill_erp";
	
	public static final String PJT_USER = "user_erp";
	public static final String PJT_PASSWD = "rootroot";
	
	public static final String[] TABLE_NAME = {"title", "department","employee","post"};
	public static final String[] EXPORT_TABLE_NAME = {"title", "department","employee"};
	
	public static final String EXPORT_DIR = System.getProperty("user.dir")+ "\\BackupFiles\\";
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";
	
	private static final String CREATE_SQL_TITLE=
			"CREATE TABLE title ("
			+ "titleno INT(11) NOT NULL,	"
			+ "titlename VARCHAR(20) NOT NULL, "
			+ "PRIMARY KEY (no))";
	
	private static final String CREATE_SQL_DEPT=
			"CREATE TABLE department (	"
			+ "deptno   INT(11)     NOT NULL, "
			+ "deptname VARCHAR(10) NOT NULL, "
			+ "floor    INT(11)     NULL, "
			+ "PRIMARY KEY (deptno))";
	
	private static final String CREATE_SQL_EMP=
			"CREATE TABLE employee ("
			+ "empno   INT(11)     NOT NULL, "
			+ "pass    CHAR(41)    NOT NULL, "
			+ "empname VARCHAR(20) NOT NULL, "
			+ "title   INT(11)     NULL, "
			+ "manager INT(11)     NULL, "
			+ "salary  INT(11)     NULL, "
			+ "dno     INT(11)     NULL, "
			+ "post      CHAR(5)      NULL,	"
			+ "address   VARCHAR(120) NULL,	"
			+ "addr_etc  VARCHAR(40)  NULL,	"
			+ "dependent BOOL         NULL,	"
			+ "married   BOOL         NULL, "
			+ "pic       blob         NULL, "
			+ "PRIMARY key (empno),	"
			+ "FOREIGN KEY (dno) REFERENCES department (deptno), "
			+ "FOREIGN KEY (manager) REFERENCES employee (empno), "
			+ "FOREIGN KEY (title) REFERENCES title (no))";
	
	private static final String CREATE_SQL_POST=
			"CREATE TABLE post ("
					+ "zipcode   CHAR(5)     NULL,	"
					+ "sido      VARCHAR(20) NULL,	"
					+ "sigungu   VARCHAR(20) NULL,	"
					+ "eupmyun   VARCHAR(20) NULL,	"
					+ "doro      VARCHAR(80) NULL,	"
					+ "building1 CHAR(5)     NULL,	"
					+ "building2 CHAR(5)     NULL,	"
					+ "courtdong VARCHAR(20) NULL,	"
					+ "ri VARCHAR(20) NULL,	"
					+ "adminDong varchar(20) NULL,	"
					+ "zibun1    VARCHAR(4)  NULL,	"
					+ "zibun2    VARCHAR(4)  NULL)";
	
	public static final String[] CREATE_TABLE_SQL={CREATE_SQL_TITLE, CREATE_SQL_DEPT, CREATE_SQL_EMP, CREATE_SQL_POST};
	
	
	public static String getFilePath(String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append(Config.IMPORT_DIR).append(tableName).append(".txt");
		return sb.toString().replace("\\", "/");
	}
}
