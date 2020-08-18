package com.dtinone.datashare.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dtinone.datashare.entity.InformationTitle;

@Mapper
public interface InformationTitleMapper {

	/**
	 * 新增 信息资源-标题
	 * @param param
	 * @return
	 */
	int addItem(InformationTitle param);
	
	/**
	 * 删除 信息资源-标题
	 * @param idKeys
	 * @return 操作成功的数目
	 * 真删
	 * @提示:使用该方法时,应当先查询list-data-content再做删除,否则list-data-content将形成冗余数据
	 */
	int delItem(@Param("idKeys") List<Integer> idKeys);
	
	/**
	 * 删除 信息资源-标题
	 * @param idKeys
	 * @param isOpen true 表示开启remove 反之关闭
	 * @return 操作成功的数目
	 * 假删
	 * @提示:使用该方法时,应当先查询list-data-content再做删除,否则list-data-content将形成冗余数据
	 * 改变remove状态 0：可见 -1：不可见
	 */
	int changeRemove(@Param("idKeys") List<Integer> idKeys,@Param("isOpen") boolean isOpen);

	/**
	 * 通过模板id删除title
	 * @param idKeys
	 * @return
	 */
	int changeRemoveByDemoModel(@Param("idKeys") List<Integer> idKeys);

	/**
	 * 修改 信息资源-标题-相关属性
	 * @param param
	 * @return 数据改变的数量
	 */
	int updateItem(InformationTitle param);
	
	/**
	 *查询-标题
	 * @param condition
	 * @return 数据集合
	 */
	List<InformationTitle> queryInfo(InformationTitle condition);
}
