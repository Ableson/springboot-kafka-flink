package com.dtinone.datashare.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("InformationContent")
public class InformationContents {

	@ApiModelProperty("该idKey使用uuid,是同信息项列表id一致")
	private String idKey;
	@ApiModelProperty("除了(信息项+提供方一级部门+提供方二级部门+状态+信息资源名称外),其他的内容都存这里(json格式)")
	private String tableContent;
	@ApiModelProperty("信息所属目录id")
	private Integer catagoryCode;
	@ApiModelProperty("信息所属目录的中文名字-通过catagoryCode反推获得")
	private String catagoryName;
	@ApiModelProperty("数据状态 0-待注册  1-待审核  2-待发布  3-已发布 4-下架")
	private Integer status;
	@ApiModelProperty("创建时间")
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@ApiModelProperty("数据是否有效")
	private Integer remove;
	
	@ApiModelProperty("信息资源名称")
	private String tableName;
	@ApiModelProperty("资源目录提供方一级部门")
	private String providerFirst;
	@ApiModelProperty("资源目录提供方二级部门")
	private String providerSecond;
	@ApiModelProperty("审核描述")
	private String shengHeRemark;
	@ApiModelProperty("下架描述")
	private String xiaJiaRemark;
	@ApiModelProperty("发布时驳回的描述")
	private String boHuiRemark;
	@ApiModelProperty("描述")
	private String remark;
	@ApiModelProperty("发布位置的id")
	private String networkId;

	@ApiModelProperty("模板id")
	private Integer demoModeId;
	@ApiModelProperty("关联分类id")
	private Integer categoryRelationCode;

	@ApiModelProperty("信息项数-不要操作")
	@JsonIgnore
	private Integer infoCount;
	@ApiModelProperty("信息项列表的集合")
	private List<ListDataContent> listData;
}
