package com.dtinone.datashare.controller;

import com.cdjiamigu.datasource.common.meta.entitys.Column;
import com.cdjiamigu.datasource.common.meta.entitys.Database;
import com.cdjiamigu.datasource.common.meta.entitys.Table;
import com.cdjiamigu.datasource.feign.common.entity.basic.DbSource;
import com.cdjiamigu.datasource.feign.common.entity.basic.DbSourceConnRecord;
import com.cdjiamigu.datasource.feign.common.obj.ResponseObj;
import com.cdjiamigu.datasource.feign.common.utils.ResultDataUtils;
import com.cdjiamigu.datasource.feign.interfaces.DbSourceInterface;
import com.dtinone.datashare.common.enums.DbSourceEnum;
import com.dtinone.datashare.entity.DbSourceVO;
import com.dtinone.datashare.entity.TableVO;
import com.dtinone.datashare.util.ControllerHelper;
import com.dtinone.datashare.util.RD;
import com.dtinone.datashare.util.Utils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data-base-info")
@Slf4j
@Api(tags = "数据源信息接口", value = "/data-base-info")
public class DataSourceController {

	@Autowired
	private DbSourceInterface dbSourceInterface;

	@PostMapping("/add-or-upd")
	@ApiOperation(value = "数据源信息新增/修改")
	public RD<?> addOrUpdItem (DbSource dbSource) {
		try {
			//oracle 需要将表空间名字赋值到dbName
			String sourceType = dbSource.getSourceType();
			String driverName = ControllerHelper.getDriverNameForDbType(sourceType);
			dbSource.setModularType(DbSourceEnum.MODEL_CURR.getType());
			dbSource.setDriverName(driverName);
			dbSourceInterface.save(dbSource);
			return RD.isOk().setMsg("写入成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@PostMapping("/query")
	@ApiOperation(value = "数据源查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "systemId", value = "所属系统ID",required = false),
			@ApiImplicitParam(name = "sourceName", value = "数据源名称", required = false),
			@ApiImplicitParam(name = "pageNo", value = "页码", required = true),
			@ApiImplicitParam(name = "pageSize", value = "每页数据量", required = true),
			@ApiImplicitParam(name = "sourceStatus", value = "状态", required = false)
	})
	public RD<?> queryInfo (@RequestParam(value = "systemId",required = false) String systemId,@RequestParam(value = "sourceName", required = false) String sourceName, @RequestParam(value = "sourceStatus",required = false) Integer sourceStatus,
							@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize) {
		try {
			ResponseObj<PageInfo<DbSource>> data = dbSourceInterface.getPageBySystemId(systemId, sourceName, sourceStatus, DbSourceEnum.MODEL_CURR.getType(), pageNo, pageSize);
			PageInfo<DbSource> pageInfo = ResultDataUtils.getData(data);
			List<DbSource> dbList = pageInfo.getList();
			List<DbSourceVO> dataList = new ArrayList<>();
			dbList.stream().forEach(o->{
				ResponseObj<List<Table>> tables = new ResponseObj<>();
				try {
					tables = dbSourceInterface.getTbales(o.getId());
				} catch (Exception e){
					log.error("存在错误得数据源ip:"+o.getSourceIp()+" port:"+o.getSourcePort());
				}
				DbSourceVO obj = new DbSourceVO();
				BeanUtils.copyProperties(o,obj);
				List<Table> tableList = new ArrayList<>();
				try {
					if(tables.getData() != null){
						tableList = ResultDataUtils.getData(tables);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				obj.setTableCount(tableList.size());
				dataList.add(obj);
			});
			PageInfo<DbSourceVO> _pageInfo = new PageInfo<>();
			BeanUtils.copyProperties(pageInfo, _pageInfo);
			_pageInfo.setList(dataList);
			return RD.isOk().setData(_pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@PostMapping("/query-details")
	@ApiOperation(value = "获得数据源详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sourceId", value = "数据源id")
	})
	public RD<?> queryDetails(String sourceId){
		try {
			ResponseObj<DbSource> data = dbSourceInterface.getById(sourceId);
			DbSource contents = ResultDataUtils.getData(data);
			return RD.isOk().setData(contents);
		} catch (Exception e){
			return RD.isFail().setMsg("操作失败");
		}
	}
	@PostMapping("/query-database")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sourceId", value = "数据源类型id")
	})
	@ApiOperation(value = "通过数据源id获得oracle的dbName")
	public RD<?> queryDataBase(@RequestParam(value = "sourceId", required = true) String sourceId){
		try{
			ResponseObj<List<Database>> database = dbSourceInterface.getDatabase(sourceId);

			return RD.isOk().setData(ResultDataUtils.getData(database));
		}catch (Exception e){
			log.error(e.getMessage());
			return RD.isFail().setMsg("查询失败");
		}
	}


	@PostMapping("/query-dbname")
	@ApiOperation(value = "获取数据表dbName(前端分页)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sourceId", value = "数据源类型id"),
			@ApiImplicitParam(name = "tableName", value = "数据源名称", required = false),
			@ApiImplicitParam(name = "tableType", value = "数据表类型", required = false),
			@ApiImplicitParam(name = "pageNo", value = "页码", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页数据量", required = false)
	})
	public RD<?> queryTableForDataBase(@RequestParam("sourceId") String sourceId,@RequestParam(value = "tableName",required = false) String tableName,@RequestParam(value = "tableType",required = false) String tableType,
									   @RequestParam(value = "pageNo", required = false)Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize){
		try {
			//条件是否存在
			if(StringUtils.isNotBlank(tableType)) tableType = tableType.toUpperCase();
			String _tableType = tableType;
			boolean finalTableNameCondition = checkConditionExists(tableName);
			boolean finalTableTypeCondition = checkConditionExists(tableType);
			ResponseObj<List<Table>> tablesResult = new ResponseObj<>();
			try {
				tablesResult = dbSourceInterface.getTbales(sourceId);
			} catch (Exception e){
				log.error("存在错误得数据源id:"+sourceId);
			}
			List<Table> tables = new ArrayList<>();
			if(tablesResult.getData() != null){
				tables = ResultDataUtils.getData(tablesResult);
			}
			List<TableVO> dataList = new ArrayList<>();
			tables.stream().forEach(o -> {
				String tableNameForDB = o.getTableName();
				String tableTypeForDB = o.getTableType();
				if(finalTableNameCondition){
					boolean isPass = tableNameForDB.contains(tableName);
					if(finalTableTypeCondition){
						//两个条件均有
						if(isPass && tableTypeForDB.contains(_tableType)){
							handleTableCounts(o, sourceId, tableNameForDB, dataList);
						}
					} else {
						//单一得tableName条件
						if(isPass){
							handleTableCounts(o, sourceId, tableNameForDB, dataList);
						}
					}
				} else if (finalTableTypeCondition){
					//单一得tableType条件
					if(tableTypeForDB.contains(_tableType)){
						handleTableCounts(o, sourceId,tableTypeForDB,dataList);
					}
				} else {
					//不含任何条件
					handleTableCounts(o, sourceId, tableNameForDB, dataList);
				}
			});
			PageInfo pageInfo = null;
			if(pageNo != null && pageSize != null && dataList.size() > 0){
				pageInfo = Utils.handlePageInfo(dataList, pageNo, pageSize);
			} else {
				pageInfo = new PageInfo(dataList);
			}
			return RD.isOk().setData(pageInfo);
//			return RD.isOk().setData(dataList);
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("查询失败");
		}
	}


	@PostMapping("/query-dbcolumn")
	@ApiOperation(value = "获得数据表字段columns(前端分页)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sourceId", value = "数据源ID", type = "String"),
			@ApiImplicitParam(name = "tableName", value = "数据表名", type = "String"),
			@ApiImplicitParam(name = "pageNo", value = "页码", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页数据量", required = false)
	})
	public RD<?> queryTableForColumn(@RequestParam("sourceId") String sourceId, @RequestParam("tableName") String tableName,
									 @RequestParam(value = "pageNo", required = false)Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize){
		try {
			ResponseObj<List<Column>> columnsResult = dbSourceInterface.getColumns(sourceId, tableName);

			List<Column> columns = new ArrayList<>();
			if(columnsResult.getData() != null){
				columns = ResultDataUtils.getData(columnsResult);
			}
			PageInfo pageInfo = new PageInfo(columns);
			if(columns.size() > 0){
				pageInfo = Utils.handlePageInfo(columns, pageNo, pageSize);
			}
			return RD.isOk().setData(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RD.isFail().setMsg("查询失败");
		}
	}

	@PostMapping("/query-dbtype")
	@ApiOperation(value = "获得数据源类型")
	public RD<?> queryDbType(){
		try {
			return RD.isOk().setData(dbSourceInterface.getSourceTypeList());
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("查询失败");
		}
	}

	@PostMapping("/check-connection")
	@ApiOperation(value = "数据源连通性测试-并写入测试记录")
	public RD<?> checkConnection(String id){
		ResponseObj<Boolean> connection = dbSourceInterface.testConnection(id);
		if (connection.getData()){
			log.info("测试连接成功");
			return RD.isOk().setMsg("连接成功");
		} else {
			return RD.isFail().setMsg("连接失败");
		}
	}

	@PostMapping("/is-connection")
	@ApiOperation(value = "数据源连通性测试")
	public RD<?> isConnection(DbSource dbSource){
		String sourceType = dbSource.getSourceType();
		String driverName = ControllerHelper.getDriverNameForDbType(sourceType);
		dbSource.setModularType(DbSourceEnum.MODEL_CURR.getType());
		dbSource.setDriverName(driverName);
		ResponseObj<Boolean> connection = dbSourceInterface.isConnection(dbSource);
		if(connection.getData()){
			log.info("测试连接成功");
			return RD.isOk().setMsg("连接成功");
		}else{
			return RD.isFail().setMsg("连接失败");
		}
	}
	@ApiOperation("查询连通信测试记录")
	@PostMapping("/query-connction-record")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNo", value = "页码"),
			@ApiImplicitParam(name = "pageSize", value = "每页数据量"),
			@ApiImplicitParam(name = "sourceId", value = "数据源id"),
			@ApiImplicitParam(name = "status", value = "1：成功 0：失败", required = false),
	})
	public RD<?> queryConnectionRecord(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,
									   @RequestParam("sourceId") String sourceId,@RequestParam(value = "status", required = false) String status){
		//调用查询记录得方法
		ResponseObj<PageInfo<DbSourceConnRecord>> dbSourceConnRecordPage = dbSourceInterface.getDbSourceConnRecordPage(pageNo, pageSize, sourceId, status);
		PageInfo<DbSourceConnRecord> data = null;
		try {
			data = ResultDataUtils.getData(dbSourceConnRecordPage);
			log.info("查询连接记录成功");
			return RD.isOk().setData(data);
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@PostMapping("/delete-dbsource")
	@ApiOperation("删除数据原")
	public RD<?> deleteDbSource(@RequestParam("idComma") String idComma){
		try{
			dbSourceInterface.delete(idComma);
			log.info("删除数据源成功");
			return RD.isOk();
		} catch (Exception e){
			log.info("删除数据源失败");
			return RD.isFail().setMsg("操作失败");
		}
	}
	@PostMapping("/querySource")
	@ApiOperation("数据源查询-用于从信息系统获取")
	public RD<?> getListBySystemId(@RequestParam("systemId") String systemId,@RequestParam(value = "sourceType",required = false)String sourceType){
		List<DbSource> data = null;
		ResponseObj<List<DbSource>> listBySystemId = dbSourceInterface.getListBySystemId(systemId,null, sourceType, DbSourceEnum.MODEL_CURR.getType());
		try {
			if(listBySystemId.getData() != null){
				data = ResultDataUtils.getData(listBySystemId);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("获取数据源失败");
		}
		return RD.isOk().setData(data);
	}

	//===================辅助方法===============
	/**
	 * 处理table中得字段数量统计
	 * 远端调用中不支持条件查询 故在本方法中处理
	 * 处理页面中需要表现出得每个数据源中表的数量tableCount
	 * @param sourceId 数据源id
	 * @param tableNameForDB 表名
	 * @param dataList 数据结果集
	 */
	private List<TableVO> handleTableCounts(Table o,String sourceId, String tableNameForDB, List<TableVO> dataList){
		TableVO obj = new TableVO();
		try {
			BeanUtils.copyProperties(o, obj);
			ResponseObj<List<Column>> columns = dbSourceInterface.getColumns(sourceId, tableNameForDB);
			List<Column> columnList = ResultDataUtils.getData(columns);
			obj.setColumnCount(columnList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataList.add(obj);
		return dataList;
	}

	/**
	 * 用于判断是否在进行条件查询-byTableName/byTableType
	 * @param condition tableName/tableType 得查询字符
	 * @return flag
	 */
	private boolean checkConditionExists(String condition){
		boolean flag = false;
		if(StringUtils.isNotBlank(condition)){
			flag = true;
		}
		return flag;
	}
}
