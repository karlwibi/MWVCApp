/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.ClassSessionModel;
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
public class ClassSessionDAOImpl implements ClassSessionDAO {

    @Override
    public int createSession(ClassSessionModel session) {
        
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO classsession (scheduleclassid, presentationid, sessiondate)"
                    + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setInt(i++, session.getScheduleClassId());
            p.setInt(i++, session.getPresentationId());
            p.setDate(i++, session.getSessionDate());

            p.executeUpdate();

            rs = p.getGeneratedKeys();
            rs.next();
            identity = rs.getInt(1);

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

        return identity;
    }

    @Override
    public void updateSession(ClassSessionModel session) {

        int i = 1;
        
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("UPDATE classsession"
                    + "set scheduleclasid=?,"
                    + "presentationid=?,"
                    + "sessiondate=?"
                    + "WHERE sessionid=?");

            p.setInt(i++, session.getScheduleClassId());
            p.setInt(i++, session.getPresentationId());
            
            p.setDate(i++, session.getSessionDate());
            p.setInt(i++, session.getSessionId());

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
    public void deleteSession(ClassSessionModel session) {
         int i = 1;
        
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement(" DELETE FROM classsession"
                    + "WHERE sessionid=?");

            p.setInt(i++, session.getSessionId());

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
    public ClassSessionModel getASession(int sessionId) {
        int i=1;
        
        ClassSessionModel classSessionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM classsession WHERE sessionid=?");

            p.setInt(i++,sessionId );
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                classSessionModel = new ClassSessionModel();
                classSessionModel.setSessionId(rs.getInt(j++));
                classSessionModel.setScheduleClassId(rs.getInt(j++));
                classSessionModel.setPresentationId(rs.getInt(j++));
                classSessionModel.setSessionDate(rs.getDate(j++));
                
        

            }

            rs.close();

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

        return classSessionModel;
    }

    @Override
    public ArrayList<ClassSessionModel> getSessionsByScheduleId(int scheduleId) {
        
        int i=1;
        ArrayList<ClassSessionModel> list =new ArrayList<>();
        ClassSessionModel classSessionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM classsession WHERE scheduleclassid=?");

            p.setInt(i++,scheduleId );
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                classSessionModel = new ClassSessionModel();
                classSessionModel.setSessionId(rs.getInt(j++));
                classSessionModel.setScheduleClassId(rs.getInt(j++));
                classSessionModel.setPresentationId(rs.getInt(j++));
                classSessionModel.setSessionDate(rs.getDate(j++));
                
                list.add(classSessionModel);

            }

            rs.close();

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

        return list;
    }

    @Override
    public ArrayList<ClassSessionModel> getAllSessions() {
       
        ArrayList<ClassSessionModel> list =new ArrayList<>();
        ClassSessionModel classSessionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM classsession ");

                       
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                classSessionModel = new ClassSessionModel();
                classSessionModel.setSessionId(rs.getInt(j++));
                classSessionModel.setScheduleClassId(rs.getInt(j++));
                classSessionModel.setPresentationId(rs.getInt(j++));
                classSessionModel.setSessionDate(rs.getDate(j++));
                
                list.add(classSessionModel);

            }

            rs.close();

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

        return list;
    }
    
    
    
    
}
