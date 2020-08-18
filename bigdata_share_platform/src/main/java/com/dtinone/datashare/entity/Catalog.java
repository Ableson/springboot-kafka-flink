package com.dtinone.datashare.entity;



import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Catalog", description = "目录实体")
public class Catalog {

	@ApiModelProperty("唯一标识")
	private Integer idKey;
	@ApiModelProperty("目录名字")
	private String name;
	@ApiModelProperty("父目录id")
	private Integer parentId;
	private String remark;
	private Integer remove;
	private Date createTime;
	//用于目录数格式
	private List<Catalog> children;
}
