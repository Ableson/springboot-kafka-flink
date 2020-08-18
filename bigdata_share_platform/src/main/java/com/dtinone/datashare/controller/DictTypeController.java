package com.dtinone.datashare.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dtinone.datashare.common.enums.StatusEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtinone.datashare.entity.DictType;
import com.dtinone.datashare.service.IDictType;
import com.dtinone.datashare.util.RD;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dict")
@Slf4j
@Api(tags = "字典表接口", value = "/dict")
public class DictTypeController {

	@Resource
	private IDictType dictService;
	
	@PostMapping("/add")
	@ApiOperation(value = "字典新增|下拉选内容新增")
	public RD<?> addItem (DictType param) {
		
		return dictService.addItem(param);
	}
	
	@PostMapping("/del")
	@ApiOperation(value = "字典删除|下拉选内容删除")
	public RD<?> delItem (@RequestParam("idKeys") List<Integer> idKeys) {
		
		return dictService.changeRemove(idKeys, false);
	}
	
	@PostMapping("/upd")
	@ApiOperation(value = "字典修改|下拉选内容修改")
	public RD<?> updateItem (DictType param) {
		
		return dictService.updateItem(param);
	}
	
	@PostMapping("/query")
	@ApiOperation(value = "字典查询|下拉选内容获得,具体所属类目详情参阅参数描述")
	public RD<?> queryInfo (DictType condition) {
		
		return dictService.queryInfo(condition);
	}
	
	@PostMapping("/queryByName")
	@ApiOperation(value = "字典查询|下拉选内容获得,具体所属类目详情参阅参数描述,通过名字查询")
	public RD<?> queryInfoByName (DictType condition) {
		
		return dictService.queryInfoByName(condition);
	}

	@PostMapping("/getStatusList")
	@ApiOperation(value = "查询所有的状态")
	public RD<?> getStatusList() {
		List<Map<String, Object>> list = new ArrayList<>();
		for (StatusEnum statusEnum : StatusEnum.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", statusEnum.getValue());
			map.put("code", statusEnum.getValue());
			map.put("name", statusEnum.getChnName());
			list.add(map);
		}
		return RD.isOk().setData(list);
	}
	
	@PostMapping("/queryByTree")
	@ApiOperation(value = "获得联动菜单数据")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "字典的类目名字")
	})
	public RD<?> queryCatalogTree (DictType condition) {
		
		return dictService.queryCatalogTree(condition);
	}
}
