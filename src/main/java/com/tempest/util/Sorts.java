package com.tempest.util;

/**
 * @author qiu
 * @date 2020/8/12 9:55 上午
 */
public class Sorts {
    private String direction;
    private String field;

    public Sorts() {
    }

    public Sorts(String direction, String field) {
        this.direction = direction;
        this.field = field;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}

