/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.RoomParticipantModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface RoomParticipantDAO {

    /**
     * 
     * @param room 
     */
    public void addStudentToRoomModel(RoomParticipantModel room);

    /**
     * 
     * @param room 
     */
    public void deleteStudenRoomModelmRoom(RoomParticipantModel room);

    /**
     * 
     * @param roomid
     * @return 
     */
    public ArrayList<RoomParticipantModel> findallStudentforRoom(int roomid);

    
    /**
     * 
     * @param room
     * @return 
     */
    public ArrayList<RoomParticipantModel> updateRoom(RoomParticipantModel room);

}
