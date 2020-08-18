package com.dtinone.datashare.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtinone.datashare.entity.ListDataContent;
import com.dtinone.datashare.service.IListDataContent;
import com.dtinone.datashare.util.RD;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/list-data")
@Slf4j
//@Api(tags = "信息项接口", value = "/list-data")
public class ListDataController {

	@Resource
	private IListDataContent listDataService;
	
	@PostMapping("/add")
	//@ApiOperation(value = "信息项的新增")
	public RD<?> addItem (ListDataContent param) {
		
		return listDataService.addItem(param);
	}
	
	@PostMapping("/del")
	//@ApiOperation(value = "信息项表的删除")
	public RD<?> delItem (@RequestParam("idKeys") List<Integer> idKeys) {
		
		return listDataService.changeRemove(idKeys, false);
	}
	
	@PostMapping("/upd")
	//@ApiOperation(value = "信息项的修改")
	public RD<?> updateItem (ListDataContent param) {
		
		return listDataService.updateItem(param);
	}
	
	@PostMapping("/query")
	//@ApiOperation(value = "信息项的查询")
	public RD<?> queryInfo (ListDataContent condition) {
		
		return listDataService.queryInfo(condition);
	}
}
