package com.dtinone.datashare.util;

import java.util.List;

import lombok.Data;
/**
 * 目录树实体对象
 * @author 15011
 */
@Data
public class NodeInfo {

	private String title;
	private String key;
	private Integer idKey;
	private Integer parentId;
	private String remark;
	private List<NodeInfo> children;
}
