package com.dtinone.datashare.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class GeneralForm {

	@ApiModelProperty("目录分类的code码")
	private String catagoryCode;
	@ApiModelProperty("信息资源名称")
	private String tableName;
	@ApiModelProperty("提供方一级")
	private String providerFirst;
	@ApiModelProperty("提供方二级级")
	private String providerSecond;
	@ApiModelProperty("目录分类的中文名字")
	private String catagoryName;
	@ApiModelProperty("信息项列表")
	private List<ListDataContent> listData;
}
