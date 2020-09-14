package com.example.study.integer;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author hjs
 * @date 2020/9/4
 * @description
 */
public class IntegerTest {

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field myCache = cache.getDeclaredField("cache");
        myCache.setAccessible(true);

        Integer[] newCache = (Integer[]) myCache.get(cache);
        // 缓存中，原来132索引处的值4替换为133索引处的值5
        // 所以，在Integer.valueIf(4)取cache中索引为132处的值，此时为5
        // 因此，2+2=5
        newCache[132] = newCache[133];

        int a = 2;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b);
        System.out.println();
    }
}
