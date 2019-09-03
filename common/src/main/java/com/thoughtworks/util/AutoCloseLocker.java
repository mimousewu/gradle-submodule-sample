package com.thoughtworks.util;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
public class AutoCloseLocker extends ReentrantLock implements AutoCloseable {
    private static final Map<String, AutoCloseLocker> LOCKER_MAP = new ConcurrentHashMap<>();

    private final String key;

    public static AutoCloseLocker getLocker(final String key) {
        return LOCKER_MAP.computeIfAbsent(key, AutoCloseLocker::new);
    }

    @Override
    public void close() {
        if (this.getOwner().equals(Thread.currentThread())) {
            unlock();
            LOCKER_MAP.remove(this.key);
        }
    }
}
