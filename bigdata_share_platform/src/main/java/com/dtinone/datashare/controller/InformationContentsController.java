package com.dtinone.datashare.controller;

import com.dtinone.datashare.entity.InformationContent;
import com.dtinone.datashare.entity.InformationContents;
import com.dtinone.datashare.service.ContentService;
import com.dtinone.datashare.service.IInformationContents;
import com.dtinone.datashare.util.RD;
import com.dtinone.datashare.util.Utils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/information-content")
@Slf4j
@Api(tags = "目录编制内容+信息项写入的操作", value = "/information-content")
public class InformationContentsController {

    @Resource
    private IInformationContents informationService;
    @Autowired
    private ContentService service;


    @PostMapping("/add")
    @ApiOperation(value = "信息资源+信息项数据写入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableContent描述", value = "除了(信息项+提供方一级部门+提供方二级部门+状态+信息资源名称外),其他的内容都存这里(json格式),key采用keyCode值")
    })
    public RD<?> addItem(@RequestBody InformationContents param) {

        return informationService.addItem(param);
    }

    @PostMapping("/add-test")
    @ApiOperation(value = "信息资源+信息项数据写入+信息项功能写入")
    public RD<?> addItemJson(@RequestBody Map<String, Object> generalStr) {

        return informationService.addItemJson(generalStr);
    }

    @PostMapping("/del")
    @ApiOperation(value = "信息系统信息项-删除")
    public RD<?> delItem(@RequestParam("idKeys") List<String> idKeys) {

        return informationService.changeRemove(idKeys, false);
    }

    @PostMapping("/upd")
    @ApiOperation(value = "信息系统信息项-单笔修改")
    public RD<?> updateItem(InformationContents param) {

        return informationService.updateItem(param);
    }

    @ApiOperation(value = "信息系统列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu", value = "信息系统菜单顺序，0.资源管理1.目录注册2.目录审核3.目录发布4.目录下架5.目录查询", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", type = "Integer", required = true),
            @ApiImplicitParam(name = "pageNo", value = "页码", type = "Integer", required = true)
    })
    @PostMapping("/queryAllList")
    public RD queryAllList(InformationContent content, Integer pageNo, Integer pageSize, Integer menu) {

        PageInfo<InformationContent> pageInfo = service.list(content, pageNo, pageSize, menu);
        return RD.isOk().setData(pageInfo);

    }

    @PostMapping("/get-resource-code")
    @ApiOperation(value = "获得资源编码")
    public RD<?> updateResourceCode(){

        return RD.isOk().setData(Utils.getInfoCode());
    }

}
