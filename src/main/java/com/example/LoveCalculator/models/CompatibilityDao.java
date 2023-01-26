package com.example.LoveCalculator.models;

public class CompatibilityDao {
    private String fname;
    private String sname;
    private String percentage;
    private String result;

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getPercentage() {
        return percentage;
    }
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    
    @Override
    public String toString() {
        return "CompatibilityDao [fname=" + fname + ", sname=" + sname + ", percentage=" + percentage + ", result="
                + result + "]";
    }

    

}
