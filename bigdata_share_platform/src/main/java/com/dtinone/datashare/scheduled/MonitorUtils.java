package com.dtinone.datashare.scheduled;

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
        CatalogSystemMonitor catalogSystemMonitor = new CatalogSystemMonitor() {{
            setGuid(Utils.getUID());
            setDeviceId(sm.getDeviceId());
            setTotalDiskSpaceGb(sm.getDisk().getTotalDiskSpaceGb());
            setFreeDiskSpaceGb(sm.getDisk().getFreeDiskSpaceGb());
            setUsedDiskSpaceGb(sm.getDisk().getUsedDiskSpaceGb());
            setJvmInitTotalMemorySizeMb(sm.getMem().getJvmInitTotalMemorySizeMb());
            setJvmMaxMemorySizeMb(sm.getMem().getJvmMaxMemorySizeMb());
            setJvmUsedMemorySizeMb(sm.getMem().getJvmUsedMemorySizeMb());
            setTotalMemorySizeGb(sm.getMem().getTotalMemorySizeGb());
            setUserUsageRate(sm.getMem().getUsedMemoryGb());
            setFreePhysicalMemorySizeGb(sm.getMem().getFreePhysicalMemorySizeGb());
            setCores(Double.valueOf(sm.getCpu().getCores()));
            setSysUsageRate(sm.getCpu().getSysUsageRate());
            setUserUsageRate(sm.getCpu().getUserUsageRate());
            setWaitingRate(sm.getCpu().getWaitingRate());
            setIdleRate(sm.getCpu().getWaitingRate());
            setOutSpeed(sm.getNet().getOutSpeed());
            setInSpeed(sm.getNet().getInSpeed());

        }};
        if (sm.getSmTime() != null) {
            catalogSystemMonitor.setSmTime(new Date(sm.getSmTime()));
        }
        return catalogSystemMonitor;
    }


    public static Integer getNum(Integer num) {
        if (num == null) {
            return 0;
        } else {
            return num;
        }

    }

}
