package com.dtinone.datashare.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("信息内容实体")
public class InformationContent {

    @ApiModelProperty("信息内容id")
    private String idKey;
    @ApiModelProperty("所属目录分类id")
    private Integer catagoryCode;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("是否移除 -1不可见 0 默认 1待指定")
    private Integer remove;
    @ApiModelProperty("数据状态 0-待注册  1-待审核  2-待发布  3-已发布 4-下架")
    private Integer status;
    @ApiModelProperty("表单名字")
    private String tableName;
    @ApiModelProperty("提供者一级部门")
    private String providerFirst;
    @ApiModelProperty("提供者二级部门")
    private String providerSecond;
    @ApiModelProperty("描述-待用 采用了分开存储")
    private String remark;

    @ApiModelProperty("模板id")
    private Integer demoModeId;
    @ApiModelProperty("关联分类id")
    private Integer categoryRelationCode;

    private String shengHeRemark;

    private String xiaJiaRemark;
    @ApiModelProperty("发布时使用,发布到哪个网络位置id")
    private String networkId;

    private String boHuiRemark;
    @ApiModelProperty("除开每个表单都具有的属性,其他均json格式赋值该字段")
    private String tableContent;
    @ApiModelProperty("数据来自tableContent")
    private JSONObject tableContentJson;

    //以下字段用于回传数据使用,数据库不存在该字段
    @ApiModelProperty("信息项数量")
    private Integer infoCount;
    @ApiModelProperty("通过catagoryCode 获得的目录中文名字 例如:A>B>C")
    private String catagoryName;
    @ApiModelProperty("信息项列表的集合")
    private List<ListDataContent> listData;


}