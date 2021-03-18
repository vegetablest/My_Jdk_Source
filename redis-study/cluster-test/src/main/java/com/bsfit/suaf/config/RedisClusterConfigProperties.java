package com.bsfit.suaf.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <br>
 * 接受配置
 * @author bangsun
 * @data 2021/3/13 20:22
 * @verson
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisClusterConfigProperties {
    private List<String> nodes;
    private Integer maxAttempts;
    private Integer connectionTimeout;
    private Integer soTimeout;
    private String password;
}