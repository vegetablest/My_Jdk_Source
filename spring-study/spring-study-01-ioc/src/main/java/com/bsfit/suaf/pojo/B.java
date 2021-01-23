package com.bsfit.suaf.pojo;

/**
 * 测试循环依赖
 *
 * @author bangsun
 */
public class B {
    private A a;

    public B() {
        System.out.println("B创建成功");
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
