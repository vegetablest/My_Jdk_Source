package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置了一个id为route_name的路由规则,
 * 当访问地址http://localhost:9527/guonei时会自动转发到地址: http://news.baidu.com/guonei
 * @author bangsun
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customerRoutes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("my_route",
                r -> r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
        return routes.build();
    }
    @Bean
    public RouteLocator customerRoutes2(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("my_route2",
                r -> r.path("/guoji").uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
