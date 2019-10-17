package com.company.entity;

public class IDCount implements Comparable<IDCount>{
    private String staId;
    private int count;

    public IDCount(String staId, int count) {
        this.staId = staId;
        this.count = count;
    }

    public String getStaId() {
        return staId;
    }

    public int getCount() {
        return count;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(IDCount idCount) {
        return this.count > idCount.getCount() ? -1 : (this.count == idCount.getCount() ? 0 : 1);
    }
}
