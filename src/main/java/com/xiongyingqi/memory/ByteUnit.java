package com.xiongyingqi.memory;

import com.xiongyingqi.memory.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xiongyingqi
 * @since 17-4-1 下午3:21
 */
public enum ByteUnit {
    K(1024L), M(1024L * K.size), G(1024L * M.size), T(1024L * G.size);

    private Long size;

    ByteUnit(Long size) {
        this.size = size;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public static String readSize(Long size) {
        ByteUnit[] values = ByteUnit.values();
        Arrays.sort(values, new Comparator<ByteUnit>() {
            public int compare(ByteUnit o1, ByteUnit o2) {
                return (int) (o2.getSize() - o1.getSize());
            }
        });
        for (ByteUnit value : values) {
            Long unitSize = value.getSize();
            double valueDisplay = NumberUtils.divide(new BigDecimal(size), new BigDecimal(unitSize))
                    .doubleValue();
            if (valueDisplay >= 1.0 && valueDisplay < 1024.0) {
                return valueDisplay + "" + value.toString();
            }
        }
        return size + "";
    }

    public static void main(String[] args) {
        System.out.println(readSize(1L));
        System.out.println(readSize(1024L));
        System.out.println(readSize(2024L));
        System.out.println(readSize(1024 * 1024L));
        System.out.println(readSize(1024 * 1024L * 1024L * 1022));
    }
}
