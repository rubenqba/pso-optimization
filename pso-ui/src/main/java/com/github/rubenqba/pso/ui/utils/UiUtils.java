package com.github.rubenqba.pso.ui.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ruben on 19/03/17.
 */
public final class UiUtils {

    public static String getScientificNotation(int base, int exponent) {
        NumberFormat formatter = new DecimalFormat("0E0");
        return exponent == 0 ? "1" : formatter.format(Math.pow(base, exponent));
    }

    public static Integer getScientificExponent(String number) {
        Matcher m = Pattern.compile("1([eE]((\\+|\\-)?[0-9]+))?").matcher(number);
        if (m.matches())
            return StringUtils.isBlank(m.group(2)) ? 0 : Integer.parseInt(m.group(2));
        return 0;
    }
}
