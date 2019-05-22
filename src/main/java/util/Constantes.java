package util;

import java.util.Map;
import com.github.shyiko.dotenv.DotEnv;

public class Constantes {
	private static String ANEXO_DIR;
	private static String APP_URL;
	private static String PRE_URL;
	private static String APP_IMG_URL;
	private static String APP_CSS_URL;
	private static Integer NUMBER_OF_ROWS_PER_PAGE;
	private static String SESSION_MSG;
	private static String DATABASE_CONF_DIR;
	private static String EMAIL_CONF_DIR;
	private static String GUARDIAO_APP;
	private static String LOGO_UFC;
	private static String TEMP_DIR;
	
	private Constantes(){
		
	}
	
	static{
		Map<String, String> dotEnv = DotEnv.load();
		ANEXO_DIR = dotEnv.get("ANEXO_DIR");
		DATABASE_CONF_DIR = dotEnv.get("DATABASE_CONF_DIR");
		EMAIL_CONF_DIR = dotEnv.get("EMAIL_CONF_DIR");
		APP_URL = dotEnv.get("APP_URL");
		APP_IMG_URL = dotEnv.get("APP_IMG_URL");
		APP_CSS_URL = dotEnv.get("APP_CSS_URL");
		NUMBER_OF_ROWS_PER_PAGE = Integer.valueOf(dotEnv.get("NUMBER_OF_ROWS_PER_PAGE"));
		SESSION_MSG = dotEnv.get("SESSION_MSG");
		PRE_URL = dotEnv.get("PRE_URL");
		GUARDIAO_APP = dotEnv.get("GUARDIAO_APP");
		LOGO_UFC = dotEnv.get("LOGO_UFC");
		TEMP_DIR = dotEnv.get("TEMP_DIR");
	}
	
	public static String getAnexoDir() {
		return ANEXO_DIR;
	}

	public static String getAppUrl() {
		return APP_URL;
	}

	public static String getPreUrl() {
		return PRE_URL;
	}

	public static String getAppImgUrl() {
		return APP_IMG_URL;
	}

	public static String getAppCssUrl() {
		return APP_CSS_URL;
	}

	public static Integer getNumberOfRowsPerPage() {
		return NUMBER_OF_ROWS_PER_PAGE;
	}

	public static String getSessionMsg() {
		return SESSION_MSG;
	}

	public static String getDatabaseConfDir() {
		return DATABASE_CONF_DIR;
	}

	public static String getEmailConfDir() {
		return EMAIL_CONF_DIR;
	}
	
	public static String getGuardiaoApp() {
		return GUARDIAO_APP;
	}
	
	public static String getLogoUfc() {
		return LOGO_UFC;
	}
	
	public static String getTempDir() {
		return TEMP_DIR;
	}
	
}
