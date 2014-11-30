/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.helper.Property;
import edu.ilstu.model.OnlineClassModel;
import edu.ilstu.model.RoomParticipantModel;
import edu.ilstu.model.ScheduleClassModel;
import edu.ilstu.model.StudentModel;
import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author kawibi
 */
@ManagedBean
@RequestScoped
public class OnlineClassesController {

    private RoomParticipantModel roomParticipant;
    private ScheduleClassModel scheduleClassModel;
    private OnlineClassModel onlineClassModel;
    private StudentModel studentModel;
    private TeacherModel teacherModel;
    private UserModel userModel;
    private int userId;
    private boolean teacher;
    private boolean showClass;
    private boolean showConnectButton;
    private ArrayList<RoomParticipantModel> classEnrollmentList;
    private ArrayList<OnlineClassModel> classList; //may need another list for professor

    private ArrayList<OnlineClassModel> teacherClassList;
    private ArrayList<ScheduleClassModel> teacherSchedule;
    private String webrtcURL;
    private int counter;

    /**
     * Creates a new instance of OnlineCalssesController
     */
    public OnlineClassesController() {
        roomParticipant = new RoomParticipantModel();
        onlineClassModel = new OnlineClassModel();
        studentModel = new StudentModel();
        teacherModel = new TeacherModel();
        scheduleClassModel = new ScheduleClassModel();
        teacherClassList = new ArrayList<>();
        classList = new ArrayList<>();
        classEnrollmentList = new ArrayList<>();
        teacherSchedule = new ArrayList<>();

    }

    private void loadStudentClasses() {

        System.out.println("student Load");
        roomParticipant.setStudentId(userId);
        setClassEnrollmentList(roomParticipant.gelEnrolClassesForStudent());

        for (Iterator<RoomParticipantModel> i = classEnrollmentList.iterator(); i.hasNext();) {
            roomParticipant = i.next();
            onlineClassModel.setOnlineClassId(roomParticipant.getOnlineClassId());
            classList.add(onlineClassModel.getAClass());
            //modifyclass

        }

    }

    private void loadteacherClasses() {

        //get the online classe id 
        onlineClassModel.setTeacherId(userId);
        //get all schedule for a specific teacher
        for (Iterator<OnlineClassModel> i = (onlineClassModel.getClassesByTeacherID()).iterator(); i.hasNext();) {

            OnlineClassModel ocm = i.next();
            getTeacherClassList().add(ocm);
            //modifyclass

        }

    }

    public void checkUserType() {

        if (teacherModel.getTeacherById(userId) != null) {
            System.out.println("teacher");
            teacher = true;
            userModel = teacherModel.getTeacherById(userId);
            loadteacherClasses();

        } else {
            System.out.println("student");
            teacher = false;
            userModel = studentModel.getStudentById(userId);
            loadStudentClasses();
        }

    }

    public void onRowEdit(RowEditEvent event) {

    }

    public void onRowCancel(RowEditEvent event) {

    }

    /**
     * @return the roomParticipant
     */
    public RoomParticipantModel getRoomParticipant() {
        return roomParticipant;
    }

    /**
     * @param roomParticipant the roomParticipant to set
     */
    public void setRoomParticipant(RoomParticipantModel roomParticipant) {
        this.roomParticipant = roomParticipant;
    }

    /**
     * @return the onlineClassModel
     */
    public OnlineClassModel getOnlineClassModel() {
        return onlineClassModel;
    }

    /**
     * @param onlineClassModel the onlineClassModel to set
     */
    public void setOnlineClassModel(OnlineClassModel onlineClassModel) {
        this.onlineClassModel = onlineClassModel;
    }

    /**
     * @return the studentModel
     */
    public StudentModel getStudentModel() {
        return studentModel;
    }

    /**
     * @param studentModel the studentModel to set
     */
    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
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

    /**
     * @return the teacherModel
     */
    public TeacherModel getTeacherModel() {
        return teacherModel;
    }

