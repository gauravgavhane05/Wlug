package com.example.shree.wlug;

/**
 * Created by rohit on 8/9/17.
 */

public class EventData {

    public String fname,lname,email,mobno,gender,event,college,amount;
    public EventData() {
    }

    public EventData(String f,String l,String email,String mobno,String gender,String event,String college,String amount)
    {

        this.fname=f;
        this.lname=l;
        this.email=email;
        this.mobno=mobno;
        this.event=event;
        this.college=college;
        this.amount=amount;
        this.gender=gender;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
