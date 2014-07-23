/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.StudentModel;
import edu.ilstu.model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public int createStudent(StudentModel aStudent) {
        int identity = 0;
        Connection con = ConnectionDB.getConnInst();

        try {

            int i = 1;
            PreparedStatement p = con.prepareStatement("INSERT INTO student (userid,major)"
                    + "VALUES(?,?)");
            p.setInt(i++, aStudent.getUserid());
            p.setString(i++, aStudent.getMajor());
            p.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {

        }
        identity = 1;
        return identity;
    }

    @Override
    public void upadteStudent(StudentModel aStudent) {

        Connection con = ConnectionDB.getConnInst();

        try {

            int i = 1;
            PreparedStatement p = con.prepareStatement("UPDATE teacher set major=? WHERE userId=?)");

            p.setString(i++, aStudent.getMajor());
            p.setInt(i++, aStudent.getUserid());

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

    @Override
    public ArrayList<UserModel> getStudents() {
        int i=1;
          ResultSet rs = null;
          UserModel userModel= new StudentModel();
          ArrayList<UserModel> studentList=new ArrayList<UserModel>();
        Connection con = ConnectionDB.getConnInst();
        
        try {

            
            PreparedStatement p = con.prepareStatement("Select u.userid,fname,lname,username,password,securityq,securitya,email,phone,street,city,state,zipcode,country,is_a  "
                    + "FROM user u"
                    + "INNER JOIN student s "
                    + " WHERE u.userid=s.userid");

           
            rs=p.executeQuery();
            
            while(rs.next()){
                
                userModel.setUserid(rs.getInt(i++));
                userModel.setFname(rs.getString(i++));
                userModel.setLname(rs.getString(i++));
                userModel.setUsername(rs.getString(i++));
                userModel.setPassword(rs.getString(i++));
                userModel.setSecurityq(rs.getString(i++));
                userModel.setSecuritya(rs.getString(i++));
                userModel.setEmail(rs.getString(i++));
                userModel.setPhone(rs.getInt(i++));
                userModel.setStreet(rs.getString(i++));
                
                
                
            }
            
            

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
        
        return studentList;
    }

    @Override
    public UserModel findStudentById(int userid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteStudent(StudentModel aStudent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
