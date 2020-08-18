package com.dtinone.datashare.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.dtinone.datashare.entity.Catalog;
import com.dtinone.datashare.service.impl.CatalogImpl;
import com.dtinone.datashare.util.RD;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/catalog")
@Slf4j
@Api(tags="目录接口", value = "/catalog")
public class CatalogController {

	@Resource
	private CatalogImpl catalogService;
	
	@PostMapping("/add")
	@ApiOperation(value = "目录编制录入时,左侧菜单的新增")
	public RD<?> addItem (Catalog param) {

		return catalogService.addItem(param);
	}
	
	@PostMapping("/del")
	@ApiOperation(value = "目录编制录入时,左侧菜单的删除")
	public RD<?> delItem (@RequestParam("idKeys") List<Integer> idKeys) {
		
		return catalogService.changeRemove(idKeys, false);
	}
	
	@PostMapping("/upd")
	@ApiOperation(value = "目录编制录入时,左侧菜单的修改")
	public RD<?> updateItem (Catalog param) {
		
		return catalogService.updateItem(param);
	}
	
	@PostMapping("/query")
	@ApiOperation(value = "目录编制录入时,左侧菜单的查询")
	public RD<?> queryInfo (Catalog condition) {

		return catalogService.queryInfo(condition);
	}
	
	@PostMapping("/queryAll")
	@ApiOperation(value = "目录编制录入时,左侧菜单的查询-特殊处理结构")
	public RD<?> queryInfoAll () {

		return catalogService.queryInfoAll();
	}
	
}
