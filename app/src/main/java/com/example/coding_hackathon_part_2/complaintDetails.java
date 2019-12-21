package com.example.coding_hackathon_part_2;

public class complaintDetails {
    private String remarks;
    private String userid;

    public complaintDetails(Object userid, Object remarks) {
        this.remarks = remarks.toString();
        this.userid = userid.toString();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
