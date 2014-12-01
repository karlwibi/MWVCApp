/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.admin.UserLogin;
import edu.ilstu.dao.UserDAO;
import edu.ilstu.dao.UserDAOImpl;
import edu.ilstu.model.StudentModel;
import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kawibi
 */
@ManagedBean
@RequestScoped
public class UserController {

    private int userId;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserModel aUserModel;
    

    /**
     * Creates a new instance of UserController
     */
    public UserController() {

    }

    public void _init(){
        
        UserDAO udao= new UserDAOImpl();
        UserModel um= udao.getUserBy(userId);
        UserLogin ul=new UserLogin();
        ul.setUserId(userId);
        ul=ul.getLoginById();
        
        firstName=um.getFname();
        lastName=um.getLname();
        username=ul.getUsername();
        password=ul.getPassword();
        email=um.getEmail();
        
    }
    
    public void update() {
        UserDAO udao= new UserDAOImpl();
        UserModel um= udao.getUserBy(userId);
        UserLogin ul=new UserLogin();
        ul.setUserId(userId);
        ul=ul.getLoginById();
        
        um.setFname(firstName);
        um.setLname(lastName);
        um.setEmail(email);
        
        ul.setPassword(password);
        
        um.updateUser();
        ul.updateLogin();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "your profile was updated!"));
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

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    
}
