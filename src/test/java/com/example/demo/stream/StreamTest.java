package com.example.demo.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamTest {
    @Test
    public void test1(){
        int[] sourceArr = {12,33,10,99,11,12};
        int[] desArr = IntStream.of(sourceArr)
                .filter((element)->element>10)
                .map((element)->element+1)
                .distinct()
                .sorted()
                .toArray();
        System.out.println(Arrays.asList(desArr).toString());
    }
}