    /**
     * @param teacherModel the teacherModel to set
     */
    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }

    /**
     * @return the userModel
     */
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * @param userModel the userModel to set
     */
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    /**
     * @return the teacher
     */
    public boolean isTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    /**
     * @return the scheduleClassModel
     */
    public ScheduleClassModel getScheduleClassModel() {
        return scheduleClassModel;
    }

    /**
     * @param scheduleClassModel the scheduleClassModel to set
     */
    public void setScheduleClassModel(ScheduleClassModel scheduleClassModel) {
        this.scheduleClassModel = scheduleClassModel;
    }

    /**
     * @return the classEnrollmentList
     */
    public ArrayList<RoomParticipantModel> getClassEnrollmentList() {
        return classEnrollmentList;
    }

    /**
     * @param classEnrollmentList the classEnrollmentList to set
     */
    public void setClassEnrollmentList(ArrayList<RoomParticipantModel> classEnrollmentList) {
        this.classEnrollmentList = classEnrollmentList;
    }

    /**
     * @return the teachingClassList
     */
    public ArrayList<OnlineClassModel> getClassList() {
        return classList;
    }

    /**
     * @param classList the teachingClassList to set
     */
    public void setClassList(ArrayList<OnlineClassModel> classList) {
        this.classList = classList;
    }

    /**
     * @return the teacherSchedule
     */
    public ArrayList<ScheduleClassModel> getTeacherSchedule() {
        return teacherSchedule;
    }

    /**
     * @param teacherSchedule the teacherSchedule to set
     */
    public void setTeacherSchedule(ArrayList<ScheduleClassModel> teacherSchedule) {
        this.teacherSchedule = teacherSchedule;
    }

    /**
     * @return the teacherClassList
     */
    public ArrayList<OnlineClassModel> getTeacherClassList() {
        return teacherClassList;
    }

    /**
     * @param teacherClassList the teacherClassList to set
     */
    public void setTeacherClassList(ArrayList<OnlineClassModel> teacherClassList) {
        this.teacherClassList = teacherClassList;
    }

    public void openWebrtcWindow(int roomId) throws IOException {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("http://54.187.74.210:9010/virtualClassTV.html?roomId=" + roomId + "&userId=" + userId);
    }

    /**
     * @return the webrtcURL
     */
    public String getWebrtcURL() {
        return Property.getWebRtcURL();
    }

    public boolean displayClass(int onlineClassID) {

        Calendar calendar = Calendar.getInstance();
        ScheduleClassModel scm = new ScheduleClassModel(onlineClassID);
        scm = scm.getScheduleByOnlineClassID();
        scm.setStart_Date(scm.getStartDate());
        scm.setEnd_Date(scm.getEndDate());
        scm.setStart_Time(scm.getStartTime());
        scm.setEnd_Time(scm.getEndTime());
        showClass = false;

        
        
        
        java.util.Date serverDate = ScheduleClassModel.utcToTimeZonecheck(calendar.getTime(), scm.getTzname());

        System.out.println(serverDate);
            System.out.println(scm.getStart_Time());
        if ((serverDate.before(scm.getEnd_Date())) || serverDate.equals(scm.getEnd_Date())) {

            showClass = true;
        }

        return showClass;
    }

    public boolean displayButton(int onlineClassID) {

        Calendar calendar = Calendar.getInstance();
        ScheduleClassModel scm = new ScheduleClassModel(onlineClassID);
        scm = scm.getScheduleByOnlineClassID();
        scm.setStart_Date(scm.getStartDate());
        scm.setEnd_Date(scm.getEndDate());
        scm.setStart_Time(scm.getStartTime());
        scm.setEnd_Time(scm.getEndTime());

        showConnectButton = false;

        java.util.Date serverDate = ScheduleClassModel.utcToTimeZonecheck(calendar.getTime(), scm.getTzname());

        if ((serverDate.before(scm.getEnd_Date())) || serverDate.equals(scm.getEnd_Date())) {
            DateFormat utcFormat = new SimpleDateFormat("HH:mm:ss");

            String dateAsString = utcFormat.format(serverDate);

            try {
                serverDate = utcFormat.parse(dateAsString);
            } catch (ParseException ex) {
                Logger.getLogger(OnlineClassesController.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(serverDate);
            System.out.println(scm.getStart_Time());

            if (serverDate.getTime() >= scm.getStart_Time().getTime() && serverDate.getTime() <= scm.getEnd_Time().getTime()) {

                showConnectButton = true;
            }

        }

        return showConnectButton;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        counter++;
    }
}
