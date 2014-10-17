/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.StudentModel;
import edu.ilstu.model.UserModel;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class StudentDAOImpl implements StudentDAO, Serializable {

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
            PreparedStatement p = con.prepareStatement("UPDATE student set major=? WHERE userId=?)");

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
    public ArrayList<StudentModel> getStudents() {
        
        ResultSet rs = null;
        StudentModel userModel = null;
        ArrayList<StudentModel> studentList = new ArrayList<StudentModel>();
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT u.userid as userid,fname,lname,securityq,securitya,email,phone,street,city,state,zipcode,country,is_a ,major"
                    + " FROM user u"
                    + " INNER JOIN student s"
                    + " WHERE u.userid=s.userid");

            rs = p.executeQuery();

            while (rs.next()) {
                int i = 1;
                userModel = new StudentModel();
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
                userModel.setMajor(rs.getString(i++));

                studentList.add(userModel);

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

        return studentList;
    }

    @Override
    public StudentModel findStudentById(int userid) {
       
        int j = 1;

        ResultSet rs = null;
        StudentModel userModel = null;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("Select u.userid as userid,fname,lname,securityq,securitya,email,phone,street,city,state,zipcode,country,is_a, major  "
                    + " FROM user u"
                    + " INNER JOIN student s "
                    + " WHERE u.userid=s.userid AND u.userid=?");
            p.setInt(j++, userid);

            rs = p.executeQuery();

            while (rs.next()) {

                 int i = 1;
                userModel = new StudentModel();
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
                userModel.setMajor(rs.getString(i++));

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

        return userModel;
    }

    @Override
    public void deleteStudent(StudentModel aStudent) {

        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM user where userid=?");
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

}
