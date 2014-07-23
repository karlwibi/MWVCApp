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
public class TeacherModel extends UserModel {

    private String department;
    
    public TeacherModel(){     
    }
    
    public TeacherModel(int userid){
        super(userid);
    }
    
    public TeacherModel(String fname, String lname, String username, String password, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String department) {
        super(fname, lname, username, password, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
        this.department = department;
    }

      public TeacherModel(int userId, String fname, String lname, String username, String password, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String major) {
        super(userId, fname, lname, username, password, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
         this.department = department;

    }
    
    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    
}
