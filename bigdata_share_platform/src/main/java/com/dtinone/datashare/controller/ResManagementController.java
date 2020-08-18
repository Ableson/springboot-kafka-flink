package com.dtinone.datashare.controller;

import com.dtinone.datashare.entity.ResManagement;
import com.dtinone.datashare.service.IResManagement;
import com.dtinone.datashare.util.RD;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/resManagement")
@Api(tags = "资源管理", value = "resManagement")
public class ResManagementController {

    @Resource
    private IResManagement resService;

    @ApiOperation(value = "资源管理-新增")
    @PostMapping("/add-or-upd")
    public RD<?> addOrUpdItem(ResManagement data){

        return resService.addOrUpdItem(data);
    }

    @ApiOperation(value = "资源管理-查询")
    @PostMapping("/query")
    public RD<?> queryInfo(ResManagement param){

        return resService.queryInfo(param);
    }

    @ApiOperation(value = "资源管理-删除")
    @PostMapping("/del")
    public RD<?> delItem(ResManagement param){

        return resService.delItem(param);
    }

}
