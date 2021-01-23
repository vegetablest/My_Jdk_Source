package com.bsfit.suaf.pojo;

/**
 * 测试循环依赖
 * @author bangsun
 */
public class A {
    private B b;

    public A() {
        System.out.println("A创建成功");
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
