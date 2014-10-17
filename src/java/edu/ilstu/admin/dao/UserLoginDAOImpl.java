/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.admin.dao;

import edu.ilstu.admin.UserLogin;
import edu.ilstu.dao.ConnectionDB;
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
public class UserLoginDAOImpl implements UserLoginDAO {

    @Override
    public int createLogin(UserLogin userLogin) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO userlogin (username,password,userid)"
                    + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setString(i++, userLogin.getUsername());
            p.setString(i++, userLogin.getPassword());
            p.setInt(i++, userLogin.getUserId());

            p.executeUpdate();

            rs = p.getGeneratedKeys();
            rs.next();
            identity = rs.getInt(1);

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
    public void updateLogin(UserLogin userLogin) {
        int i = 1;
        
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("UPDATE userlogin "
                    + "SET password=?"
                    + "WHERE username=?");

            p.setString(i++, userLogin.getPassword());
            p.setString(i++, userLogin.getUsername());

            p.executeUpdate();

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

    }

    @Override
    public void deleteLogin(UserLogin userlogin) {
       int i = 1;
        
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM userlogin "
                    + "WHERE username=?");

          
            p.setString(i++, userlogin.getUsername());

            p.executeUpdate();

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
    }

    @Override
    public UserLogin getLogin(String username) {
         int i=1;
        UserLogin userLogin = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM userlogin where username=?");
            
            p.setString(i++, username);
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                userLogin = new UserLogin();
                userLogin.setUsername(rs.getString(j++));
                userLogin.setPassword(rs.getString(j++));
                userLogin.setUserId(rs.getInt(j++));
                

            }

            rs.close();

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

        return userLogin;
    }

    @Override
    public UserLogin getLogin(int userId) {
         int i=1;
        UserLogin userLogin = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM userlogin where userid=?");
            
            p.setInt(i++, userId);
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                userLogin = new UserLogin();
                userLogin.setUsername(rs.getString(j++));
                userLogin.setPassword(rs.getString(j++));
                userLogin.setUserId(rs.getInt(j++));
                

            }

            rs.close();

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

        return userLogin;
    }

    @Override
    public ArrayList<UserLogin> getLogins() {
          int i=1;
        UserLogin userLogin = null;
        ArrayList<UserLogin> list= new ArrayList();
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM userlogin");
            
          
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                userLogin = new UserLogin();
                userLogin.setUsername(rs.getString(j++));
                userLogin.setPassword(rs.getString(j++));
                userLogin.setUserId(rs.getInt(j++));
                
                list.add(userLogin);

            }

            rs.close();

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

        return list;
    }

}
