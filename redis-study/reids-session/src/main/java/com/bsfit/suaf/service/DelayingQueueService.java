package com.bsfit.suaf.service;

import com.bsfit.suaf.pojo.Message;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 10:07
 * @verson
 */
public interface DelayingQueueService {
    List<Message> pull();
    void push(Message message);
    void remove(Message message);
}
