/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kawibi
 */
public class TeacherDAOImpl implements TeacherDAO, Serializable {

    @Override
    public int createTeacher(TeacherModel aTeacher) {
        
        int identity=0;
        Connection con=ConnectionDB.getConnInst();
        
        try{
            
            int i=1;
            PreparedStatement p=con.prepareStatement("INSERT INTO teacher (userid,department)"
                    + "VALUES(?,?)");
            p.setInt(i++, aTeacher.getUserid());
            p.setString(i++, aTeacher.getDepartment());
            p.executeUpdate();
            
        }catch(SQLException e){
             System.err.println(e.getMessage());
        }finally{
            if (con!=null){
                try{
                    con.close();
                }catch(SQLException e){
                    System.err.println(e.getMessage());
              }
            }
        }
        identity=1;
        return identity;
            
                
       
    }
    
        
    @Override
    public void updateTeacher(TeacherModel aTeacher) {
        
        Connection con=ConnectionDB.getConnInst();
        
        try{
            
            int i=1;
            PreparedStatement p=con.prepareStatement("UPDATE teacher set department=? WHERE userId=?)");
            
            p.setString(i++, aTeacher.getDepartment());
            p.setInt(i++, aTeacher.getUserid());
            
            p.executeUpdate();
            
        }catch(SQLException e){
             System.err.println(e.getMessage());
        }finally{
            if (con!=null){
                try{
                    con.close();
                }catch(SQLException e){
                    System.err.println(e.getMessage());
              }
            }
        }
        
               
    }
    
    

    @Override
    public TeacherModel findTeacherById(int userId) {
        
        int j = 1;
        ResultSet rs = null;
        TeacherModel userModel = null;
        
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("Select t.userid as userid,fname,lname,securityq,securitya,email,phone,street,city,state,zipcode,country,is_a, department  "
                    + " FROM user u"
                    + " INNER JOIN teacher t "
                    + " WHERE u.userid=t.userid AND t.userid=?");
            
            p.setInt(j++, userId);
            rs = p.executeQuery();
            
            while (rs.next()) {
               int i = 1;  
               userModel = new TeacherModel();
                userModel.setUserid(rs.getInt(i++));
                userModel.setFname(rs.getString(i++));
                userModel.setLname(rs.getString(i++));
                userModel.setSecurityq(rs.getString(i++));
                userModel.setSecuritya(rs.getString(i++));
                userModel.setEmail(rs.getString(i++));
                userModel.setPhone(rs.getInt(i++));
                userModel.setStreet(rs.getString(i++));
                userModel.setCity(rs.getString(i++));
                userModel.setState(rs.getString(i++));
                userModel.setZipCode(rs.getInt(i++));
                userModel.setCountry(rs.getString(i++));
                userModel.setIs_a(rs.getString(i++).charAt(0));
                userModel.setDepartment(rs.getString(i++));
                
             }

            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        return userModel;
    }

    @Override
    public ArrayList<TeacherModel> getTeachers() {
      
        ResultSet rs = null;
        TeacherModel userModel = null;
        ArrayList<TeacherModel> teacherList = new ArrayList<>();
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("Select t.userid as userid,fname,lname,securityq,securitya,email,phone,street,city,state,zipcode,country,is_a, department  "
                    + " FROM user u"
                    + " INNER JOIN teacher t "
                    + " WHERE u.userid=t.userid");

            rs = p.executeQuery();

            while (rs.next()) {
                int i = 1;
                userModel = new TeacherModel();
                 userModel.setUserid(rs.getInt(i++));
                userModel.setFname(rs.getString(i++));
                userModel.setLname(rs.getString(i++));
                userModel.setSecurityq(rs.getString(i++));
                userModel.setSecuritya(rs.getString(i++));
                userModel.setEmail(rs.getString(i++));
                userModel.setPhone(rs.getInt(i++));
                userModel.setStreet(rs.getString(i++));
                userModel.setCity(rs.getString(i++));
                userModel.setState(rs.getString(i++));
                userModel.setZipCode(rs.getInt(i++));
                userModel.setCountry(rs.getString(i++));
                userModel.setIs_a(rs.getString(i++).charAt(0));
                userModel.setDepartment(rs.getString(i++));
                
                teacherList.add(userModel);

            }

            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        return teacherList;
    }

    @Override
    public void deleteTeacher(TeacherModel aTeacher) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM user where userid=?");
            p.setInt(i++, aTeacher.getUserid());

            p.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

    }
    
}
