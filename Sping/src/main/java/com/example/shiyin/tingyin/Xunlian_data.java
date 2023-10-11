package com.example.shiyin.tingyin;


import java.util.Date;

public class Xunlian_data {

    private String user;
    private int right_cnt;
    private int wrong_cnt;
    private int cost_time;
    private int all_cnt;
    private double right_per;
    private String ans;
    private String all_dian;
    private String music_link;
    private String type;
    private String diff;
    private Date time;
    private double accuracy;
    private double cnt;

    public String charu(){
        String sql = "insert into tingyin_xunlian(user,  right_cnt, wrong_cnt, all_cnt, right_per, ans, all_dian,music_link,type,diff) " +
                "values ('"+user+"',"+right_cnt+","+wrong_cnt+","+all_cnt+","+right_per+",'"+ans+"','"+all_dian+"','"+music_link+"','"+type+"','"+diff+"')";

        System.out.println(sql);


        return sql;
    }

    public String print(){
        return "ans:"+ans+"  all_cnt= "+all_cnt +" cnt:"+cnt+" acc:"+accuracy+"right_acc"+right_per;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getRight_cnt() {
        return right_cnt;
    }

    public void setRight_cnt(int right_cnt) {
        this.right_cnt = right_cnt;
    }

    public int getWrong_cnt() {
        return wrong_cnt;
    }

    public void setWrong_cnt(int wrong_cnt) {
        this.wrong_cnt = wrong_cnt;
    }

    public int getCost_time() {
        return cost_time;
    }

    public void setCost_time(int cost_time) {
        this.cost_time = cost_time;
    }

    public int getAll_cnt() {
        return all_cnt;
    }

    public void setAll_cnt(int all_cnt) {
        this.all_cnt = all_cnt;
    }

    public double getRight_per() {
        return right_per;
    }

    public void setRight_per(double right_per) {
        this.right_per = right_per;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getAll_dian() {
        return all_dian;
    }

    public void setAll_dian(String all_dian) {
        this.all_dian = all_dian;
    }

    public String getMusic_link() {
        return music_link;
    }

    public void setMusic_link(String music_link) {
        this.music_link = music_link;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getCnt() {
        return cnt;
    }

    public void setCnt(double cnt) {
        this.cnt = cnt;
    }
}
