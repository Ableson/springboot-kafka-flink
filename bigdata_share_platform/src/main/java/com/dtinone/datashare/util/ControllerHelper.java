package com.dtinone.datashare.util;

import com.dtinone.datashare.common.enums.DbSourceEnum;

public class ControllerHelper {

	public static String getDriverNameForDbType(String dbType) {
		
		for (DbSourceEnum obj : DbSourceEnum.values()) {
			if (null != dbType && dbType.toLowerCase().equals(obj.getType().toLowerCase())) {
				return obj.getValue();
			}
		}
		return null;
	}
}
