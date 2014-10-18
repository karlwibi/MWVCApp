/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.ScheduleClassModel;
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
public class ScheduleClassDAOImpl implements ScheduleClassDAO, Serializable {

    @Override
    public int createSchedule(ScheduleClassModel scheduleClass) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO scheduleclass"
                    + "(onlineclassid,startdate,enddate,starttime, endtime)"
                    + "values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

           
            p.setInt(i++, scheduleClass.getOnlineClassId());
            p.setDate(i++, scheduleClass.getStartDate());
            p.setDate(i++, scheduleClass.getEndDate());
            p.setTime(i++, scheduleClass.getStartTime());
            p.setTime(i++, scheduleClass.getEndTime());

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
    public ArrayList<ScheduleClassModel> findScheduleByTeacherId(int teacherId) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        ScheduleClassModel scm = new ScheduleClassModel();
        ArrayList<ScheduleClassModel> scheduleClassModels = new ArrayList<ScheduleClassModel>();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM scheduleclass WHERE teacherid=?");

            p.setInt(i++, teacherId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                scm.setScheduleClassId(rs.getInt(j++));
               
                scm.setOnlineClassId(rs.getInt(j++));
                scm.setStartDate(rs.getDate(j++));
                scm.setEndDate(rs.getDate(j++));
                scm.setStartTime(rs.getTime(j++));
                scm.setEndTime(rs.getTime(j++));

                scheduleClassModels.add(scm);
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

        return scheduleClassModels;
    }

    @Override
    public ScheduleClassModel findScheduleByOnlineClassId(int onlineClassId) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        ScheduleClassModel scm = new ScheduleClassModel();
        ArrayList<ScheduleClassModel> scheduleClassModels = new ArrayList<ScheduleClassModel>();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM scheduleclass WHERE onlineclassid=?");

            p.setInt(i++, onlineClassId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                scm.setScheduleClassId(rs.getInt(j++));
                
                scm.setOnlineClassId(rs.getInt(j++));
                scm.setStartDate(rs.getDate(j++));
                scm.setEndDate(rs.getDate(j++));
                scm.setStartTime(rs.getTime(j++));
                scm.setEndTime(rs.getTime(j++));

                
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

        return scm;
    }

   

    @Override
    public void updateSchedule(ScheduleClassModel scheduleClass) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("UPDATE scheduleclass"
                    + "set startdate=?,"
                    + "enddate=?,"
                    + "starttime=?,"
                    + "endtime=? "
                    + "WHERE scheduleclassid=?");

            p.setDate(i++, scheduleClass.getStartDate());
            p.setDate(i++, scheduleClass.getEndDate());
            p.setTime(i++, scheduleClass.getStartTime());
            p.setTime(i++, scheduleClass.getEndTime());
            p.setInt(i++, scheduleClass.getScheduleClassId());

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
    public void deleteSchedule(ScheduleClassModel scheduleClass) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM scheduleclass WHERE scheduleclassid=?");

            p.setInt(i++, scheduleClass.getScheduleClassId());

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
    public ScheduleClassModel getScheduleClassById(int scheduleClassId) {

        int i = 1;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        ScheduleClassModel scm = new ScheduleClassModel();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM scheduleclass WHERE scheduleclassid=?");

            p.setInt(i++, scheduleClassId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                scm.setScheduleClassId(rs.getInt(j++));
               
                scm.setOnlineClassId(rs.getInt(j++));
                scm.setStartDate(rs.getDate(j++));
                scm.setEndDate(rs.getDate(j++));
                scm.setStartTime(rs.getTime(j++));
                scm.setEndTime(rs.getTime(j++));

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

        return scm;
    }

    @Override
    public ArrayList<ScheduleClassModel> getAllSchedules() {
        
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        ScheduleClassModel scm = new ScheduleClassModel();
        ArrayList<ScheduleClassModel> scheduleClassModels = new ArrayList<ScheduleClassModel>();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM scheduleclass");

            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
               
                scm.setOnlineClassId(rs.getInt(j++));
                scm.setStartDate(rs.getDate(j++));
                scm.setEndDate(rs.getDate(j++));
                scm.setStartTime(rs.getTime(j++));
                scm.setEndTime(rs.getTime(j++));

                scheduleClassModels.add(scm);
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

        return scheduleClassModels;
    }

    @Override
    public ScheduleClassModel findScheduleByScheduleId(int scheduleId) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        ScheduleClassModel scm = new ScheduleClassModel();
        ArrayList<ScheduleClassModel> scheduleClassModels = new ArrayList<ScheduleClassModel>();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM scheduleclass WHERE scheduleclassid=?");

            p.setInt(i++, scheduleId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                scm.setScheduleClassId(rs.getInt(j++));
                
                scm.setOnlineClassId(rs.getInt(j++));
                scm.setStartDate(rs.getDate(j++));
                scm.setEndDate(rs.getDate(j++));
                scm.setStartTime(rs.getTime(j++));
                scm.setEndTime(rs.getTime(j++));

                
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

        return scm;
    }

}