package com.dtinone.datashare.controller;

import com.dtinone.datashare.entity.InformationContents;
import com.dtinone.datashare.service.IInformationContents;
import com.dtinone.datashare.util.RD;
import com.dtinone.datashare.util.Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/information-update")
@Slf4j
@Api(tags = "信息资源操作", value = "/information-update")
public class InformationUpdateController {

    @Resource
    private IInformationContents informationService;

    @PostMapping("/del")
    @ApiOperation(value = "信息资源-删除")
    public RD<?> delItem(@RequestParam("idKeys") List<String> idKeys) {

        return informationService.changeRemove(idKeys, false);
    }

    @PostMapping("/upd")
    @ApiOperation(value = "信息资源-修改")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "listData", value = "信息项的修改数据")
    })
    public RD<?> updateItem(@RequestBody InformationContents param) {

        return informationService.updateItem(param);
    }

    @PostMapping("/upd-batch")
    @ApiOperation(value = "信息资源-批量修改各种状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idKeys", value = "需要操作的列表idKey"),
            @ApiImplicitParam(name = "status", value = "状态")
    })
    public RD<?> updateBatchStatus(@RequestParam("idKeys") String idKeys, @RequestParam("status") Integer status, @RequestParam(value = "remark", required = false,defaultValue = "") String remark) {

        List<String> _idKeys = Arrays.asList(idKeys.split(","));
        return informationService.updateBatchStatus(_idKeys, status, remark);
    }

    @PostMapping("/query")
    @ApiOperation(value = "信息资源-查询-支持模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", type = "Integer")
    })
    public RD<?> queryList(InformationContents condition,
                           @RequestParam(value = "pageNo") Integer pageNo,
                           @RequestParam(value = "pageSize") Integer pageSize) {

        return informationService.queryInfo(condition, pageNo, pageSize);
    }

    /**
     * 以下为垃圾代码 多余的东西
     * @return
     */
    @PostMapping("/upd-status-shenghe")
    @ApiOperation(value = "信息资源-批量修改各种状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idKeys", value = "需要操作的列表idKey"),
            @ApiImplicitParam(name = "status", value = "状态"),
            @ApiImplicitParam(name = "remark", value = "备注")
    })
    public RD<?> updateShenHe(@RequestParam("idKeys") String idKeys, @RequestParam("status") Integer status, @RequestParam("remark") String remark){

        List<String> _idKeys = Arrays.asList(idKeys.split(","));
        return informationService.updateShengHe(_idKeys, status, remark);
    }

    @PostMapping("/upd-status-xiajia")
    @ApiOperation(value = "信息资源-批量修改各种状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idKeys", value = "需要操作的列表idKey"),
            @ApiImplicitParam(name = "status", value = "状态"),
            @ApiImplicitParam(name = "remark", value = "描述")
    })
    public RD<?> updateXiaJia(@RequestParam("idKeys") String idKeys, @RequestParam("status") Integer status, @RequestParam("remark") String remark){

        List<String> _idKeys = Arrays.asList(idKeys.split(","));
        return informationService.updateXiaJia(_idKeys, status, remark);
    }

    @PostMapping("/upd-status-fabu")
    @ApiOperation(value = "信息资源-批量修改各种状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idKeys", value = "需要操作的列表idKey"),
            @ApiImplicitParam(name = "status", value = "状态"),
            @ApiImplicitParam(name = "networkNameId", value = "发布位置的id"),
            @ApiImplicitParam(name = "remark", value = "发布时驳回的描述")
    })
    public RD<?> updateFaBu(@RequestParam("idKeys") String idKeys,
                            @RequestParam("status") Integer status,
                            @RequestParam(value = "networkNameId", required = false) String networkNameId,
                            @RequestParam(value = "remark", required = false) String remark){

        List<String> _idKeys = Arrays.asList(idKeys.split(","));
        return informationService.updateFaBu(_idKeys, status, networkNameId, remark);
    }
    
}
