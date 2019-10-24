package com.asuna.textutils.entity;

public class RateResult {

    private int blank;
    private int trans;

    public RateResult() {
    }

    public RateResult(int blank, int trans) {
        this.blank = blank;
        this.trans = trans;
    }

    public int getBlank() {
        return blank;
    }

    public void setBlank(int blank) {
        this.blank = blank;
    }

    public int getTrans() {
        return trans;
    }

    public void setTrans(int trans) {
        this.trans = trans;
    }

    @Override
    public String toString() {
        return "RateResult{" +
                "blank=" + blank +
                ", trans=" + trans +
                '}';
    }
}
