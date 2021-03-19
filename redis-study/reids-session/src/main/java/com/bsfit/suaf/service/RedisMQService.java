package com.bsfit.suaf.service;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 9:46
 * @verson
 */
public interface RedisMQService {

    void produce(String string);
    void consume();
}
