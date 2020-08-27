package com.dtinone.datashare.controller;

import com.cdjiamigu.common.system.SystemMonitor;
import com.cdjiamigu.common.system.model.SM;
import com.cdjiamigu.datasource.common.constant.ConstantCom;
import com.dtinone.datashare.scheduled.CatalogSystemMonitor;
import com.dtinone.datashare.util.RD;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dtinone.datashare.scheduled.MonitorUtils.changeSmToBean;
import static com.dtinone.datashare.scheduled.SmTask.catalogSystemMonitorTaskQueue;

@RestController
@RequestMapping(value = "/catalog-monitor")
@Api(value = "/monitor", tags = "监控管理")
public class MonitorController {

    @ApiOperation(value = "查询系统监控")
    @GetMapping(value = "/getSysData")
    public RD<?> getSysData() {
        return RD.isOk().setData(catalogSystemMonitorTaskQueue);
    }

    @ApiOperation(value = "查询系统实时信息")
    @GetMapping(value = "/getSysInfo")
    public RD<?> getSysInfo(){
        SM sm = SystemMonitor.getSm();
        CatalogSystemMonitor catalogSystemMonitor = changeSmToBean(sm);
        return RD.isOk().setData(catalogSystemMonitor);
    }
    @ApiOperation(value = "查询当前服务状态")
    @GetMapping(value = "/getServiceStatus")
    public RD<?> getServiceStatus() {
        return RD.isOk().setData(ConstantCom.TRUE);
    }
}
