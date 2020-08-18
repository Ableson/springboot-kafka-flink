package com.dtinone.datashare.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dtinone.datashare.entity.InformationContents;
import com.dtinone.datashare.util.RD;

public interface IInformationContents {

    /**
     * 新增目录
     *
     * @param param
     * @return 新增数据的id
     */
    RD<?> addItem(InformationContents param);
    
    /**
     * 新增目录
     *
     * @param param
     * @return 新增数据的id
     */
    RD<?> addItemJson(Map<String,Object> param);

    /**
     * 删除目录
     *
     * @param idKeys
     * @return 操作成功的数目
     * 真删
     * @提示:使用该方法时,应当先查询其子目录结构再做删除,否则无法删除子目录
     */
    RD<?> delItem(@Param("idKeys") List<String> idKeys);

    /**
     * 删除目录
     *
     * @param idKeys
     * @param isOpen true 表示开启remove 反之关闭
     * @return 操作成功的数目
     * 假删
     * @提示:使用该方法时,应当先查询其子目录结构再做修改,否则无法修改子目录 改变remove状态 0：可见 -1：不可见
     */
    RD<?> changeRemove(@Param("idKeys") List<String> idKeys, @Param("isOpen") boolean isOpen);

    /**
     * 修改目录-相关属性
     *
     * @param param
     * @return 数据改变的数量
     */
    RD<?> updateItem(InformationContents param);

    /**
     * 批量修改 信息资源-相关属性
     * @param idKeys
     * @param status
     * @return 数据改变的数量
     */
    RD<?> updateBatchStatus(List<String> idKeys, Integer status, String remark);
    RD<?> updateShengHe(List<String> idKeys, Integer status, String remark);
    RD<?> updateXiaJia(List<String> idKeys, Integer status, String remark);
    RD<?> updateFaBu(List<String> idKeys, Integer status, String networkId, String remark);

    /**
     * 
     * @param condition
     * @param pageNo
     * @param pageSize
     * @return
     */
    RD<?> queryInfo(InformationContents condition, Integer pageNo, Integer pageSize);
}
