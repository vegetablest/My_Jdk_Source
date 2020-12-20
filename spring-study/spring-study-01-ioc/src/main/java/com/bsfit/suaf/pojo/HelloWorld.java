package com.bsfit.suaf.pojo;

import java.security.Principal;

/**
 * @author bangsun
 */
public class HelloWorld {
    private String str;

    public HelloWorld() {
    }

    public HelloWorld(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "HelloWorld{" +
                "str='" + str + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HelloWorld that = (HelloWorld) o;

        return str != null ? str.equals(that.str) : that.str == null;
    }

    @Override
    public int hashCode() {
        return str != null ? str.hashCode() : 0;
    }
}
