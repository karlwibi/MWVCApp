/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.admin;

import edu.ilstu.admin.dao.UserLoginDAO;
import edu.ilstu.admin.dao.UserLoginDAOImpl;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class UserLogin {

    private String username;
    private String password;
    private int userId;

    public UserLogin() {
    }

    public UserLogin(String username, String password, int userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public int createLogin() {

        UserLoginDAO uldao = new UserLoginDAOImpl();

        int returnValue = uldao.createLogin(this);

        return returnValue;
    }

    public void updateLogin() {

        UserLoginDAO uldao = new UserLoginDAOImpl();

        uldao.updateLogin(this);

    }

    public void deleteLogin() {

        UserLoginDAO uldao = new UserLoginDAOImpl();

        uldao.deleteLogin(this);

    }

    public UserLogin getLogin() {

        UserLoginDAO uldao = new UserLoginDAOImpl();

        UserLogin returnValue = uldao.getLogin(this.username);

        return returnValue;

    }

    /**
     * Return all the logins in the UserLogin
     * Table
     * @return 
     */
    public static ArrayList<UserLogin> getLogins() {

        UserLoginDAO uldao = new UserLoginDAOImpl();

        ArrayList<UserLogin> returnValue = uldao.getLogins();

        return returnValue;

    }

    public UserLogin getLoginById() {

        UserLoginDAO uldao = new UserLoginDAOImpl();

        UserLogin returnValue = uldao.getLogin(this.userId);

        return returnValue;

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

}
