package com.example.shiyin.personalise;

public class accuracy {
    private double first_accuracy;
    private double second_accuracy;
    private double third_accuracy;
    private double before_accuracy;
    private int first_cnt;
    private int second_cnt;
    private int third_cnt;
    private int before_cnt;
    public void print(){
        System.out.println("first_accuracy:"+first_accuracy+"  first_cnt:"+first_cnt);
    }
    public double getFirst_accuracy() {
        return first_accuracy;
    }

    public double getSecond_accuracy() {
        return second_accuracy;
    }

    public double getThird_accuracy() {
        return third_accuracy;
    }

    public int getFirst_cnt() {
        return first_cnt;
    }

    public void setFirst_cnt(int first_cnt) {
        this.first_cnt = first_cnt;
    }

    public int getSecond_cnt() {
        return second_cnt;
    }

    public void setSecond_cnt(int second_cnt) {
        this.second_cnt = second_cnt;
    }

    public int getThird_cnt() {
        return third_cnt;
    }

    public void setThird_cnt(int third_cnt) {
        this.third_cnt = third_cnt;
    }

    public void setThird_accuracy(double third_accuracy) {
        this.third_accuracy = third_accuracy;
    }

    public void setSecond_accuracy(double second_accuracy) {
        this.second_accuracy = second_accuracy;
    }

    public void setFirst_accuracy(double first_accuracy) {
        this.first_accuracy = first_accuracy;
    }

    public int getBefore_cnt() {
        return before_cnt;
    }

    public void setBefore_cnt(int before_cnt) {
        this.before_cnt = before_cnt;
    }

    public double getBefore_accuracy() {
        return before_accuracy;
    }

    public void setBefore_accuracy(double before_accuracy) {
        this.before_accuracy = before_accuracy;
    }
}
