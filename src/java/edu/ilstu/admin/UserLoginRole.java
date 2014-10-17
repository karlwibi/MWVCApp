/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.admin;

import edu.ilstu.admin.dao.UserLoginRoleDAO;
import edu.ilstu.admin.dao.UserLoginRoleDAOImpl;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class UserLoginRole {

    private String username;
    private String rolename;

    public UserLoginRole() {
    }

    public UserLoginRole(String username, String rolename) {
        this.username = username;
        this.rolename = rolename;
    }

    public int setUserRole() {
        
        UserLoginRoleDAO ulrdao = new UserLoginRoleDAOImpl();

        int returnValue = ulrdao.setUserRole(this);

        return returnValue;
    }

    public void updateRole(){
                
        UserLoginRoleDAO ulrdao = new UserLoginRoleDAOImpl();
        
        ulrdao.updateRole(this);
    }
    
    
    public UserLoginRole getUserRole(){
        
                
        UserLoginRoleDAO ulrdao = new UserLoginRoleDAOImpl();
        
        UserLoginRole returnValue=ulrdao.getUserRole(this.username);
        
        return returnValue;
    }
    
    /**
     * Return all the roles for each
     * user in the database
     * @return 
     */
    public static ArrayList<UserLoginRole> getUserRoles(){
                
        UserLoginRoleDAO ulrdao = new UserLoginRoleDAOImpl();
        
        ArrayList<UserLoginRole> returnValue=ulrdao.getUserRoles();
        
        return returnValue;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename the rolename to set
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    
    
}
