package com.bsfit.suaf.client;

/**
 *
 * kafka采取异步发送topic方式，有异步通就会有回调函数
 * main线程线生产数据ProductRecord放到RecordAccumulator
 * 然后sender去ProductRecord取然后放入topic
 * */
public class SimpleProductTest {
}
