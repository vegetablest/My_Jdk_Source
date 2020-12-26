package com.bsfit.suaf.config;

import com.bsfit.suaf.log.AnnoDiyPointCut;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class BeanConfig {
    @Bean
    public AnnoDiyPointCut annoDiyPointCut(){
        return new AnnoDiyPointCut();
    }
}
