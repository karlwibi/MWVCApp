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
import org.json.simple.JSONObject;

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
    
    public TeacherModel(String fname, String lname, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String department) {
        super(fname, lname, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
        this.department = department;
        tdao=new TeacherDAOImpl();
    }

      public TeacherModel(int userId, String fname, String lname, String username, String password, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a, String department) {
        super(userId, fname, lname, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
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

    
    public ArrayList<TeacherModel> getAllTeachers(){
    
          
        ArrayList<TeacherModel> teacherList= tdao.getTeachers();
        
        return teacherList;
    }
    
    public TeacherModel getTeacherById(int id){
        
        TeacherModel tm=tdao.findTeacherById(id);
        
        return tm;
    }
    
    public String teacherToJSONString() {

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
        obj.put("type", String.valueOf(super.getIs_a()));

        return obj.toJSONString();
    }
}
