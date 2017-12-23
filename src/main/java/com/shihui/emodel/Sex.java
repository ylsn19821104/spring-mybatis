package com.shihui.emodel;

import com.google.common.collect.Maps;

import java.util.Map;

public enum Sex {
    MALE(1, "男"), FEMALE(2, "女"), UNKNOWN(0, "未知");
    private int id;
    private String remark;
    private static Map<String, Sex> sexMap;

    static {
        sexMap = Maps.newHashMap();
        for (Sex sex : values())
            sexMap.put(sex.name(), sex);
    }

    Sex(int id, String remark) {
        this.id = id;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static Sex getSex(int id) {
        if (id == 1)
            return MALE;
        else if (id == 2)
            return FEMALE;

        return null;
    }

    public static Sex getSex(String sex) {
        return sexMap.get(sex);
    }

    @Override
    public String toString() {
        return "Sex{" +
                "remark='" + remark + '\'' +
                '}';
    }
}
