/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

import edu.ilstu.dao.ScheduleClassDAO;
import edu.ilstu.dao.ScheduleClassDAOImpl;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kawibi
 */
public class ScheduleClassModel implements Serializable {

    private int scheduleClassId;
    private int onlineClassId;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private java.util.Date start_Date;
    private java.util.Date end_Date;
    private java.util.Date start_Time;
    private java.util.Date end_Time;
    private String tzname;
    //private ScheduleClassDAO scdao;

    public ScheduleClassModel() {

    }

    public ScheduleClassModel(int onlineClassId) {

        this.onlineClassId = onlineClassId;
    }

    public ScheduleClassModel(int onlineClassId, Date startDate, Date endDate) {

        this.startDate = startDate;
        this.endDate = endDate;

    }

    public ScheduleClassModel(int onlineClassId, Date startDate, Date endDate, Time startTime, Time endTime) {
        this(onlineClassId, startDate, endDate);
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public ScheduleClassModel(int scheduleClassId, int onlineClassId, Date startDate, Date endDate, Time startTime, Time endTime) {
        this(onlineClassId, startDate, endDate, startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleClassId = scheduleClassId;

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

    /**
     * @return the scheduleClassId
     */
    public int getScheduleClassId() {
        return scheduleClassId;
    }

    /**
     * @param scheduleClassId the scheduleClassId to set
     */
    public void setScheduleClassId(int scheduleClassId) {
        this.scheduleClassId = scheduleClassId;
    }

    public void saveSchedule() {
        ScheduleClassDAO scdao = new ScheduleClassDAOImpl();
        scdao.createSchedule(this);

    }

    public void updateSchedule() {
        ScheduleClassDAO scdao = new ScheduleClassDAOImpl();
        scdao.updateSchedule(this);
    }

    public void deleteSchedule() {
        ScheduleClassDAO scdao = new ScheduleClassDAOImpl();
        scdao.deleteSchedule(this);
    }

    public ArrayList<ScheduleClassModel> getAllSchedules() {
        ScheduleClassDAO scdao = new ScheduleClassDAOImpl();

        ArrayList<ScheduleClassModel> list = scdao.getAllSchedules();

        return list;
    }

    /**
     * Find Schedule class by online class iD
     *
     * @return ScheduleClassModel
     */
    public ScheduleClassModel getScheduleByOnlineClassID() {

        ScheduleClassDAO scdao = new ScheduleClassDAOImpl();
        ScheduleClassModel scm = scdao.findScheduleByOnlineClassId(this.getOnlineClassId());

        return scm;
    }

    /**
     * find Schedule class by ID
     *
     * @return
     */
    public ScheduleClassModel getASchedule() {

        ScheduleClassDAO scdao = new ScheduleClassDAOImpl();

        ScheduleClassModel scm = scdao.findScheduleByScheduleId(this.scheduleClassId);

        return scm;
    }

    /**
     * Use java util date
     *
     * @return the start_Date
     */
    public java.util.Date getStart_Date() {
        return startDate;
    }

    /**
     * Use java util date
     *
     * @param start_Date the start_Date to set
     */
    public void setStart_Date(java.util.Date start_Date) {
        this.start_Date = start_Date;
        if (this.start_Date != null) {
            setStartDate(new java.sql.Date(this.start_Date.getTime()));
        }
    }

    /**
     * Use java util date
     *
     * @return the end_Date
     */
    public java.util.Date getEnd_Date() {
        return endDate;
    }

    /**
     * Use java util date
     *
     * @param end_Date the end_Date to set
     */
    public void setEnd_Date(java.util.Date end_Date) {
        this.end_Date = end_Date;
        if (this.end_Date != null) {
            setEndDate(new java.sql.Date(this.end_Date.getTime()));
        }
    }

    /**
     * Use java util date
     *
     * @return the start_Time
     */
    public java.util.Date getStart_Time() {
        return startTime;
    }

    /**
     * Use java util date
     *
     * @param start_Time the start_Time to set
     */
    public void setStart_Time(java.util.Date start_Time) {
        this.start_Time = start_Time;
        if (this.start_Time != null) {
            setStartTime(new java.sql.Time(new java.sql.Date(this.start_Time.getTime()).getTime()));
        }
    }

    /**
     * Use java util date
     *
     * @return the end_Time
     */
    public java.util.Date getEnd_Time() {
        return endTime;
    }

    /**
     * Use java util date
     *
     * @param end_Time the end_Time to set
     */
    public void setEnd_Time(java.util.Date end_Time) {
        this.end_Time = end_Time;
        if (this.end_Time != null) {
            setEndTime(new java.sql.Time(new java.sql.Date(this.end_Time.getTime()).getTime()));
        }
    }

    /**
     * @return the tzname
     */
    public String getTzname() {
        return tzname;
    }

    /**
     * @param tzname the tzname to set
     */
    public void setTzname(String tzname) {
        this.tzname = tzname;
    }

    public static java.util.Date utcToTimeZonecheck(java.util.Date serverUtcTime, String timeZone) {

        // Construct FROM and TO TimeZone instances
        TimeZone fromTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone toTimeZone = TimeZone.getTimeZone(timeZone);

// Get a Calendar instance using the default time zone and locale.
        Calendar calendar = Calendar.getInstance();
 // Set the calendar's time with the given date
        calendar.setTimeZone(fromTimeZone);
        calendar.setTime(serverUtcTime);

        System.out.println("Input: " + calendar.getTime() + " in " + fromTimeZone.getDisplayName());

        // FROM TimeZone to UTC
        calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);

        if (fromTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
        }

// UTC to TO TimeZone
        calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());

        if (toTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
        }

        return calendar.getTime();

    }

}
