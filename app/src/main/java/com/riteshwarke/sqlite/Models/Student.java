package com.riteshwarke.sqlite.Models;

/**
 * Created by Ritesh Warke on 30/04/17.
 */

public class Student{
    int roll_no;
    String name;
    String address;

    public Student (){
    }
    public Student (String name, String address){
        this.name = name;
        this.address = address;
    }
    public Student (int roll_no, String name, String address){
        this.roll_no = roll_no;
        this.name = name;
        this.address = address;
    }
    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
