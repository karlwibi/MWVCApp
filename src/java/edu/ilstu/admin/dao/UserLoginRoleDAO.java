/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.admin.dao;

import edu.ilstu.admin.UserLoginRole;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface UserLoginRoleDAO {
    
    public int setUserRole(UserLoginRole userLoginRole);
    
    public void updateRole (UserLoginRole userLoginRole);
    
    public UserLoginRole getUserRole(String userName);
    
    public ArrayList<UserLoginRole> getUserRoles();
    
}
