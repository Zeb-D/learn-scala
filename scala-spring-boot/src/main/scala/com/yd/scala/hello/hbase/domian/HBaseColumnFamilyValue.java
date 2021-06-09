package com.yd.scala.hello.hbase.domian;

import java.util.List;

public class HBaseColumnFamilyValue {
    private String familyName;
    private List<HBaseRowValue> rowValues;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<HBaseRowValue> getRowValues() {
        return rowValues;
    }

    public void setRowValues(List<HBaseRowValue> rowValues) {
        this.rowValues = rowValues;
    }
}