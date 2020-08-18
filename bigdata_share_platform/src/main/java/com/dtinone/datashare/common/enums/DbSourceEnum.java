package com.dtinone.datashare.common.enums;

/**
 * 状态枚举
 */
public enum DbSourceEnum {
    MYSQL("mysql","com.mysql.jdbc.Driver"),
    ORACLE("oracle","oracle.jdbc.driver.OracleDriver"),
    DB2("db2","com.ibm.db2.jcc.DB2Driver"),
    HIVE("hive","org.apache.hive.jdbc.HiveDriver"),
    MODEL_TEST("dataSync",""),
    MODEL_CURR("resourceCatalog","");
    
    private String type;
    private String value;

    DbSourceEnum(String t, String v) {
        this.type = t;
        this.value = v;
    }

    public static DbSourceEnum nameOfValue(String value) {
        for (DbSourceEnum info : DbSourceEnum.values()) {
            if (info.getValue().equals(value)) {
                return info;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
    public String getType() {
    	return type;
    }

}
