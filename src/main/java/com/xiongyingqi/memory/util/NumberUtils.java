package com.xiongyingqi.memory.util;

import java.math.BigDecimal;

/**
 *
 * @author xiongyingqi
 * @since 16-6-16 下午4:23
 */
public abstract class NumberUtils {
    /**
     * 四舍五入除法
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        BigDecimal result = dividend.divide(divisor, 2, BigDecimal.ROUND_HALF_UP);
        return result;
    }

}
