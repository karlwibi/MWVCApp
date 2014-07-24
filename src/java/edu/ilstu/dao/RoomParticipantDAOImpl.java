/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.RoomParticipantModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class RoomParticipantDAOImpl implements RoomParticipantDAO {

    @Override
    public void addStudentToRoomModel(RoomParticipantModel room) {
        int i = 1;
        
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO roomparticipant(onlineclassid, studentid)"
                    + "values (?,?)");

            p.setInt(i++, room.getOnlineClassId());
            p.setInt(i++, room.getStudentId());
            
            p.executeUpdate();

            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        
    }

    @Override
    public void deleteStudenRoomModelmRoom(RoomParticipantModel room) {
        int i = 1;
        
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO roomparticipant(onlineclassid, studentid)"
                    + "values (?,?)");

            p.setInt(i++, room.getOnlineClassId());
            p.setInt(i++, room.getStudentId());
            
            p.executeUpdate();

            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public ArrayList<RoomParticipantModel> findallStudentforRoom(int roomid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<RoomParticipantModel> updateRoom(RoomParticipantModel room) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
