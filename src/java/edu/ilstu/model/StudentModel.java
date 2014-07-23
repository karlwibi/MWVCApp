/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

/**
 *
 * @author kawibi
 */
public class StudentModel extends UserModel {

    private String major;

    public StudentModel() {
    }

    public StudentModel(int userid) {
        super(userid);
    }

    public StudentModel(String fname, String lname, String username, String password, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String major) {
        super(fname, lname, username, password, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
        this.major = major;
    }

    public StudentModel(int userId, String fname, String lname, String username, String password, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String major) {
        super(userId, fname, lname, username, password, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
        this.major = major;

    }

    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }

}
