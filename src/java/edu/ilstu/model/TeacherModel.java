/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

import edu.ilstu.dao.TeacherDAO;
import edu.ilstu.dao.TeacherDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class TeacherModel extends UserModel implements Serializable{

    private String department;
    private TeacherDAO tdao;
    
    public TeacherModel(){
        super();
        tdao=new TeacherDAOImpl();
    }
    
    public TeacherModel(int userid){
        super(userid);
        tdao=new TeacherDAOImpl();
        
    }
    
    public TeacherModel(String fname, String lname, String username, String password, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String department) {
        super(fname, lname, username, password, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
        this.department = department;
        tdao=new TeacherDAOImpl();
    }

      public TeacherModel(int userId, String fname, String lname, String username, String password, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String department) {
        super(userId, fname, lname, username, password, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
         this.department = department;
         tdao=new TeacherDAOImpl();

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

    
    public ArrayList<UserModel> getAllTeachers(){
    
          
        ArrayList<UserModel> teacherList= tdao.getTeachers();
        
        return teacherList;
    }
    
    public UserModel getTeacherById(int id){
        
        UserModel tm=tdao.findTeacherById(id);
        
        return tm;
    }
    
    
}
