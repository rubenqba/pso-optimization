package com.github.rubenqba.phd.pso;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PSOUtilityTest {

    @Test
    public void testIndexOfMin() {
        List<Integer> list = Arrays.asList(34, 11, 98, 56, 43);

        int minIndex = IntStream.range(0,list.size()).boxed()
                .min(comparingInt(list::get))
                .get();

        assertThat(minIndex, equalTo(1));
    }
}