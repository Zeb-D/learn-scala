package com.yd.scala.hello.hbase.domian;

public class HBaseRowValue {
    private String rowName;

    private String rowValue;

    public HBaseRowValue(String rowName, String rowValue) {
        this.rowName = rowName;
        this.rowValue = rowValue;
    }

    public HBaseRowValue() {
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getRowValue() {
        return rowValue;
    }

    public void setRowValue(String rowValue) {
        this.rowValue = rowValue;
    }
}
