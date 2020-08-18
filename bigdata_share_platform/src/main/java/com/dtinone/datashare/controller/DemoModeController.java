package com.dtinone.datashare.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtinone.datashare.entity.DemoMode;
import com.dtinone.datashare.entity.InformationTitle;
import com.dtinone.datashare.service.IDemoMode;
import com.dtinone.datashare.util.RD;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/demo-mode")
@Slf4j
@Api(tags = "模板接口",value = "/demo-mode")
public class DemoModeController {

	@Resource
	private IDemoMode demoModeService;
	
	@PostMapping("/add")
	@ApiOperation(value = "模板新增")
	public RD<?> addItem (DemoMode param) {
		
		return demoModeService.addItem(param);
	}
	
	@PostMapping("/del")
	@ApiOperation(value = "模板删除")
	public RD<?> delItem (@RequestParam("idKeys") List<Integer> idKeys) {
		
		return demoModeService.changeRemove(idKeys, false);
	}
	
	@PostMapping("/upd")
	@ApiOperation(value = "模板修改")
	public RD<?> updateItem (DemoMode param) {
		
		return demoModeService.updateItem(param);
	}
	
	@PostMapping("/query")

	@ApiOperation(value = "模板查询")
	public RD<?> queryInfo(DemoMode condition) {

		return demoModeService.queryInfo(condition);
	}
	 
	
	@PostMapping("/queryByName")
	@ApiOperation(value = "模板查询-通过名字获得模板子项")
	public RD<?> queryInfoByName (DemoMode condition) {
		
		return demoModeService.queryInfoByName(condition);
	}
	
	@PostMapping("/queryTitleByDemoName")
	@ApiOperation(value = "通过模板的名字,获得模板的标题-通过名字/通过ID 均可")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "模板的名字")
	})
	public RD<?> queryTitleForDemoName(DemoMode condition) {
		
		return demoModeService.queryTitleForDemoName(condition);
	}
}
