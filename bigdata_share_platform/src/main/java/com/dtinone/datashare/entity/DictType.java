package com.dtinone.datashare.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("DictType")
public class DictType {

	private Integer idKey;
	@ApiModelProperty("字典名称|下拉选内容,parentId为null,表示该数据为下拉框所代表的类目, 其他数据列等于该idKey,表示为所属内容")
	private String name;
	private Integer parentId;
	private Date createTime;
	private Integer remove;
}
