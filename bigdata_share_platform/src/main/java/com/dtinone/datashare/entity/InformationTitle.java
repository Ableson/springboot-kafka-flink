package com.dtinone.datashare.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiModel
public class InformationTitle {

	private Integer idKey;
	@ApiModelProperty("标题的名字")
	private String name;
	private Integer parentId;
	private Date createTime;
	@ApiModelProperty("数据的状态")
	private Integer remove;
	@ApiModelProperty("标题的英文名字")
	private String nameEn;
	
	@ApiModelProperty("对应的栏位格式类型-text/select")
	private String layout;
	@ApiModelProperty("写入时,使用的属性名字")
	private String keyCode;
	@ApiModelProperty("是否需要查询字典表,对应字典表名称-暂时用ID")
	private String isQueryDict;
	@ApiModelProperty("是否是必填项 0:非必填 1:必填")
	private Integer require;
	
}
