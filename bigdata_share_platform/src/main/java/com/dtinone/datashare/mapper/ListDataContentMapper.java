package com.dtinone.datashare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtinone.datashare.entity.ListDataContent;

@Mapper
public interface ListDataContentMapper {

	/**
	 * 新增 信息资源
	 * @param param
	 * @return
	 * @note idKey 为uuid
	 */
	int addItem(ListDataContent param);
	
	/**
	 * 批量新增 信息资源
	 * @param param
	 * @return
	 * @note idKey 为uuid
	 * 待修正好再使用吧
	 */
	int addItemBatch(@Param("param") List<ListDataContent> param);
	
	/**
	 * 删除 信息资源
	 * @param idKeys
	 * @return 操作成功的数目
	 * 真删
	 */
	int delItem(@Param("idKeys") List<Integer> idKeys);

	int delItemForParentId(@Param("parentId") String parentId);
	
	/**
	 * 删除 细腻资源
	 * @param idKeys
	 * @param isOpen true 表示开启remove 反之关闭
	 * @return 操作成功的数目
	 * 假删
	 * 改变remove状态 0：可见 -1：不可见
	 */
	int changeRemove(@Param("idKeys") List<Integer> idKeys,@Param("isOpen") boolean isOpen);
	
	/**
	 * 修改目录-相关属性
	 * @param param
	 * @return 数据改变的数量
	 */
	int updateItem(ListDataContent param);
	
	/**
	 *查询目录 
	 * @param condition
	 * @return 数据集合
	 */
	List<ListDataContent> queryInfo(ListDataContent condition);
	
	List<ListDataContent> queryInfoForParentId(@Param("parentIds") List<String> parentIds);
}
