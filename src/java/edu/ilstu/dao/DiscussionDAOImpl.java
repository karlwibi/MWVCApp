/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.DiscussionModel;
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
public class DiscussionDAOImpl implements DiscussionDAO {

    @Override
    public int createDiscussion(DiscussionModel discussion) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO discussion (title,description,startdate, enddate, starttime, endtime, createdby, onlineclassid)"
                    + "VALUES(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setString(i++, discussion.getTitle());
            p.setString(i++, discussion.getDescription());
            p.setDate(i++, discussion.getStartDate());
            p.setDate(i++, discussion.getEndDate());
            p.setTime(i++, discussion.getStartTime());
            p.setTime(i++, discussion.getEndTime());
            p.setInt(i++, discussion.getCreateBy());
            p.setInt(i++, discussion.getOnlineClassId());

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
    public void updateDiscussion(DiscussionModel discussion) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("UPDATE discussion "
                    + "set title=?,"
                    + "description=?,"
                    + "startdate=?, "
                    + "enddate=?,"
                    + "starttime=?,"
                    + "endtime=?,"
                    + "createdby=?,"
                    + "onlineclassid=? "
                    + "WHERE discussionid=?");

            p.setString(i++, discussion.getTitle());
            p.setString(i++, discussion.getDescription());
            p.setDate(i++, discussion.getStartDate());
            p.setDate(i++, discussion.getEndDate());
            p.setTime(i++, discussion.getStartTime());
            p.setTime(i++, discussion.getEndTime());
            p.setInt(i++, discussion.getCreateBy());
            p.setInt(i++, discussion.getOnlineClassId());
            p.setInt(i++, discussion.getDiscussionId());

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
    public DiscussionModel findDiscussionsbyId(int discussionid) {
        int i = 1;

        DiscussionModel discussionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM discussion WHERE discussionid=?");

            p.setInt(i++, discussionid);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                discussionModel = new DiscussionModel();

                discussionModel.setDiscussionId(rs.getInt(j++));
                discussionModel.setTitle(rs.getString(j++));
                discussionModel.setDescription(rs.getString(j++));
                discussionModel.setStartDate(rs.getDate(j++));
                discussionModel.setEndDate(rs.getDate(j++));
                discussionModel.setStartTime(rs.getTime(j++));
                discussionModel.setEndTime(rs.getTime(j++));
                discussionModel.setCreateBy(rs.getInt(j++));
                discussionModel.setOnlineClassId(rs.getInt(j++));
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

        return discussionModel;
    }

    @Override
    public ArrayList<DiscussionModel> findDiscussionbyOnlineClass(int onlinecalssid) {
        int i = 1;

        DiscussionModel discussionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        ArrayList<DiscussionModel> list = new ArrayList<>();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM discussion WHERE onlineclassid=?");

            p.setInt(i++, onlinecalssid);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                discussionModel = new DiscussionModel();

                discussionModel.setDiscussionId(rs.getInt(j++));
                discussionModel.setTitle(rs.getString(j++));
                discussionModel.setDescription(rs.getString(j++));
                discussionModel.setStartDate(rs.getDate(j++));
                discussionModel.setEndDate(rs.getDate(j++));
                discussionModel.setStartTime(rs.getTime(j++));
                discussionModel.setEndTime(rs.getTime(j++));
                discussionModel.setCreateBy(rs.getInt(j++));
                discussionModel.setOnlineClassId(rs.getInt(j++));

                list.add(discussionModel);
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
    public ArrayList<DiscussionModel> findDiscussionByOwner(int teacherId) {

        int i = 1;

        DiscussionModel discussionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        ArrayList<DiscussionModel> list = new ArrayList<>();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM discussion WHERE createby=?");

            p.setInt(i++, teacherId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                discussionModel = new DiscussionModel();

                discussionModel.setDiscussionId(rs.getInt(j++));
                discussionModel.setTitle(rs.getString(j++));
                discussionModel.setDescription(rs.getString(j++));
                discussionModel.setStartDate(rs.getDate(j++));
                discussionModel.setEndDate(rs.getDate(j++));
                discussionModel.setStartTime(rs.getTime(j++));
                discussionModel.setEndTime(rs.getTime(j++));
                discussionModel.setCreateBy(rs.getInt(j++));
                discussionModel.setOnlineClassId(rs.getInt(j++));

                list.add(discussionModel);
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
    public ArrayList<DiscussionModel> getDiscussions() {

        DiscussionModel discussionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        ArrayList<DiscussionModel> list = new ArrayList<>();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM discussion ");

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                discussionModel = new DiscussionModel();

                discussionModel.setDiscussionId(rs.getInt(j++));
                discussionModel.setTitle(rs.getString(j++));
                discussionModel.setDescription(rs.getString(j++));
                discussionModel.setStartDate(rs.getDate(j++));
                discussionModel.setEndDate(rs.getDate(j++));
                discussionModel.setStartTime(rs.getTime(j++));
                discussionModel.setEndTime(rs.getTime(j++));
                discussionModel.setCreateBy(rs.getInt(j++));
                discussionModel.setOnlineClassId(rs.getInt(j++));

                list.add(discussionModel);
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
    public void deleteDiscussion(DiscussionModel discussion) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM discussion "
                    + "WHERE discussionid=?");

            p.setInt(i++, discussion.getDiscussionId());

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
    public DiscussionModel findDiscussionsByTitle(String title) {
         int i = 1;

        DiscussionModel discussionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM discussion WHERE title=?");

            p.setString(i++, title);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                discussionModel = new DiscussionModel();

                discussionModel.setDiscussionId(rs.getInt(j++));
                discussionModel.setTitle(rs.getString(j++));
                discussionModel.setDescription(rs.getString(j++));
                discussionModel.setStartDate(rs.getDate(j++));
                discussionModel.setEndDate(rs.getDate(j++));
                discussionModel.setStartTime(rs.getTime(j++));
                discussionModel.setEndTime(rs.getTime(j++));
                discussionModel.setCreateBy(rs.getInt(j++));
                discussionModel.setOnlineClassId(rs.getInt(j++));
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

        return discussionModel;
    }

    @Override
    public ArrayList<DiscussionModel> findDiscussionsByDescriptionKeyWord(String keyWord) {
        int i = 1;

        DiscussionModel discussionModel = null;
        ResultSet rs = null;
        ArrayList<DiscussionModel> list= new ArrayList<>();
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM discussion WHERE title LIKE ?");

            p.setString(i++, "%"+keyWord+"%");

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                discussionModel = new DiscussionModel();

                discussionModel.setDiscussionId(rs.getInt(j++));
                discussionModel.setTitle(rs.getString(j++));
                discussionModel.setDescription(rs.getString(j++));
                discussionModel.setStartDate(rs.getDate(j++));
                discussionModel.setEndDate(rs.getDate(j++));
                discussionModel.setStartTime(rs.getTime(j++));
                discussionModel.setEndTime(rs.getTime(j++));
                discussionModel.setCreateBy(rs.getInt(j++));
                discussionModel.setOnlineClassId(rs.getInt(j++));
                
                list.add(discussionModel);
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
