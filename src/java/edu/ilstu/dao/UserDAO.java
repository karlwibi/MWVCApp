/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.UserModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface UserDAO {
    
    public int createNewUser(UserModel aUser);
    
    public void updateUser(UserModel aUser);
    
    public ArrayList<UserModel> getAllUsers();
    
    public UserModel getUserBy(int userId);
    
    public void deleteUser(UserModel userModel);
    
            
    
}
