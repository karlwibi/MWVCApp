/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.dao.StudentDAO;
import edu.ilstu.dao.StudentDAOImpl;
import edu.ilstu.dao.TeacherDAO;
import edu.ilstu.dao.TeacherDAOImpl;
import edu.ilstu.dao.UserDAO;
import edu.ilstu.dao.UserDAOImpl;
import edu.ilstu.model.StudentModel;
import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kawibi
 */
@ManagedBean
@RequestScoped
public class UserController {
    
    private String email;
    private String username;
    private String password;
    private UserModel aUserModel;
    private char is_a;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
        
    }
    
    public void submit() {
        if (getIs_a() == 't') {
            System.out.println("it's a teacher");
            aUserModel = new TeacherModel();
        } else {
            aUserModel = new StudentModel();
        }
        
        aUserModel.setEmail(getEmail());
        aUserModel.setUsername(getUsername());
        aUserModel.setPassword(getPassword());
        aUserModel.setIs_a(is_a);

        UserDAO udao = new UserDAOImpl();
        udao.createNewUser(aUserModel);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the is_a
     */
    public char getIs_a() {
        return is_a;
    }

    /**
     * @param is_a the is_a to set
     */
    public void setIs_a(char is_a) {
        this.is_a = is_a;
    }

    /**
     * @return the aUserModel
     */
    public UserModel getaUserModel() {
        return aUserModel;
    }

    /**
     * @param aUserModel the aUserModel to set
     */
    public void setaUserModel(UserModel aUserModel) {
        this.aUserModel = aUserModel;
    }
    
}
