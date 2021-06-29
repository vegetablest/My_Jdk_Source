package com.bsfit.suaf;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <br>
 * junit5
 * @author bangsun
 * @data 2021/4/26 21:39
 * @verson
 */
@Slf4j
@SpringBootTest
@DisplayName("Junit5单元测试")
public class Junit5Test {

    @BeforeEach
    public void testBefore() throws Exception{
        log.info("Test Start ……");
    }
    @AfterEach
    public void testAfter() throws Exception{
        log.info("Test End ……");
    }
    @BeforeAll
    public static void testBeforeAll() throws Exception{
        log.info("everyOne Start ……");
    }
    @AfterAll
    public static void testAfterAll() throws Exception{
        log.info("everyOne End ……");
    }
    @Test
    @DisplayName("Junit5单元测试1")
    public void testA() throws Exception{
        System.out.println("junit5");
    }
    @Test
    @DisplayName("Junit5单元测试2")
    public void testB() throws Exception{
        System.out.println("junit5");
    }
}
