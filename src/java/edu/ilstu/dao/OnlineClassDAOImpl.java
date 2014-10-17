/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.OnlineClassModel;
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
public class OnlineClassDAOImpl implements OnlineClassDAO, Serializable {

    @Override
    public int createOnlineClass(OnlineClassModel onlineClass) {

        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO onlineclass (title,description,roomid, teacherid)"
                    + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setString(i++, onlineClass.getTitle());
            p.setString(i++, onlineClass.getDescription());
            p.setInt(i++, onlineClass.getRoomid());
            p.setInt(i++, onlineClass.getTeacherId());

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
    public OnlineClassModel getfindOnlineClassById(int onlineClassId) {

        int i = 1;

        OnlineClassModel onlineClassModel = new OnlineClassModel();
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM onlineclass WHERE onlineclassid=?");

            p.setInt(i++, onlineClassId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                onlineClassModel.setOnlineClassId(rs.getInt(j++));
                onlineClassModel.setTitle(rs.getString(j++));
                onlineClassModel.setDescription(rs.getString(j++));
                onlineClassModel.setRoomid(rs.getInt(j++));
                onlineClassModel.setTeacherId(rs.getInt(j++));
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

        return onlineClassModel;
    }

    @Override
    public ArrayList<OnlineClassModel> getOnliceClasses() {

        ArrayList<OnlineClassModel> onlineClassModels = new ArrayList<>();
        OnlineClassModel onlineClassModel = new OnlineClassModel();
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM onlineclass");

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                onlineClassModel.setOnlineClassId(rs.getInt(j++));
                onlineClassModel.setTitle(rs.getString(j++));
                onlineClassModel.setDescription(rs.getString(j++));
                onlineClassModel.setRoomid(rs.getInt(j++));
                onlineClassModel.setTeacherId(rs.getInt(j++));

                onlineClassModels.add(onlineClassModel);
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

        return onlineClassModels;
    }

    /**
     *
     * @param onlineClass
     */
    @Override
    public void updateOnlineClass(OnlineClassModel onlineClass) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("UPDATE onlineclass"
                    + "set title=?,"
                    + "description=?"
                    + "roomid=?");

            p.setString(i++, onlineClass.getTitle());
            p.setString(i++, onlineClass.getDescription());
            p.setInt(i++, onlineClass.getRoomid());

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

    /**
     *
     * @param onlineClass
     */
    @Override
    public void deleteOnlineClass(OnlineClassModel onlineClass) {

        int i = 1;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM onlineclass WHERE onlineclassid=?");

            p.setInt(i++, onlineClass.getOnlineClassId());

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
    public OnlineClassModel findByRoomId(int roomId) {
        int i = 1;

        OnlineClassModel onlineClassModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM onlineclass WHERE roomid=?");

            p.setInt(i++, roomId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                onlineClassModel = new OnlineClassModel();

                onlineClassModel.setOnlineClassId(rs.getInt(j++));
                onlineClassModel.setTitle(rs.getString(j++));
                onlineClassModel.setDescription(rs.getString(j++));
                onlineClassModel.setRoomid(rs.getInt(j++));
                onlineClassModel.setTeacherId(rs.getInt(j++));
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

        return onlineClassModel;
    }

    @Override
    public ArrayList<OnlineClassModel> getOnliceClassesByTeacherId(int teacherID) {
        int i = 1;
        ArrayList<OnlineClassModel> list = new ArrayList<>();
        OnlineClassModel onlineClassModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM onlineclass WHERE teacherid=?");

            p.setInt(i++, teacherID);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                onlineClassModel = new OnlineClassModel();

                onlineClassModel.setOnlineClassId(rs.getInt(j++));
                onlineClassModel.setTitle(rs.getString(j++));
                onlineClassModel.setDescription(rs.getString(j++));
                onlineClassModel.setRoomid(rs.getInt(j++));
                onlineClassModel.setTeacherId(rs.getInt(j++));

                list.add(onlineClassModel);
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
