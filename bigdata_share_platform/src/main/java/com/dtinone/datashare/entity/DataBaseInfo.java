package com.dtinone.datashare.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("数据源信息实体")
public class DataBaseInfo {

	private Integer idKey;
	@ApiModelProperty("数据源名字")
	private String dataSourceName;//数据源名字
	@ApiModelProperty("数据源类型")
	private String dataSourceType;//数据源类型
	@ApiModelProperty("版本号")
	private String versionNo;//版本号
	@ApiModelProperty("IP地址")
	private String ipAddress;//ip地址
	@ApiModelProperty("端口号")
	private String portNo;//端口号
	@ApiModelProperty("数据库名字")
	private String tableName;//数据库名
	private String userName;
	private String pwd;
	private String remark;
	private Date createTime;
	@ApiModelProperty("状态 -1不可见 0 默认 1待指定")
	private Integer remove;
	@ApiModelProperty("sid服务名称")
	private String sidName;
	@ApiModelProperty("所属部门")
	private String dept;
	@ApiModelProperty("所属系统")
	private String ownerSystem;
}
