package com.bsfit.suaf.services;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author bangsun
 */
public interface Send {
    boolean send(String phoneNum, String templateCode, Map<String,Object> codeMap);

}
