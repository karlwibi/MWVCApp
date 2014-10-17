/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.admin.dao;

import edu.ilstu.admin.Role;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface RoleDAO {
 
    public int addRole(Role role);
    
      
    public void deleteRole(Role role);
    
    public ArrayList<Role> getRoles();
    
}
