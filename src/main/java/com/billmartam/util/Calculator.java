package com.billmartam.util;

/**
 * Created by pp00344204 on 06/06/17.
 */
public final class Calculator {

    public static double addArrayElement(float[] nums) {
        double total = 0;
        for (float num : nums) {
            total += num;
        }
        return total;
    }
}
