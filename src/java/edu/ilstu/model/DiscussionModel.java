/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author kawibi
 */
public class DiscussionModel implements Serializable {
    
    private int discussionId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private int createBy;
    
    private int onlineClassId;
    
    public DiscussionModel(){}
    
    public DiscussionModel(int discussionId){
        this.discussionId=discussionId;
    }
    
    
     public DiscussionModel(String title, String description, Date startDate, Date endDate, Time startTime, Time endTime, int createBy){
        this.title=title;
        this.description=description;
        this.startDate=startDate;
        this.endDate=endDate;
        this.startTime=startTime;
        this.endTime=endTime;
        this.createBy=createBy;
     }
    
    
    public DiscussionModel(int discussionid,String title, String description, Date startDate, Date endDate, Time startTime, Time endTime, int createBy, int onlinceClassId){
        this(title,description, startDate, endDate, startTime, endTime, createBy);
        this.discussionId=discussionid;
        this.onlineClassId=onlinceClassId;
        
     }

    /**
     * @return the discussionId
     */
    public int getDiscussionId() {
        return discussionId;
    }

    /**
     * @param discussionId the discussionId to set
     */
    public void setDiscussionId(int discussionId) {
        this.discussionId = discussionId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     * @return the createBy
     */
    public int getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy the createBy to set
     */
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
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
    
    
    
    
    
    
    
}
