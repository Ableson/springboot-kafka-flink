package com.dtinone.datashare.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dtinone.datashare.entity.InformationTitle;
import com.dtinone.datashare.util.RD;

public interface IInformationTitle {

	/**
	 * 新增目录
	 * @param param
	 * @return 新增数据的id
	 */
	RD<?> addItem(InformationTitle param);
	
	/**
	 * 删除目录
	 * @param idKey
	 * @return 操作成功的数目
	 * 真删
	 * @提示:使用该方法时,应当先查询其子目录结构再做删除,否则无法删除子目录
	 */
	RD<?> delItem(@Param("idKeys") List<Integer> idKeys);
	
	/**
	 * 删除目录
	 * @param idKeys
	 * @param isOpen true 表示开启remove 反之关闭
	 * @return 操作成功的数目
	 * 假删
	 * @提示:使用该方法时,应当先查询其子目录结构再做修改,否则无法修改子目录
	 * 改变remove状态 0：可见 -1：不可见
	 */
	RD<?> changeRemove(@Param("idKeys") List<Integer> idKeys,@Param("isOpen") boolean isOpen);
	
	/**
	 * 修改目录-相关属性
	 * @param param
	 * @return 数据改变的数量
	 */
	RD<?> updateItem(InformationTitle param);
	
	/**
	 *查询目录 
	 * @param condition
	 * @return 数据集合
	 */
	RD<?> queryInfo(InformationTitle condition);
}
