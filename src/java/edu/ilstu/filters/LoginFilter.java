/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.filters;

import edu.ilstu.admin.UserLogin;
import edu.ilstu.controller.LoginController;
import edu.ilstu.dao.UserDAO;
import edu.ilstu.dao.UserDAOImpl;
import edu.ilstu.helper.Property;
import edu.ilstu.model.UserModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kawibi
 */
public class LoginFilter implements Filter {

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

    private String username;
    private String password;
    private UserDAO udao = null;
    private UserLogin login = null;
    private static String url = null;
    private static UserModel user = null;
    private static boolean isLogin = false;
    private String message = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpreq = (HttpServletRequest) request;
        HttpServletResponse httpres = (HttpServletResponse) response;
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        String context = httpreq.getContextPath();
       
        
        
        
        Property.uploadWebRTCAppInfo();
        

        String requestURL = httpreq.getRequestURI();

        if (requestURL.equals("/MWVCApp/")) {

            setIsLogin(true);

            setLogin(new UserLogin());
            getLogin().setUsername(httpreq.getUserPrincipal().getName());

            if (getLogin().getLogin() != null) {
                setLogin(getLogin().getLogin());

                setUdao(new UserDAOImpl());

                setUser(getUdao().getUserBy(getLogin().getUserId()));

                if (getUrl() != null) {
                    //context.redirect(getUrl());
                } else {
                    httpres.sendRedirect(context + "/faces/protected/onlineClasses.xhtml?faces-redirect=true&userId=" + user.getUserid());
                    //context.redirect(context.getRequestContextPath()+"/faces/protected/onlineClasses.xhtml?userId="+user.getUserid());
                }
            } else {
                throw new ServletException("Username or password Not Correct!");
            }

        } else {
            chain.doFilter(request, response);
        }

    }

    
    
    @Override
    public void destroy() {

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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
