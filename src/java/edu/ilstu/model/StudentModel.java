/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

import edu.ilstu.dao.StudentDAO;
import edu.ilstu.dao.StudentDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author kawibi
 */
public class StudentModel extends UserModel implements Serializable{

    private String major;
    

    public StudentModel() {
        super();
        
    }

    public StudentModel(int userid) {
        super(userid);
        
        
    }

    public StudentModel(String fname, String lname, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String major) {
        super(fname, lname, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
        this.major = major;
        
    }

    public StudentModel(int userId, String fname, String lname, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String major) {
        super(userId, fname, lname, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
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

    
    public StudentModel getStudentById(int id){
        StudentDAO dAO =new StudentDAOImpl();
        StudentModel um = dAO.findStudentById(id);
        
        return um;
    }
    
    
    public ArrayList<StudentModel> getAllStudents(){
        StudentDAO dAO =new StudentDAOImpl();
        ArrayList<StudentModel> studenList =dAO.getStudents();
        
        return studenList;
    }
    
    public String studentToJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("userid", super.getUserid());
        obj.put("fname", super.getFname());
        obj.put("lname", super.getLname());
        obj.put("securityq", super.getSecurityq());
        obj.put("securitya", super.getSecuritya());
        obj.put("email", super.getEmail());
        obj.put("phone", super.getPhone());
        obj.put("street", super.getStreet());
        obj.put("city", super.getCity());
        obj.put("state", super.getState());
        obj.put("zipcode", super.getZipCode());
        obj.put("country", super.getUserid());
        obj.put("type",String.valueOf(super.getIs_a()));

        return obj.toJSONString();
    }
}
