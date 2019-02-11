package com.example.demo.mid;

import java.util.LinkedHashMap;
import java.util.Map;

public interface MIDConstants {

	/** START: STAGING DB DATASOURCE RELATED ENV VARIABLES NAMES **/
	String STAGING_DB_URL = "GCE_DMT_USER_ORCL_DB_URL";
	String STAGING_DB_USER = "GCE_DMT_USER_ORCL_DB_USER";
	String STAGING_DB_CRED = "GCE_DMT_USER_ORCL_DB_CRED";
	
	/** START: GLSM DB DATASOURCE RELATED ENV VARIABLES NAMES **/
	String GLSM_ORCL_DB_CRED = "GGLSM_ORCL_DB_CRED";
	String GLSM_ORCL_DB_URL= "GLSM_ORCL_DB_URL";
	String GLSM_ORCL_DB_USER = "GLSM_ORCL_DB_USER";
	
	String INPUT_FILE_EXTENSION_XLS ="xls";
	String INPUT_FILE_EXTENSION_XLSX ="xlsx";
	
	Map<Integer, String> SHEET_INDEX_CLASS_MAP = new LinkedHashMap<>();
	
}
