package com.dtinone.datashare.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("ListDataContent")
public class ListDataContent {

	private Integer idKey;
	@ApiModelProperty("信息项对应的信息资源id")
	private String parentId;//
	@ApiModelProperty("信息项名称/点击-从系统资源获取-信息项-字段描述")
	private String nameZh;//中文-name
	@ApiModelProperty("信息项名称/点击-从系统资源获取-信息项-字段名")
	private String nameEn;//英文-name
	@ApiModelProperty("信息项名称/点击-从系统资源获取-信息项-类型")
	private String columnType;//列类型
	@ApiModelProperty("信息项名称/点击-从系统资源获取-信息项-长度")
	private Integer columnLength;//列长度
	@ApiModelProperty("字段类型为数字类型时,表示需要的小数位精度")
	private Integer accuracy;//小数位精度
	@ApiModelProperty("共享类型")
	private String shareType;//共享类型
	@ApiModelProperty("是否开放 1代表开启 0代表关闭")
	private Integer isEnable;//是否开放
	@ApiModelProperty("责任部门")
	private String dept;//负责部门
	@JsonIgnore
	private Date createTime;//创建时间
	@ApiModelProperty("数据标记： -1不可见/无效数据 0 默认(表示可见/有效数据) 1待指定(未来可能需要的状态指定)")
	private Integer remove;//删除标记
}
