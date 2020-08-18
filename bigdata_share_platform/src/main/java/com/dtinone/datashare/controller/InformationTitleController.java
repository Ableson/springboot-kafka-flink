package com.dtinone.datashare.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtinone.datashare.entity.InformationTitle;
import com.dtinone.datashare.service.IInformationTitle;
import com.dtinone.datashare.util.RD;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/information-title")
@Slf4j
@Api(tags = "模板具有的标题接口", value = "/information-title")
public class InformationTitleController {

	@Resource
	private IInformationTitle informationService;
	
	@PostMapping("/add")
	@ApiOperation(value = "模板标题-新增")
	public RD<?> addItem (InformationTitle param) {
		
		return informationService.addItem(param);
	}
	
	@PostMapping("/del")
	@ApiOperation(value = "模板标题-删除")
	public RD<?> delItem (@RequestParam("idKeys") List<Integer> idKeys) {
		
		return informationService.changeRemove(idKeys, false);
	}
	
	@PostMapping("/upd")
	@ApiOperation(value = "模板标题-修改")
	public RD<?> updateItem (InformationTitle param) {
		
		return informationService.updateItem(param);
	}
	
	@PostMapping("/query")
	@ApiOperation(value = "模板标题-查询")
	public RD<?> queryInfo (@ApiParam(value = "") InformationTitle condition) {
		
		return informationService.queryInfo(condition);
	}
	
}
