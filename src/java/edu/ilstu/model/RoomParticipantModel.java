/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

/**
 *
 * @author kawibi
 */
public class RoomParticipantModel {
 
    private int onlineClassId;
    private int studentId;
    
    
    public RoomParticipantModel(){}
    
    public RoomParticipantModel(int onlineClassId, int studentId){
        this.onlineClassId=onlineClassId;
        this.studentId=studentId;
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
     * @return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    
    
}
