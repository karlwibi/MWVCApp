/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.RoomParticipantModel;
import java.io.Serializable;
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
public class RoomParticipantDAOImpl implements RoomParticipantDAO, Serializable {

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

            PreparedStatement p = con.prepareStatement("DELETE FROM roomparticipant WHERE onliclassid=? and studentid=?");

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
    public ArrayList<RoomParticipantModel> findallStudentforRoom(int onlineclassid) {
        int i = 1;
        ResultSet rs=null;
        Connection con = ConnectionDB.getConnInst();
        RoomParticipantModel rpm = null;
        ArrayList<RoomParticipantModel> roomParticipantModels=new ArrayList();
        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM roomparticipant WHERE onlineclassid=?");

            p.setInt(i++, onlineclassid);
           
            
            rs=p.executeQuery();
            
            while(rs.next()){
                int j=1;
                rpm = new RoomParticipantModel();
                rpm.setOnlineClassId(rs.getInt(j++));
                rpm.setStudentId(rs.getInt(j++));
                
                roomParticipantModels.add(rpm);
            }

            
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
    
        return roomParticipantModels;
                
    }

    @Override
    public ArrayList<RoomParticipantModel> findallEnrollClasses(int studentId) {
        int i = 1;
        ResultSet rs=null;
        Connection con = ConnectionDB.getConnInst();
        RoomParticipantModel rpm = null;
        ArrayList<RoomParticipantModel> roomParticipantModels=new ArrayList();
        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM roomparticipant WHERE studentid=?");

            p.setInt(i++, studentId);
           
            
            rs=p.executeQuery();
            
            while(rs.next()){
                int j=1;
                rpm = new RoomParticipantModel();
                rpm.setOnlineClassId(rs.getInt(j++));
                rpm.setStudentId(rs.getInt(j++));
                
                roomParticipantModels.add(rpm);
            }

            
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
    
        return roomParticipantModels;
    }

}
