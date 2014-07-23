/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
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
public class TeacherDAOImpl implements TeacherDAO {

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
    
    public void updateTeacherTbl(int identity, TeacherModel aTeacher, Connection con){
        
    }

    @Override
    public UserModel findTeacherById(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<UserModel> getTeachers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTeacher(TeacherModel aTeacher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
