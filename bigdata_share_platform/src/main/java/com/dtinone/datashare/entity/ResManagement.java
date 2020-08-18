package com.dtinone.datashare.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("资源管理")
public class ResManagement {
    @ApiModelProperty("主键id")
    private String idKey;

    @ApiModelProperty("系统信息名称")
    private String sysName;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司id")
    private String companyId;

    @ApiModelProperty("用途/建设性质")
    private String useType;

    @ApiModelProperty("所在网络")
    private String networkName;

    @ApiModelProperty("访问地址")
    private String url;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("数据标记： -1不可见/无效数据 0 默认(表示可见/有效数据) 1待指定(未来可能需要的状态指定)")
    private Integer remove;

    @ApiModelProperty("系统类型")
    private String systemType;

    @ApiModelProperty("系统维护公司")
    private String systemDefendCompany;

    @ApiModelProperty("系统维护人")
    private String systemDefendPeople;

    @ApiModelProperty("系统维护人电话号")
    private String defendPhone;

    @ApiModelProperty("系统简介")
    private String systemSummary;

    private Integer pageNo;
    private Integer pageSize;

}