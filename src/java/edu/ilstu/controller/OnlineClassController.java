/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.controller;


import edu.ilstu.dao.OnlineClassDAO;
import edu.ilstu.dao.OnlineClassDAOImpl;
import edu.ilstu.dao.ScheduleClassDAO;
import edu.ilstu.dao.ScheduleClassDAOImpl;
import edu.ilstu.model.OnlineClassModel;
import edu.ilstu.model.ScheduleClassModel;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kawibi
 */
@ManagedBean
@RequestScoped
public class OnlineClassController {

    
    private OnlineClassModel ocm;
    private ScheduleClassModel scm;
    private java.util.Date startDate;
    private java.util.Date endDate;
    private java.util.Date startTime;
    private java.util.Date endTime;
    //private DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
    
    
    /**
     * Creates a new instance of OnlineClassController
     */
    public OnlineClassController() {
        ocm=new OnlineClassModel();
        scm=new ScheduleClassModel();
    }

    /**
     * @return the ocm
     */
    public OnlineClassModel getOcm() {
        return ocm;
    }

    /**
     * @param ocm the ocm to set
     */
    public void setOcm(OnlineClassModel ocm) {
        this.ocm = ocm;
    }

    /**
     * @return the scm
     */
    public ScheduleClassModel getScm() {
        return scm;
    }

    /**
     * @param scm the scm to set
     */
    public void setScm(ScheduleClassModel scm) {
        this.scm = scm;
    }

   
    
    public void addClass(){
        int identity=0;
                
        //inserting data into the onliclass table
        identity=ocm.saveClass();
        
        scm.setOnlineClassId(identity);
        scm.setTeacherId(1);
        scm.setStartDate(new java.sql.Date(startDate.getTime()));

        scm.setEndDate(new java.sql.Date(endDate.getTime()));
      //change the object date from java.util.date to java.sql.date before setting the date into scheduleClassModel
        scm.setStartTime(new java.sql.Time(new java.sql.Date(startTime.getTime()).getTime()));
        scm.setEndTime(new java.sql.Time(new java.sql.Date(getEndTime().getTime()).getTime()));
        
        //inserting data into the scheduleclass table
        scm.saveSchedule();
       
        
    }

    /**
     * @return the StartDate
     */
    public java.util.Date getStartDate() {
        return startDate;
    }

    /**
     * @param StartDate the StartDate to set
     */
    public void setStartDate(java.util.Date StartDate) {
        this.startDate = StartDate;
    }

    /**
     * @return the endDate
     */
    public java.util.Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the startTime
     */
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }
    
    
    
}
