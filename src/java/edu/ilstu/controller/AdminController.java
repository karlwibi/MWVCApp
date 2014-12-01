/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import com.jcabi.aspects.Async;
import edu.ilstu.admin.UserLogin;
import edu.ilstu.admin.UserLoginRole;
import edu.ilstu.dao.UserDAO;
import edu.ilstu.dao.UserDAOImpl;
import edu.ilstu.helper.Property;
import edu.ilstu.mail.Email;
import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.extensions.model.layout.LayoutOptions;

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
    
    private String savePath;
    private String applicationLink;
    private String webRTCUrl;
    private String smtpServer;
    private String smtpPort;
    private String smtpUsername;
    private String smtpPassword;
    private LayoutOptions layoutOptions; 
    
     
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
    
    @PostConstruct  
    protected void initialize() {  
        setLayoutOptions(new LayoutOptions());  
  
        // options for all panes  
        LayoutOptions panes = new LayoutOptions();  
        panes.addOption("slidable", false);  
        panes.addOption("resizeWhileDragging", false);  
        getLayoutOptions().setPanesOptions(panes);  
  
        // options for center pane  
        LayoutOptions center = new LayoutOptions();  
        getLayoutOptions().setCenterOptions(center);  
  
        // options for nested center layout  
        LayoutOptions childCenterOptions = new LayoutOptions();  
        center.setChildOptions(childCenterOptions);  
  
        // options for center-north pane  
        LayoutOptions centerNorth = new LayoutOptions();  
        centerNorth.addOption("size", "50%");  
        childCenterOptions.setNorthOptions(centerNorth);  
  
        // options for center-center pane  
        LayoutOptions centerCenter = new LayoutOptions();  
        centerCenter.addOption("minHeight", 60);  
        childCenterOptions.setCenterOptions(centerCenter);  
  
        // options for west pane  
        LayoutOptions west = new LayoutOptions();  
        west.addOption("size", 400);  
        getLayoutOptions().setWestOptions(west);  
  
        // options for nested west layout  
        LayoutOptions childWestOptions = new LayoutOptions();  
        west.setChildOptions(childWestOptions);  
  
        // options for west-north pane  
        LayoutOptions westNorth = new LayoutOptions();  
        westNorth.addOption("size", "33%");  
        childWestOptions.setNorthOptions(westNorth);  
  
        // options for west-center pane  
        LayoutOptions westCenter = new LayoutOptions();  
        westCenter.addOption("minHeight", "60");  
        childWestOptions.setCenterOptions(westCenter);  
  
        // options for west-south pane  
        LayoutOptions westSouth = new LayoutOptions();  
        westSouth.addOption("size", "33%");  
        childWestOptions.setSouthOptions(westSouth);  
  
        // options for east pane  
        LayoutOptions east = new LayoutOptions();  
        east.addOption("size", 300);  
        getLayoutOptions().setEastOptions(east);  
  
        // options for south pane  
        LayoutOptions south = new LayoutOptions();  
        south.addOption("size", 80);  
        getLayoutOptions().setSouthOptions(south);  
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
        
        if(tm.getEmail()!=null){
            sendMessage(username, username, password, email);
        }
        
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
//        FacesMessage msg = new FacesMessage("Following user was removed", ((UserLoginRole) event.getObject()).getUsername());
//        
//        UserLogin ul=new UserLogin();
//        ul.setUsername(((UserLoginRole) event.getObject()).getUsername());
//        ul.deleteLogin();
//        
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowRemove(String username) {
       // FacesMessage msg = new FacesMessage("Edit Cancelled", ((UserLoginRole) event.getObject()).getUsername());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        
        
        FacesMessage msg = new FacesMessage("Following user was removed", username);
        
        UserLogin ul=new UserLogin();
        ul.setUsername(username);
        ul=ul.getLogin();
        
        UserDAO udao= new UserDAOImpl();
        UserModel um=udao.getUserBy(ul.getUserId());
        
        um.deleteUser();
        //ul.deleteLogin();
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
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

    /**
     * @return the savePath
     */
    public String getSavePath() {
        
        return Property.getSavePath();
    }

    /**
     * @param savePath the savePath to set
     */
    public void setSavePath(String savePath) {
        Property.setSavePath(savePath);
//        this.savePath = savePath;
    }

    /**
     * @return the applicationLink
     */
    public String getApplicationLink() {
        return Property.getApplicationLink();
    }

    /**
     * @param applicationLink the applicationLink to set
     */
    public void setApplicationLink(String applicationLink) {
        //this.applicationLink = applicationLink;
        Property.setApplicationLink(applicationLink);
    }

    /**
     * @return the webRTCUrl
     */
    public String getWebRTCUrl() {
        return Property.getWebRtcURL();
    }

    /**
     * @param webRTCUrl the webRTCUrl to set
     */
    public void setWebRTCUrl(String webRTCUrl) {
        this.webRTCUrl = webRTCUrl;
        Property.setWebRtcURL(this.webRTCUrl);
    }

    /**
     * @return the smtpServer
     */
    public String getSmtpServer() {
        return Property.getSmtpServer();
    }

    /**
     * @param smtpServer the smtpServer to set
     */
    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
        Property.setSmtpServer(this.smtpServer);
    }

    /**
     * @return the smtpPort
     */
    public String getSmtpPort() {
        return Property.getSmtpPort();
    }

    /**
     * @param smtpPort the smtpPort to set
     */
    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
        Property.setSmtpPort( this.smtpPort);
    }

    /**
     * @return the smtpUsername
     */
    public String getSmtpUsername() {
        return Property.getSmtpUsername();
    }

    /**
     * @param smtpUsername the smtpUsername to set
     */
    public void setSmtpUsername(String smtpUsername) {
        this.smtpUsername = smtpUsername;
        Property.setSmtpUsername(this.smtpUsername);
    }

    /**
     * @return the smtpPassword
     */
    public String getSmtpPassword() {
        return Property.getSmtpPassword();
    }

    /**
     * @param smtpPassword the smtpPassword to set
     */
    public void setSmtpPassword(String smtpPassword) {
       this.smtpPassword = smtpPassword;
        Property.setSmtpPassword( this.smtpPassword);
    }

    /**
     * @return the layoutOptions
     */
    public LayoutOptions getLayoutOptions() {
        return layoutOptions;
    }

    /**
     * @param layoutOptions the layoutOptions to set
     */
    public void setLayoutOptions(LayoutOptions layoutOptions) {
        this.layoutOptions = layoutOptions;
    }
 
    
    
    public void saveProperties(){
        
        Property.saveWebRTCAppInfo();
        
    }
    
    @Async
    private void sendMessage(String fname, String username, String password, String emailAddress) {
        Email email = new Email();
        email.sendCredentialMessageToTeacher(fname, username, password, emailAddress);

    }
}
