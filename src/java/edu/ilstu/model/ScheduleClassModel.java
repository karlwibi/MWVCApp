/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author kawibi
 */
public class ScheduleClassModel {
    
    private int teacherId;
    private int onlineClassId;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    
    
    public ScheduleClassModel(){}
    
    public ScheduleClassModel(int teacherId, int onlineClassId){
         
     this.teacherId=teacherId;
     this.onlineClassId=onlineClassId;
    }
    
    public ScheduleClassModel(int teacherId, int onlineClassId, Date startDate, Date endDate){
         this(teacherId,onlineClassId);
         this.startDate=startDate;
         this.endDate=endDate;
        
    }
    public ScheduleClassModel(int teacherId, int onlineClassId, Date startDate, Date endDate, Time startTime, Time endTime){
         this(teacherId,onlineClassId,startDate,endDate);
         this.startTime=startTime;
         this.endTime=endTime;
        
    }

    /**
     * @return the teacherId
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return the onlineClassId
     */
    public int getOnlineClassId() {
        return onlineClassId;
    }

    /**
     * @param onlineClassId the onlineClassId to set
     */
    public void setOnlineClassId(int onlineClassId) {
        this.onlineClassId = onlineClassId;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the startTime
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
        
    
}
