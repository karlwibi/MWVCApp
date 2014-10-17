/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.StudentModel;
import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class UserDAOImpl implements UserDAO, Serializable {

    @Override
    public int createNewUser(UserModel aUser) {

        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        //inserting valuer into the user table in the webrtc database
        try {
            int i = 1;
            PreparedStatement p = con.prepareStatement("INSERT INTO user (fname,lname, securityq,securitya,email,phone,street,city,state,zipcode,country,is_a)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            p.setString(i++, aUser.getFname());
            p.setString(i++, aUser.getLname());
            p.setString(i++, aUser.getSecuritya());
            p.setString(i++, aUser.getSecurityq());

            p.setString(i++, aUser.getEmail());
            p.setInt(i++, aUser.getPhone());
            p.setString(i++, aUser.getStreet());
            p.setString(i++, aUser.getCity());
            p.setString(i++, aUser.getState());
            p.setInt(i++, aUser.getZipCode());
            p.setString(i++, aUser.getCountry());
            p.setString(i++, Character.toString(aUser.getIs_a()));

            p.executeUpdate();
            rs = p.getGeneratedKeys();
            rs.next();
            identity = rs.getInt(1);
            aUser.setUserid(identity);
            rs.close();

            if (identity != 0) {
                if (aUser instanceof TeacherModel) {

                    TeacherDAO tdao = new TeacherDAOImpl();
                    tdao.createTeacher((TeacherModel) aUser);
                    
                } else {
                    StudentDAO sdao=new StudentDAOImpl();
                    sdao.createStudent((StudentModel)aUser);
                }

            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }

        return identity;

    }

    @Override
    public void updateUser(UserModel aUser) {
       
        
        Connection con=ConnectionDB.getConnInst();
        
        //inserting valuer into the user table in the webrtc database
        try{
            int i=1;
            PreparedStatement p=con.prepareStatement("UPDATE user "
                    + "set fname=?,lname=?, securityq=?,"
                    + "securitya=?,email=?,phone=?,street=?,city=?,state=?,zipcode=?,"
                    + "country=?,is_a=?"
                    + "WHERE userid=?");
            p.setString(i++, aUser.getFname());
            p.setString(i++, aUser.getLname());
            p.setString(i++, aUser.getSecuritya());
            p.setString(i++, aUser.getSecurityq());

            p.setString(i++, aUser.getEmail());
            p.setInt(i++, aUser.getPhone());
            p.setString(i++, aUser.getStreet());
            p.setString(i++, aUser.getCity());
            p.setString(i++, aUser.getState());
            p.setInt(i++, aUser.getZipCode());
            p.setString(i++, aUser.getCountry());
            p.setString(i++, Character.toString(aUser.getIs_a()));
            p.setInt(i++, aUser.getUserid());
            p.executeUpdate();
            
            
            
            if(aUser instanceof TeacherModel){
                
                TeacherDAO tdao= new TeacherDAOImpl();
                tdao.updateTeacher((TeacherModel)aUser);
    
             }else{
                
                StudentDAO sdao = new StudentDAOImpl();
                sdao.upadteStudent((StudentModel)aUser);
            }
            
            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }finally{
            try {
                if(con!=null){
                con.close();}
                
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        }
    }

    @Override
    public ArrayList<UserModel> getAllUsers() {
        
        ArrayList<UserModel> allUsers=new ArrayList<UserModel>();
        StudentDAO sdao= new StudentDAOImpl();
        TeacherDAO tdao = new TeacherDAOImpl();
        
        allUsers.addAll(tdao.getTeachers());
        allUsers.addAll(sdao.getStudents());
        
        return allUsers;    
              
        
    }

    @Override
    public UserModel getUserBy(int userId) {
        UserModel aUserModel=null;
        
        StudentDAO sdao = new StudentDAOImpl();
        TeacherDAO tdAO = new TeacherDAOImpl();
        
        if(sdao.findStudentById(userId)!=null){
            aUserModel=sdao.findStudentById(userId);
        }else{
            aUserModel=tdAO.findTeacherById(userId);
        }
        
        
        return aUserModel;
    }

    @Override
    public void deleteUser(UserModel userModel){
                    
        StudentDAO sdao = new StudentDAOImpl();
        TeacherDAO tdAO = new TeacherDAOImpl();
        
        if(userModel instanceof TeacherModel){
            tdAO.deleteTeacher((TeacherModel)userModel);
        }else{
            sdao.deleteStudent((StudentModel)userModel);
        }
    }
}
