package com.dtinone.datashare.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.dtinone.datashare.entity.*;
import com.dtinone.datashare.mapper.InformationContentsMapper;
import com.mchange.lang.IntegerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dtinone.datashare.entity.InformationContentExample.Criteria;
import com.dtinone.datashare.mapper.CatalogMapper;
import com.dtinone.datashare.mapper.InformationContentMapper;
import com.dtinone.datashare.service.ICatalog;
import com.dtinone.datashare.util.NodeInfo;
import com.dtinone.datashare.util.RD;
import com.dtinone.datashare.util.Utils;

import cn.hutool.json.JSONObject;

@Service
@Slf4j
public class CatalogImpl implements ICatalog{
	
	@Resource
	private CatalogMapper mapper;
	
	@Resource
	private InformationContentMapper informationMapper;
	@Resource
	private InformationContentsMapper contentsMapper;

	@Override
	public RD<?> addItem(Catalog param) {
		try {
			if (param.getParentId()==null && mapper.queryRoot().size()>0) return RD.isOk().setMsg("已存在根目录,请不要重复添加根目录");
			//验证同级名字是否相同
			Catalog catalog = new Catalog();
			catalog.setName(param.getName());
			catalog.setParentId(param.getParentId());
			List<Catalog> catalogs = mapper.queryInfo(catalog);
			if(catalogs.size()>0) return RD.isOk().setMsg("已存在同名目录");
			return RD.isOk().setData(mapper.addItem(param));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> delItem(List<Integer> idKeys) {
		try {
			return RD.isOk().setData(mapper.delItem(idKeys));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> changeRemove(List<Integer> idKeys, boolean isOpen) {
		try {
			//判断目录下是否存在数据
			//功能:需要查询的目录下是否有值-勾选的目录下会获得重复的id 去不去重不太重要
			List<Integer> allCatalogId = new ArrayList<>();
			//获得所有的目录
			List<Catalog> catalogs = mapper.queryInfo(new Catalog());
			for (Integer catalogId : idKeys) {
				List<Catalog> catalogList = Utils.treeCatalogList(catalogs, catalogId);
				List<Integer> collect = catalogList.stream().map(Catalog::getIdKey).collect(Collectors.toList());
				allCatalogId.addAll(collect);
			}
			InformationContentExample example = new InformationContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andRemoveEqualTo(0);
			criteria.andCatagoryCodeIn(allCatalogId);
			List<InformationContent> checkExists = informationMapper.selectByExample(example);
			if (checkExists.size() > 0) {
				return RD.isOk().setStatus(false).setMsg("该目录下存在数据,操作失败");
			}
			List<InformationContents> checkExistsRelation = contentsMapper.queryInfoByCategoryRelationCode(allCatalogId);
			if(checkExistsRelation.size()>0) return RD.isOk().setStatus(false).setMsg("该目录下存在关联数据,操作失败");
			return RD.isOk().setData(mapper.changeRemove(idKeys, isOpen));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
		
	}

	@Override
	public RD<?> updateItem(Catalog param) {
		try {
			if(param.getParentId() == null && mapper.queryRoot().size() > 0){
				return RD.isOk().setMsg("已存在根目录,请不要重复添加根目录");
			}
			//验证同级名字是否相同
			Catalog catalog = new Catalog();
			catalog.setName(param.getName());
			catalog.setParentId(param.getParentId());
			List<Catalog> catalogs = mapper.queryInfo(catalog);
			if(catalogs.size()>0) return RD.isOk().setMsg("已存在同名目录");
			return RD.isOk().setData(mapper.updateItem(param));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> queryInfo(Catalog condition) {
		
		try {
			return RD.isOk().setData(mapper.queryInfo(condition));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}
	
	@Override
	public RD<?> queryInfoAll() {
		
		try {
			List<Catalog> infoAll = mapper.queryInfoAll();
			//转换格式
			List<NodeInfo> _buildByRecursive = Utils._buildByRecursive(infoAll);
			return RD.isOk().setData(_buildByRecursive);
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

}
