/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.RoomModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface RoomDAO {
    
   public void addStudentToRoomModel(RoomModel room);
   
   public void deleteStudenRoomModelmRoom(RoomModel room);
   
   public ArrayList<RoomModel> findallStudentforRoom(int studentid);
   
public ArrayList<RoomModel> updateRoom(RoomModel room);
   
}
