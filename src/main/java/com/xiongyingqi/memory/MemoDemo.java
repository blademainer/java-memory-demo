package com.xiongyingqi.memory;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongyingqi
 * @since 17-4-1 下午3:18
 */
public class MemoDemo {
    public static List<byte[]> bytes = new ArrayList<byte[]>();

    public static void allocate(long size, ByteUnit byteUnit) {
        printMemory();
        printGarbage();
        int sizeInt = (int) (Integer.MAX_VALUE & size * byteUnit.getSize());
        System.out.println("Allocating memory: " + sizeInt);
        byte[] bytes = new byte[sizeInt];
        System.out.println("Success allocated memory: " + sizeInt);
        MemoDemo.bytes.add(bytes);
    }

    public static void printMemory() {
        Runtime run = Runtime.getRuntime();
        long max = run.maxMemory();
        long total = run.totalMemory();
        long free = run.freeMemory();
        long usable = max - total + free;
        System.out.println("------------------------------------------");
        System.out.println("MaxMemory: " + ByteUnit.readSize(max));
        System.out.println("FreeMemory: " + ByteUnit.readSize(free));
        System.out.println("TotalMemory: " + ByteUnit.readSize(total));
        System.out.println("Usable: " + ByteUnit.readSize(usable));
    }

    private static void printGarbage() {
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean memoryPoolMXBean : memoryPoolMXBeans) {
            MemoryUsage peakUsage = memoryPoolMXBean.getPeakUsage();
            MemoryUsage collectionUsage = memoryPoolMXBean.getCollectionUsage();
            System.out.println("------------------------------------------");
            System.out.println("Name: " + memoryPoolMXBean.getName());
            try {
                System.out.println("CollectionUsage: " + buildMemoryUsageString(collectionUsage));
            } catch (Exception e) {
            }
            try {
                System.out.println("CollectionUsageThreshold: " + memoryPoolMXBean
                        .getCollectionUsageThreshold());
            } catch (Exception e) {
            }
            try {
                System.out.println("CollectionUsageThresholdCount: " + memoryPoolMXBean
                        .getCollectionUsageThresholdCount());
            } catch (Exception e) {
            }
            try {
                System.out.println("PeakUsage: " +  buildMemoryUsageString(peakUsage));
            } catch (Exception e) {
            }
        }
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory
                .getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
            System.out.println("------------------------------------------");
            System.out.println(garbageCollectorMXBean.getName());
            System.out.println("CollectionCount: " + garbageCollectorMXBean.getCollectionCount());
            System.out.println("CollectionTime: " + garbageCollectorMXBean.getCollectionTime() + "ms");
        }
    }

    private static String buildMemoryUsageString(MemoryUsage memoryUsage) {
        StringBuffer buf = new StringBuffer();
        buf.append("init = " + memoryUsage.getInit() + "(" + ByteUnit.readSize(memoryUsage.getInit()) + ") ");
        buf.append("used = " + memoryUsage.getUsed() + "(" + ByteUnit.readSize(memoryUsage.getUsed()) + ") ");
        buf.append("committed = " + memoryUsage.getCommitted() + "(" +
                           ByteUnit.readSize(memoryUsage.getCommitted()) + ") " );
        buf.append("max = " + memoryUsage.getMax() + "(" + ByteUnit.readSize(memoryUsage.getMax()) + ")");
        return buf.toString();
    }

    //    public static void main(String[] args) {
    //
    //        //        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
    ////        List<MemoryManagerMXBean> memoryManagerMXBeans = ManagementFactory
    ////                .getMemoryManagerMXBeans();
    ////        for (MemoryManagerMXBean memoryManagerMXBean : memoryManagerMXBeans) {
    ////        }
    //    }
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.err.println("!!!!!!!!Well received shutdown hook event!!!!!!!!");
            }
        });
        Runtime.getRuntime().traceMethodCalls(true);
        for (int i = 1; ; i++) {
            allocate(10, ByteUnit.M);
            System.out.println(i);
        }
    }
}
