/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.admin.UserLogin;
import edu.ilstu.admin.UserLoginRole;
import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author kawibi
 */
@ManagedBean
@RequestScoped
public class AdminController {

   
    
    private String username;
    private String lastname;
    private String firstname;
    private String password;
    private String email;
    private String role;
    private ArrayList<UserLoginRole> list;
    
         
    private final static String[] roles;
     
    static {
        
        roles = new String[2];
        roles[0] = "admin";
        roles[1] = "teacher";
        
    }
    
    /**
     * Creates a new instance of loginAdmin
     */
    public AdminController() {
        
        list=UserLoginRole.getUserRoles();
    }
    
    
    public void createUser(){
     
        UserLogin ul=null;
        UserLoginRole ulr=null;
        TeacherModel tm= new TeacherModel();
        
        
        tm.setFname(firstname);
        tm.setLname(lastname);
        tm.setEmail(email);
        tm.setIs_a('t');
        
        ul=new UserLogin(username, password, tm.saveUser());
               
        ul.createLogin();
        
        ulr=new UserLoginRole(username,role);
        ulr.setUserRole();
        
    }

    public void reset(){
        this.email="";
        this.firstname="";
        this.lastname="";
        this.password="";
        this.username="";
        this.role="";
    }
    
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("user Role Edited for", ((UserLoginRole) event.getObject()).getUsername());
        
        UserLoginRole ulr=((UserLoginRole) event.getObject());
        ulr.updateRole();
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Following user was removed", ((UserLoginRole) event.getObject()).getUsername());
        
        UserLogin ul=new UserLogin();
        ul.setUsername(((UserLoginRole) event.getObject()).getUsername());
        ul.deleteLogin();
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowRemove(RowEditEvent event) {
       // FacesMessage msg = new FacesMessage("Edit Cancelled", ((UserLoginRole) event.getObject()).getUsername());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
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
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the list
     */
    public ArrayList getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList list) {
        this.list = list;
    }
    
   public List<String> getRoles() {
        return Arrays.asList(roles);
    } 
    
}
