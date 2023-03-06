package com.lostlife.alumni.models;

public class Student {

    private int id;
    private String name;
    private String branch;
    private int graduationyear;
    private String mobNo;
    private String email;

    public Student() {
    }

    public Student(String name, String branch, int graduationyear, String mobNo, String email) {
        this.name = name;
        this.branch = branch;
        this.graduationyear = graduationyear;
        this.mobNo = mobNo;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getGraduationyear() {
        return graduationyear;
    }

    public void setGraduationyear(int graduationyear) {
        this.graduationyear = graduationyear;
    }
}
