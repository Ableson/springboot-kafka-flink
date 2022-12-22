package com.bigdata.springbootkafkaflink.flinkTrigger;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.state.ReducingState;
import org.apache.flink.api.common.state.ReducingStateDescriptor;
import org.apache.flink.api.common.typeutils.base.LongSerializer;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

/**
 * 当我们要在一个CountWinow里统计数据时，如果数据迟迟达不到最大次数而窗口没有关闭，会造成内存泄漏
 * 所以我们需要一个带超时时间的计次的窗口触发器。
 * @param <T>
 */
public class CountWithTimeoutTrigger<T> extends Trigger<T, TimeWindow> {

    /**
     * 窗口大小
     */
    private int maxSize;
    /**
     * 时间类型
     */
    private TimeCharacteristic timeType;

    private ReducingStateDescriptor<Long> countStateDescriptor =
            new ReducingStateDescriptor("counter", new Sum(), LongSerializer.INSTANCE);


    public CountWithTimeoutTrigger(int maxSize, TimeCharacteristic timeType) {
        this.maxSize = maxSize;
        this.timeType = timeType;
    }

    private TriggerResult fireAndPurge(TimeWindow window, TriggerContext ctx) throws Exception {
        clear(window, ctx);
        return TriggerResult.FIRE_AND_PURGE;
    }

    @Override
    public TriggerResult onElement(T element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
        ReducingState<Long> countState = ctx.getPartitionedState(countStateDescriptor);
        countState.add(1L);//计数器+1

        if (countState.get() >= maxSize) { //当计数次数满足条件时候 关闭窗口
            return fireAndPurge(window, ctx);
        }
        if (timestamp >= window.getEnd()) { //当超时时 关闭窗口
            return fireAndPurge(window, ctx);
        } else {
            return TriggerResult.CONTINUE;
        }
    }

    @Override
    public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {

        if (timeType != TimeCharacteristic.ProcessingTime) {
            return TriggerResult.CONTINUE;
        }
        if (time >= window.getEnd()) {
            return TriggerResult.CONTINUE;
        } else {
            return fireAndPurge(window, ctx);
        }
    }

    @Override
    public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        if (timeType != TimeCharacteristic.EventTime) {
            return TriggerResult.CONTINUE;
        }

        if (time >= window.getEnd()) {
            return TriggerResult.CONTINUE;
        } else {
            return fireAndPurge(window, ctx);
        }
    }

    @Override
    public void clear(TimeWindow window, TriggerContext ctx) throws Exception {
        ReducingState<Long> countState = ctx.getPartitionedState(countStateDescriptor);
        countState.clear();
    }


    class Sum implements ReduceFunction<Long> {
        @Override
        public Long reduce(Long value1, Long value2) throws Exception {
            return value1 + value2;
        }
    }

}

