/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import edu.ilstu.dao.RoomParticipantDAO;
import edu.ilstu.dao.RoomParticipantDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author kawibi
 */
public class RoomParticipantModel implements Serializable{
 
    private int onlineClassId;
    private int studentId;
    private RoomParticipantDAO rpdao;
    
    
    public RoomParticipantModel(){
    
        rpdao=new RoomParticipantDAOImpl();
    }
    
    public RoomParticipantModel(int onlineClassId, int studentId){
        this();
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
    
    
    public void addParticipant(){
        
        rpdao.addStudentToRoomModel(this);
    }
    
    public void deleteParticipant(){
        
        rpdao.deleteStudenRoomModelmRoom(this);
    }
    
    public ArrayList<RoomParticipantModel> getAllParticipantForRoom(){
        
     
        ArrayList<RoomParticipantModel> list=rpdao.findallStudentforRoom(this.onlineClassId);
        
        return list;
    }
    
    public ArrayList<RoomParticipantModel> gelEnrolClassesForStudent(){
         
        ArrayList<RoomParticipantModel> list=rpdao.findallEnrollClasses(this.studentId);
        
        return list;
        
    }
    
     public String roomToJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("onlineClassId", this.onlineClassId);
        obj.put("StudentID", this.studentId);
        
        return obj.toJSONString();
    }
}
