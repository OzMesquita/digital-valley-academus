package br.ufc.russas.n2s.academus.util;

import java.util.Map;
import com.github.shyiko.dotenv.DotEnv;

public class Constantes {
	private static String APP_URL;
	private static String PRE_URL;
	private static String APP_ASSETS_URL;
	private static String APP_JS_URL;
	private static String APP_IMG_URL;
	private static String APP_CSS_URL;
	private static Integer NUMBER_OF_ROWS_PER_PAGE;
	private static String SESSION_MSG;
	private static String DATABASE_CONF_DIR;
	private static String EMAIL_CONF_DIR;
	
	private Constantes(){
		
	}
	
	static{
		Map<String, String> dotEnv = DotEnv.load();
		DATABASE_CONF_DIR = dotEnv.get("DATABASE_CONF_DIR");
		EMAIL_CONF_DIR = dotEnv.get("EMAIL_CONF_DIR");
		APP_URL = dotEnv.get("APP_URL");
		APP_ASSETS_URL = dotEnv.get("APP_ASSETS_URL");
		APP_JS_URL = dotEnv.get("APP_JS_URL");
		APP_IMG_URL = dotEnv.get("APP_IMG_URL");
		APP_CSS_URL = dotEnv.get("APP_CSS_URL");
		NUMBER_OF_ROWS_PER_PAGE = Integer.valueOf(dotEnv.get("NUMBER_OF_ROWS_PER_PAGE"));
		SESSION_MSG = dotEnv.get("SESSION_MSG");
		PRE_URL = dotEnv.get("PRE_URL");
	}

	public static String getAppUrl() {
		return APP_URL;
	}

	public static String getPreUrl() {
		return PRE_URL;
	}

	public static String getAppAssetsUrl() {
		return APP_ASSETS_URL;
	}

	public static String getAppJsUrl() {
		return APP_JS_URL;
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
	
	
}
