package com.company.entity;

public class SubCount implements Comparable<SubCount>{
    private String staId;
    private String subName;
    private int count;

    public SubCount(String staId, String subName, int count) {
        this.staId = staId;
        this.subName = subName;
        this.count = count;
    }

    public String getStaId() {
        return staId;
    }

    public String getSubName() {
        return subName;
    }

    public int getCount() {
        return count;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(SubCount subCount) {
        return this.count > subCount.getCount() ? -1 : (this.count == subCount.getCount() ? 0 : 1);
    }
}
