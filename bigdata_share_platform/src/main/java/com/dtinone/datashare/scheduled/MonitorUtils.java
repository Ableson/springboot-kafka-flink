package com.dtinone.datashare.scheduled;

import cn.hutool.core.bean.BeanUtil;
import com.cdjiamigu.common.system.model.SM;
import com.dtinone.datashare.util.Utils;

import java.util.Date;

public class MonitorUtils {

    /**
     * 转换实体
     *
     * @param sm
     */
    public static CatalogSystemMonitor changeSmToBean(SM sm) {
        try {
            CatalogSystemMonitor monitor = new CatalogSystemMonitor();
            if (sm != null &&
                    sm.getNet() != null &&
                    sm.getNet().getUacNet() != null &&
                    sm.getNet().getUacNet() != null) {
                BeanUtil.copyProperties(monitor, sm.getCpu());
                BeanUtil.copyProperties(monitor, sm.getDisk());
                BeanUtil.copyProperties(monitor, sm.getMem());
                BeanUtil.copyProperties(monitor, sm.getNet());
                BeanUtil.copyProperties(monitor, sm);
                monitor.setUacInSpeed(sm.getNet().getUacNet().getInSpeed());
                monitor.setUacOutSpeed(sm.getNet().getUacNet().getOutSpeed());
                monitor.setSmTime(new Date(sm.getSmTime()));
                monitor.setGuid(Utils.getUID());
            }
            return monitor;
        } catch (Exception e) {
            return null;
        }
    }


    public static Integer getNum(Integer num) {
        if (num == null) {
            return 0;
        } else {
            return num;
        }

    }

}
