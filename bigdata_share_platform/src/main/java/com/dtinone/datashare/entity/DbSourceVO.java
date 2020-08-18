package com.dtinone.datashare.entity;

import com.cdjiamigu.datasource.feign.common.entity.basic.DbSource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("数据源实体vo")
public class DbSourceVO extends DbSource {
    @ApiModelProperty("数据表数")
    private Integer tableCount;
}
