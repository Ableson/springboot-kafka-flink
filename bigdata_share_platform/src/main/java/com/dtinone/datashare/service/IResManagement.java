package com.dtinone.datashare.service;

import com.dtinone.datashare.entity.ResManagement;
import com.dtinone.datashare.util.RD;
/**
 * 资源管理
 */
public interface IResManagement {

    RD<?> addOrUpdItem(ResManagement data);

    RD<?> queryInfo(ResManagement data);

    RD<?> updateItem(ResManagement data);

    RD<?> delItem(ResManagement data);

}
