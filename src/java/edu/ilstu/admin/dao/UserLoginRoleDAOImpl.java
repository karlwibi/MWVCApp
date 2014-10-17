/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.admin.dao;

import edu.ilstu.admin.UserLoginRole;
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
public class UserLoginRoleDAOImpl implements UserLoginRoleDAO {

    @Override
    public int setUserRole(UserLoginRole userLoginRole) {
         int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO userlogin_role (username,rolename)"
                    + "VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setString(i++, userLoginRole.getUsername());
            p.setString(i++, userLoginRole.getRolename());
           

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
    public void updateRole(UserLoginRole userLoginRole) {
         int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("UPDATE userlogin_role "
                    + "SET rolename=?"
                    + "WHERE username=?");

            p.setString(i++, userLoginRole.getRolename());
            p.setString(i++, userLoginRole.getUsername());
         
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
    public UserLoginRole getUserRole(String userName) {
           int i=1;
        UserLoginRole userLoginRole = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM userlogin_role where username=?");
            
            p.setString(i++, userName);
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                userLoginRole = new UserLoginRole();
                userLoginRole.setUsername(rs.getString(j++));
                userLoginRole.setRolename(rs.getString(j++));
          
                

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

        return userLoginRole;
    }

    @Override
    public ArrayList<UserLoginRole> getUserRoles() {
               int i=1;
        UserLoginRole userLoginRole = null;
        ArrayList<UserLoginRole> list= new ArrayList();
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM userlogin_role ");
            
            
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                userLoginRole = new UserLoginRole();
                userLoginRole.setUsername(rs.getString(j++));
                userLoginRole.setRolename(rs.getString(j++));
          
                list.add(userLoginRole);
                

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
