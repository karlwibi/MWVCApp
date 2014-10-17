/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.admin;

import edu.ilstu.admin.dao.RoleDAO;
import edu.ilstu.admin.dao.RoleDAOImpl;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class Role {
    
    private String rolename;
    
    
    public Role(){}
    
    public Role(String role){
        this.rolename=role;
    }
    
    
    public int addRole(){
        
        RoleDAO rdao= new RoleDAOImpl();
        
        int returnValue=rdao.addRole(this);
        
        return returnValue;
    }
    
    public void deleteRole(){
         RoleDAO rdao= new RoleDAOImpl();
         
         rdao.deleteRole(this);
    }
    
       
    public ArrayList<Role> getRoles(){
        
        ArrayList<Role> list=null;
        
        return list;
    }

    /**
     * @return the role
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename the role to set
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    
    
}
