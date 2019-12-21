package com.example.coding_hackathon_part_2;

public class ConstUser {
    private String userid;
    private String remarks;
    private String time;
    private String media_path;
    private Integer user_status;

    public ConstUser(Object userid, Object remarks, Object time, Object media_path, Object user_status) {
        this.userid = userid.toString();
        this.remarks = remarks.toString();
        this.time = time.toString();
        this.media_path = media_path.toString();
        this.user_status = Integer.valueOf(user_status.toString());
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remaeks) {
        this.remarks = remaeks;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMedia_path() {
        return media_path;
    }

    public void setMedia_path(String media_path) {
        this.media_path = media_path;
    }

    public Integer getUser_status() {
        return user_status;
    }

    public void setUser_status(Integer user_status) {
        this.user_status = user_status;
    }
}
