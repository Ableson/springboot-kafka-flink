package com.dtinone.datashare.scheduled;

import lombok.Data;

import java.util.Date;

@Data
public class CatalogSystemMonitor {

    private String guid;

    private String deviceId;

    private Double totalDiskSpaceGb;

    private Double freeDiskSpaceGb;

    private Double usedDiskSpaceGb;

    private Double jvmInitTotalMemorySizeMb;

    private Double jvmMaxMemorySizeMb;

    private Double jvmUsedMemorySizeMb;

    private Double jvmFreeMemorySizeMb;

    private Double totalMemorySizeGb;

    private Double usedMemoryGb;

    private Double freePhysicalMemorySizeGb;

    private Double cores;

    private Double sysUsageRate;

    private Double userUsageRate;

    private Double waitingRate;

    private Double idleRate;

    private Double inSpeed;

    private Double outSpeed;

    private Double uacInSpeed;

    private Double uacOutSpeed;

    private Date smTime;


}