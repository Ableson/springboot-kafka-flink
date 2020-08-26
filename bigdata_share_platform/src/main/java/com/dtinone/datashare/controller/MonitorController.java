package com.dtinone.datashare.controller;

import com.cdjiamigu.common.system.SystemMonitor;
import com.cdjiamigu.common.system.model.SM;
import com.cdjiamigu.datasource.common.constant.ConstantCom;
import com.dtinone.datashare.scheduled.CatalogSystemMonitor;
import com.dtinone.datashare.util.RD;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dtinone.datashare.scheduled.MonitorUtils.changeSmToBean;
import static com.dtinone.datashare.scheduled.SmTask.catalogSystemMonitorTaskQueue;

@RestController
@RequestMapping(value = "/catalog-monitor")
@Api(value = "/monitor", tags = "监控管理")
public class MonitorController {

//    @ApiOperation(value = "查询监控列表")
//    @PostMapping(value = "/getMonitorList")
//    public ResponseObj<LimitQueue<SipSystemMonitor>> getMonitorList() {
//        ResponseObj<LimitQueue<SipSystemMonitor>> responseObj = new ResponseObj<>();
//        try {
//            responseObj.setData(SystemMonitorTask.SystemMonitorTaskQueue);
//        } catch (Exception e) {
//            responseObj.error(e.getMessage());
//        }
//        return responseObj;
//    }
//
//    @ApiOperation(value = "查询监控实时详情")
//    @PostMapping(value = "/getMonitorInfo")
//    public ResponseObj<SipSystemMonitor> getMonitorInfo() {
//        ResponseObj<SipSystemMonitor> responseObj = new ResponseObj<>();
//        try {
//            LimitQueue<SipSystemMonitor> systemMonitorTaskQueue = SystemMonitorTask.SystemMonitorTaskQueue;
//            for (SipSystemMonitor sipSystemMonitor : systemMonitorTaskQueue) {
//                responseObj.setData(sipSystemMonitor);
//                break;
//            }
//        } catch (Exception e) {
//            responseObj.error(e.getMessage());
//        }
//        return responseObj;
//    }

    @ApiOperation(value = "查询系统监控")
    @PostMapping(value = "/getSysData")
    public RD<?> getSysData() {
        return RD.isOk().setData(catalogSystemMonitorTaskQueue);
    }

    @ApiOperation(value = "查询系统实时信息")
    @PostMapping(value = "/getSysInfo")
    public RD<?> getSysInfo(){
        SM sm = SystemMonitor.getSm();
        CatalogSystemMonitor catalogSystemMonitor = changeSmToBean(sm);
        return RD.isOk().setData(catalogSystemMonitor);
    }
    @ApiOperation(value = "查询当前服务状态")
    @PostMapping(value = "/getServiceStatus")
    public RD<?> getServiceStatus() {
        return RD.isOk().setData(ConstantCom.TRUE);
    }
}
