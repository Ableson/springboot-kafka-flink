package com.dtinone.datashare.util;

import com.alibaba.fastjson.JSON;
import com.cdjiamigu.datasource.common.meta.entitys.Column;
import com.dtinone.datashare.entity.Columns;

import java.util.ArrayList;
import java.util.List;

public class EntityOperation {
    /**
     * 将column 转换为 前端需要的属性  多余的类 Columns + EntityOperation
     * @param param
     * @return
     */
    public static Object operation(List<Column> param){
        List<Columns> list = new ArrayList<>();
        param.stream().forEach(o ->{
            Columns columns = new Columns();
            columns.setTableComment(o.getTableComment());
            columns.setColumnName(o.getColumnName());
            columns.setColumnTypeName(o.getColumnTypeName());
            columns.setColumnLength(o.getColumnLength());
            columns.setColumnScale(o.getColumnScale());
            list.add(columns);
        });
        return list;
    }
}
