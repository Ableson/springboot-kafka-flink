package com.dtinone.datashare.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("DemoMode")
public class DemoMode {

	private Integer idKey;
	@ApiModelProperty("模板名字")
	private String name;
	@ApiModelProperty("模板的父级ID")
	private Integer parentId;
	private Date createTime;
	private Integer remove;
}
