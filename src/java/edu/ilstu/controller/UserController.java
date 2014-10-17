/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.model.StudentModel;
import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserModel aUserModel;
    private char is_a;
    private int id;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {

        if (!LoginController.isIsLogin()) {

            FacesContext faces = FacesContext.getCurrentInstance();
            ExternalContext context = faces.getExternalContext();

            HttpServletRequest req=(HttpServletRequest) context.getRequest();            
            
            String originalURI = req.getRequestURL().toString();

            if (originalURI == null) {
                originalURI = req.getRequestURI();
            }

            LoginController.setUrl(originalURI);

            try {
                context.redirect("faces/login.xhtml");
//            HttpServletResponse response = (HttpServletResponse) context.getResponse();
//            try {
//                response.sendRedirect("/login.xhtml");
//                faces.responseComplete(); // need this or will get "Cannot forward after response has been committed"
//                // exception. It bypasses the Render Response phase
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String submit() {
        if (getIs_a() == 't') {
            System.out.println("it's a teacher");
            aUserModel = new TeacherModel();
        } else {
            aUserModel = new StudentModel();
        }

        aUserModel.setEmail(getEmail());
        //remember to set the username there
        //remember to set the password there
        aUserModel.setIs_a(is_a);

        id = aUserModel.saveUser();

//        UserDAO udao = new UserDAOImpl();
//        udao.createNewUser(aUserModel);
        return "onlineClass.xhtml?faces-redirect=true&teacherId=" + id;
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
