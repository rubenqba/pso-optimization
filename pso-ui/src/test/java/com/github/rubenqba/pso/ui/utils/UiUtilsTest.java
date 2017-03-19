package com.github.rubenqba.pso.ui.utils;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;


/**
 * Created by ruben on 19/03/17.
 */
public class UiUtilsTest {

    @Test
    public void testFormatter() {
        NumberFormat formatter = new DecimalFormat("0E0");
        assertThat(formatter.format(0.123456), equalTo("1E-1"));
        assertThat(formatter.format(123456), equalTo("1E5"));
        assertThat(formatter.format(100d), equalTo("1E2"));
    }

    @Test
    public void getScientificNotation() {

        assertThat(UiUtils.getScientificNotation(10, 2), matchesPattern("1[eE]((\\+|\\-)?[0-9]+)"));
        assertThat(UiUtils.getScientificNotation(10, 2), matchesPattern("1E\\+?2"));
        assertThat(UiUtils.getScientificNotation(10, -2), equalTo("1E-2"));
        assertThat(UiUtils.getScientificNotation(10, 0), equalTo("1"));
    }

    @Test
    public void getScientificExponent() throws Exception {
        assertThat(UiUtils.getScientificExponent("1E-1"), equalTo(-1));
        assertThat(UiUtils.getScientificExponent("1E-7"), equalTo(-7));
        assertThat(UiUtils.getScientificExponent("1E+4"), equalTo(4));
        assertThat(UiUtils.getScientificExponent("1E2"), equalTo(2));
        assertThat(UiUtils.getScientificExponent("1"), equalTo(0));
    }

}