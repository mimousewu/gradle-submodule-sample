package com.thoughtworks.util;

import com.thoughtworks.util.AutoCloseLocker;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AutoCloseLockerTest {
    @Test
    public void when10ThreadGConcurrentGetLockerShouldNotReturnMoreThanOneInstanceShouldNotThrowIllegalMonitorStateException() {
        String lockerName = "test";
        final AutoCloseLocker[] lockList = {null};
        ExecutorService threads = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler(new MyUncheckedExceptionHandler());
                return thread;
            }
        });
        for (int i = 0; i < 10; i++) {
            threads.execute(() -> {
                try (AutoCloseLocker lock = AutoCloseLocker.getLocker(lockerName)) {
                    if (lockList[0] == null) {
                        lockList[0] = lock;
                    } else {
                        assertEquals(lock, lockList[0]);
                    }
                    if (lock.tryLock()) {
                        doSomething();
                    } else {
                        System.out.println(Thread.currentThread().getId() + " out!");
                    }
                }
            });
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        try {
            threads.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(MyUncheckedExceptionHandler.failed);
    }

    private void doSomething() {
        try {
            System.out.println("now in:" + Thread.currentThread().getId());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public class MyUncheckedExceptionHandler implements Thread.UncaughtExceptionHandler {
        public static boolean failed = false;

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
            failed = true;
        }
    }
}
