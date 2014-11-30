/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.admin.UserLogin;
import edu.ilstu.dao.UserDAO;
import edu.ilstu.dao.UserDAOImpl;
import edu.ilstu.model.UserModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kawibi
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private String username;
    private String password;
    private UserDAO udao = null;
    private UserLogin login = null;
    private static String url = null;
    private static UserModel user = null;
    private static boolean isLogin = false;
    private String message=null;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    public LoginController(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void signIn() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            if (request.getUserPrincipal() == null) {

                request.login(this.getUsername(), this.getPassword());

                setIsLogin(true);

                setLogin(new UserLogin());
                getLogin().setUsername(request.getUserPrincipal().getName());
                
             
                if(getLogin().getLogin()!=null){
                    setLogin(getLogin().getLogin());

                    setUdao(new UserDAOImpl());

                
                setUser(getUdao().getUserBy(getLogin().getUserId()));    
                }else{
                    throw new ServletException("Username or password Not Correct!");
                }
                
                
            }
        } catch (ServletException e) {

            facesContext.addMessage(null, new FacesMessage("Login failed."));
            try {
                setMessage(e.getMessage());
                context.redirect("errorLogin.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {

            if (getUrl() != null) {
                context.redirect(getUrl());
            } else {
                context.redirect(context.getRequestContextPath()+"/faces/protected/onlineClasses.xhtml?userId="+user.getUserid());
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void signOut() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        setIsLogin(false);
        setUrl(null);

        try {
            context.redirect(context.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
     * @return the udao
     */
    public UserDAO getUdao() {
        return udao;
    }

    /**
     * @param udao the udao to set
     */
    public void setUdao(UserDAO udao) {
        this.udao = udao;
    }

    /**
     * @return the login
     */
    public UserLogin getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(UserLogin login) {
        this.login = login;
    }

    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * @param aUrl the url to set
     */
    public static void setUrl(String aUrl) {
        url = aUrl;
    }

    /**
     * @return the user
     */
    public static UserModel getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(UserModel aUser) {
        user = aUser;
    }

    /**
     * @return the isLogin
     */
    public static boolean isIsLogin() {
        return isLogin;
    }

    /**
     * @param aIsLogin the isLogin to set
     */
    public static void setIsLogin(boolean aIsLogin) {
        isLogin = aIsLogin;
    }

   /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param aMessage the message to set
     */
    public void setMessage(String aMessage) {
        message = aMessage;
    }
    
}
