package com.dtinone.datashare.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dtinone.datashare.entity.Catalog;
import com.dtinone.datashare.entity.DictType;
import com.dtinone.datashare.mapper.DictTypeMapper;
import com.dtinone.datashare.service.IDictType;
import com.dtinone.datashare.util.RD;
import com.dtinone.datashare.util.Utils;

@Service
@Slf4j
public class DictTypeImpl implements IDictType{
	
	@Resource
	private DictTypeMapper mapper;

	@Override
	public RD<?> addItem(DictType param) {
		try {
			return RD.isOk().setData(mapper.addItem(param));
		} catch (Exception e) {
			return RD.isFail().setMsg(e.getMessage());
		}
	}

	@Override
	public RD<?> delItem(List<Integer> idKeys) {
		try {
			return RD.isOk().setData(mapper.delItem(idKeys));
		} catch (Exception e) {
			return RD.isFail().setMsg(e.getMessage());
		}
	}

	@Override
	public RD<?> changeRemove(List<Integer> idKeys, boolean isOpen) {
		try {
			return RD.isOk().setData(mapper.changeRemove(idKeys, false));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
		
	}

	@Override
	public RD<?> updateItem(DictType param) {
		try {
			return RD.isOk().setData(mapper.updateItem(param));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> queryInfo(DictType condition) {
		try {
			return RD.isOk().setData(mapper.queryInfo(condition));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}
	
	@Override
	public RD<?> queryInfoByName(DictType condition) {
		try {
			Integer _parentId = null;
			if("目录分类级别".equals(condition.getName())){
				_parentId = condition.getParentId();
			}
			condition.setParentId(null);
			List<DictType> queryInfo = mapper.queryInfo(condition);
			//获得子项
			List<DictType> sunList = mapper.queryInfoByName(queryInfo);
			if(_parentId == null){
				sunList.removeIf(o -> o.getName().equals("同级"));
			}
			return RD.isOk().setData(sunList);
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> queryCatalogTree(DictType condition) {
		try {
			List<DictType> dictTypes = mapper.queryInfo(new DictType());
			List<Catalog> catalogs = new ArrayList<>();
			for (DictType dictType : dictTypes) {
				Catalog catalog = new Catalog();
				catalog.setName(dictType.getName());
				catalog.setIdKey(dictType.getIdKey());
				catalog.setParentId(dictType.getParentId());
				catalogs.add(catalog);
			}
			List<Catalog> buildByRecursive = Utils.buildByRecursive(catalogs,condition.getName());
			
			return RD.isOk().setData(buildByRecursive);
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

}
