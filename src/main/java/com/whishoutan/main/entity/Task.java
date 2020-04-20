package com.whishoutan.main.entity;

public class Task {
    private Integer id;
    private String target;
    private boolean if_finish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isIf_finish() {
        return if_finish;
    }

    public void setIf_finish(boolean if_finish) {
        this.if_finish = if_finish;
    }
}
