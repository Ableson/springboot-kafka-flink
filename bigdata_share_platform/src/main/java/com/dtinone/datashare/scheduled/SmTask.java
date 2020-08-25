package com.dtinone.datashare.scheduled;

import com.cdjiamigu.common.system.SystemMonitor;
import com.cdjiamigu.common.system.model.SM;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.dtinone.datashare.scheduled.MonitorUtils.changeSmToBean;

@Component
public class SmTask {

    public static LimitQueue<CatalogSystemMonitor> catalogSystemMonitorTaskQueue = new LimitQueue<>(5);

    //3.添加定时任务
    @Scheduled(fixedRate = 4990)
    private void configureTasks(){
        SM sm = SystemMonitor.getSm();
        CatalogSystemMonitor catalogSystemMonitor = changeSmToBean(sm);
        catalogSystemMonitorTaskQueue.offer(catalogSystemMonitor);
    }

}
