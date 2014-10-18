/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.ScheduleClassModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface ScheduleClassDAO {

    /**
     *
     * @param scheduleClass
     * @return
     */
    public int createSchedule(ScheduleClassModel scheduleClass);

    /**
     *
     * @param scheduleClassId
     * @return
     */
    public ScheduleClassModel getScheduleClassById(int scheduleClassId);

    /**
     *
     * @param teacherId
     * @return
     */
    public ArrayList<ScheduleClassModel> findScheduleByTeacherId(int teacherId);

    /**
     *
     * @param onlineClassId
     * @return
     */
    public ScheduleClassModel findScheduleByOnlineClassId(int onlineClassId);

    public ScheduleClassModel findScheduleByScheduleId(int scheduleId);

    /**
     *
     * @param scheduleClass
     */
    public void updateSchedule(ScheduleClassModel scheduleClass);

    /**
     *
     * @param scheduleClass
     */
    public void deleteSchedule(ScheduleClassModel scheduleClass);

    public ArrayList<ScheduleClassModel> getAllSchedules();
}
