/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.model.OnlineClassModel;
import edu.ilstu.model.RoomParticipantModel;
import edu.ilstu.model.StudentModel;
import edu.ilstu.model.UserModel;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kawibi
 */
@ManagedBean
@SessionScoped
public class RoomParticipant implements Serializable {

    private int roomid;
    private RoomParticipantModel rpm;
    private OnlineClassModel ocm;
    private StudentModel studentModel;
    private List<StudentModel> studentList;
    private List<String> selectedStudents;
    private List<StudentModel> studentList2;
    private List<String> selectedStudents2;

    /**
     * Creates a new instance of RoomParticipant
     */
    public RoomParticipant() {

        

    }

    public void init() {

        studentList2 = new ArrayList();
        
        setOcm(new OnlineClassModel());
        getOcm().setRoomid(roomid);
        setOcm(getOcm().getClassByRoomId());//get all the information for online Class

        rpm = new RoomParticipantModel();

        rpm.setOnlineClassId(ocm.getOnlineClassId());

        studentModel = new StudentModel();

        setStudentList(studentModel.getAllStudents());
        
        if (!rpm.getAllParticipantForRoom().isEmpty()) {
            for (Iterator<RoomParticipantModel> r = rpm.getAllParticipantForRoom().iterator(); r.hasNext();) {

                int id = r.next().getStudentId();

                for (Iterator<StudentModel> s = studentList.iterator(); s.hasNext();) {
                    StudentModel sm = s.next();

                    if (sm.getUserid() == id) {
                        studentList2.add(sm);
                        s.remove();
                    }
                }

            }
        }

    }

    /**
     * @return the roomid
     */
    public int getRoomid() {
        return roomid;
    }

    /**
     * @param roomid the roomid to set
     */
    public void setRoomid(int roomid) {
        this.roomid = roomid;

    }

    /**
     * @return the rpm
     */
    public RoomParticipantModel getRpm() {
        return rpm;
    }

    /**
     * @param rpm the rpm to set
     */
    public void setRpm(RoomParticipantModel rpm) {
        this.rpm = rpm;
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
     * @return the studentModels
     */
    public List<StudentModel> getStudentModels() {
        return getStudentList();
    }

    /**
     * @param studentModels the studentModels to set
     */
    public void setStudentModels(List<StudentModel> studentModels) {
        this.setStudentList(studentModels);
    }

    /**
     * @return the studentList
     */
    public List<StudentModel> getStudentList() {
        return studentList;
    }

    /**
     * @param studentList the studentList to set
     */
    public void setStudentList(List<StudentModel> studentList) {
        this.studentList = studentList;
    }

    /**
     * @return the selectedStudents
     */
    public List<String> getSelectedStudents() {
        return selectedStudents;
    }

    /**
     * @param selectedStudents the selectedStudents to set
     */
    public void setSelectedStudents(List<String> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    /**
     * @return the studentList2
     */
    public List<StudentModel> getStudentList2() {
        return studentList2;
    }

    /**
     * @param studentList2 the studentList2 to set
     */
    public void setStudentList2(List<StudentModel> studentList2) {
        this.studentList2 = studentList2;
    }

    /**
     * @return the selectedStudents2
     */
    public List<String> getSelectedStudents2() {
        return selectedStudents2;
    }

    /**
     * @param selectedStudents2 the selectedStudents2 to set
     */
    public void setSelectedStudents2(List<String> selectedStudents2) {
        this.selectedStudents2 = selectedStudents2;
    }

    public void addStudentToClass() {

        for (Iterator<String> i = selectedStudents.iterator(); i.hasNext();) {

            StudentModel sm = new StudentModel();
            int id = Integer.parseInt(i.next());

            sm = sm.getStudentById(id);
            studentList2.add(sm);

            for (Iterator<StudentModel> j = studentList.iterator(); j.hasNext();) {
                if (j.next().getUserid() == id) {
                    j.remove();
                }
            }

        }

    }

    public void removeStudentFromClass() {

        for (Iterator<String> i = selectedStudents2.iterator(); i.hasNext();) {

            StudentModel sm = new StudentModel();
            int id = Integer.parseInt(i.next());

            sm = sm.getStudentById(id);
            studentList.add(sm);

            for (Iterator<StudentModel> j = studentList2.iterator(); j.hasNext();) {
                if (j.next().getUserid() == id) {
                    j.remove();
                }
            }

        }

    }

    public void addAll() {

        for (Iterator<StudentModel> i = studentList.iterator(); i.hasNext();) {

            studentList2.add(i.next());
            i.remove();

        }

    }

    public void removeAll() {
        for (Iterator<StudentModel> i = studentList2.iterator(); i.hasNext();) {

            studentList.add(i.next());
            i.remove();

        }
    }

    public String save() {

        if (!studentList2.isEmpty()) {

            for (Iterator<StudentModel> i = studentList2.iterator(); i.hasNext();) {
                rpm = new RoomParticipantModel();
                rpm.setOnlineClassId(ocm.getOnlineClassId());
                rpm.setStudentId(i.next().getUserid());
                rpm.addParticipant();

            }

        }

        studentList.clear();
        studentList2.clear();

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/index.xhtml?faces-redirect=true";
    }

}
