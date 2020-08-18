package com.dtinone.datashare.entity;

import com.cdjiamigu.datasource.common.meta.entitys.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("tableVO")
public class TableVO extends Table {

    @ApiModelProperty("字段数")
    private Integer columnCount;
}
