package com.bsfit.suaf.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author bangsun
 */
@Data
public class Student {
    private String name;
    private Address address;
    private List<String> hobbys;
    private Map<String,Object> card;
    private Set<String> games;
    private Properties info;
    private String[] bookes;
    private String empty;
    private String empty1;
}
