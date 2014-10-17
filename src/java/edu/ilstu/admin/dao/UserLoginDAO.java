/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.admin.dao;

import edu.ilstu.admin.UserLogin;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface UserLoginDAO {
    
    public int createLogin(UserLogin userLogin);
    
    public void updateLogin(UserLogin userLogin);
    
    public void deleteLogin(UserLogin userlogin);
    
    public UserLogin getLogin(String username);
    
    public UserLogin getLogin(int userId);
    
    public ArrayList<UserLogin> getLogins();
    
}
