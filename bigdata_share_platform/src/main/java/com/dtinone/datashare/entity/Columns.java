package com.dtinone.datashare.entity;

import lombok.Data;

@Data
public class Columns {
    private String tableComment;
    private String columnName;
    private String columnTypeName;
    private Long columnLength;
    private Long columnScale;
}
