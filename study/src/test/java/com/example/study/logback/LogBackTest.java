package com.example.study.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author hjs
 * @date 2020/7/8
 * @description
 */
@Slf4j
public class LogBackTest {

    @Test
    public void test() {
        log.debug("------ debug");
        log.info("------ info");
        log.warn("------ warn");
        log.error("------ error");
    }
}
