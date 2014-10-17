/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

import edu.ilstu.dao.UserDAO;
import edu.ilstu.dao.UserDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author kawibi
 */
public abstract class UserModel implements Serializable {

    private int userid;
    private String fname;
    private String lname;
    private String securityq;
    private String securitya;
    private String email;
    private int phone;
    private String street;
    private String city;
    private String State;
    private int zipCode;
    private String country;
    private char is_a;
    private UserDAO udao;

    public UserModel() {

        udao = new UserDAOImpl();
    }

    public UserModel(int userid) {
        this.userid = userid;
    }

    public UserModel(String fname, String lname, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a) {
        this.fname = fname;
        this.lname = lname;
        this.securityq = securityq;
        this.securitya = securitya;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.State = state;
        this.zipCode = zipCode;
        this.country = country;
        this.is_a = is_a;
    }

    public UserModel(int userId, String fname, String lname, String securityq, String securitya, String email, String street, String city, String state, int zipCode, String country, int phone, char is_a) {

        this(fname, lname, securityq, securitya, email, street, city, state, zipCode, country, phone, is_a);
        this.userid = userId;
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

  

    

    /**
     * @return the securityq
     */
    public String getSecurityq() {
        return securityq;
    }

    /**
     * @param securityq the securityq to set
     */
    public void setSecurityq(String securityq) {
        this.securityq = securityq;
    }

    /**
     * @return the securitya
     */
    public String getSecuritya() {
        return securitya;
    }

    /**
     * @param securitya the securitya to set
     */
    public void setSecuritya(String securitya) {
        this.securitya = securitya;
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
     * @return the phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(int phone) {
        this.phone = phone;
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
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    public void setState(String State) {
        this.State = State;
    }

    /**
     * @return the zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public int saveUser() {

        return udao.createNewUser(this);

    }

    public void updateUser() {

        udao.updateUser(this);

    }

    public void deleteUser() {

        udao.deleteUser(this);

    }

    public ArrayList<UserModel> getAllUsers() {

        ArrayList<UserModel> userList = udao.getAllUsers();

        return userList;

    }

    public String userToJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("userid", this.userid);
        obj.put("fname", this.fname);
        obj.put("lname", this.lname);
        obj.put("securityq", this.securityq);
        obj.put("securitya", this.securitya);
        obj.put("email", this.email);
        obj.put("phone", this.phone);
        obj.put("street", this.street);
        obj.put("city", this.city);
        obj.put("state", this.State);
        obj.put("zipcode", this.zipCode);
        obj.put("country", this.userid);
        obj.put("type", String.valueOf(this.is_a));

        return obj.toJSONString();
    }
}
