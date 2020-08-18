package com.dtinone.datashare.mapper;

import java.util.List;

import com.dtinone.datashare.entity.Catalog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtinone.datashare.entity.InformationContents;

@Mapper
public interface InformationContentsMapper {

	/**
	 * 新增 信息资源
	 * @param param
	 * @return
	 */
	int addItem(InformationContents param);
	
	/**
	 * 删除 信息资源
	 * @param idKeys
	 * @return 操作成功的数目
	 * 真删
	 * @提示:使用该方法时,应当先查询list-data-content再做删除,否则list-data-content将形成冗余数据
	 */
	int delItem(@Param("idKeys") List<String> idKeys);
	
	/**
	 * 删除 细腻资源
	 * @param idKeys
	 * @param isOpen true 表示开启remove 反之关闭
	 * @return 操作成功的数目
	 * 假删
	 * @提示:使用该方法时,应当先查询list-data-content再做删除,否则list-data-content将形成冗余数据
	 * 改变remove状态 0：可见 -1：不可见
	 */
	int changeRemove(@Param("idKeys") List<String> idKeys,@Param("isOpen") boolean isOpen);
	
	/**
	 * 修改 信息资源-相关属性
	 * @param param
	 * @return 数据改变的数量
	 */
	int updateItem(InformationContents param);

	/**
	 * 批量修改 信息资源-相关属性
	 * @param idKeys
	 * @param status
	 * @return 数据改变的数量
	 * @note 可优化部分
	 */
	int updateBatchStatus(@Param("idKeys") List<String> idKeys,@Param("status") Integer status, @Param("remark") String remark);
	int updateShengHe(@Param("idKeys") List<String> idKeys,@Param("status") Integer status, @Param("remark") String remark);
	int updateXiaJia(@Param("idKeys") List<String> idKeys,@Param("status") Integer status, @Param("remark") String remark);
	int updateFaBu(@Param("idKeys") List<String> idKeys,@Param("status") Integer status, @Param("networkId") String networkId, @Param("remark") String remark);


	List<InformationContents> queryInfo(@Param("condition") InformationContents condition, @Param("ids") List<Integer> ids);

	List<InformationContents> queryInfoById(@Param("condition") InformationContents condition);
	List<InformationContents> queryInfoByCategoryRelationCode(@Param("condition") List<Integer> ids);

	List<InformationContents> queryList(@Param("ids") List<Integer> ids, @Param("tableName") String tableName,
										@Param("providerFirst") String providerFirst, @Param("providerSecond") String providerSecond,
										@Param("status") Integer status);
}
