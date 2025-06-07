package com.hvv.agriservice.config.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class SnowflakeIdGenerator {
    private static final long EPOCH = 1288834974657L;
    private static final long MACHINE_ID = 3424L;    // ID cua machine, neu co nhieu machine
    private static final AtomicLong sequence = new AtomicLong(0);

    public synchronized long generateId() {
        long timestamp = System.currentTimeMillis() - EPOCH;
        return (timestamp << 22) | (MACHINE_ID << 22) | sequence.incrementAndGet();
    }
}
