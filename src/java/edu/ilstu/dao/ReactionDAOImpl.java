/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.ReactionModel;
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
public class ReactionDAOImpl implements ReactionDAO {

    @Override
    public int newReaction(ReactionModel reaction) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO reaction (discussionId,postby,reaction)"
                    + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setInt(i++, reaction.getDiscussionId());
            p.setInt(i++, reaction.getPostby());
            p.setString(i++, reaction.getReactionText());

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
    public void updateReaction(ReactionModel reaction) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("UPDATE reaction "
                    + "set discussionId=?,"
                    + "postby=?,"
                    + "reaction=? "
                    + "WHERE reactionid=?");

            p.setInt(i++, reaction.getDiscussionId());
            p.setInt(i++, reaction.getPostby());
            p.setString(i++, reaction.getReactionText());
            p.setInt(i++, reaction.getReactionId());

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
    public ArrayList<ReactionModel> getReactionByUserId(int userId) {
        int i = 1;

        ReactionModel reactionModel = null;
        ResultSet rs = null;
        ArrayList<ReactionModel> list = new ArrayList<>();

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM reaction WHERE postby=?");

            p.setInt(i++, userId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                reactionModel = new ReactionModel();
                reactionModel.setReactionId(rs.getInt(j++));
                reactionModel.setDiscussionId(rs.getInt(j++));
                reactionModel.setPostby(rs.getInt(j++));
                reactionModel.setReactionText(rs.getString(j++));

                list.add(reactionModel);
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
    public ArrayList<ReactionModel> getReactionByDiscussionId(int discussionId) {
        int i = 1;

        ReactionModel reactionModel = null;
        ResultSet rs = null;
        ArrayList<ReactionModel> list = new ArrayList<>();

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM reaction WHERE discussionid=?");

            p.setInt(i++, discussionId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                reactionModel = new ReactionModel();
                reactionModel.setReactionId(rs.getInt(j++));
                reactionModel.setDiscussionId(rs.getInt(j++));
                reactionModel.setPostby(rs.getInt(j++));
                reactionModel.setReactionText(rs.getString(j++));

                list.add(reactionModel);
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
    public void deleteReaction(ReactionModel reaction) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM reaction "
                    + "WHERE reactionid=?");

            p.setInt(i++, reaction.getReactionId());

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
    public ReactionModel getReactionById(int reactionId) {
        int i = 1;

        ReactionModel reactionModel = null;
        ResultSet rs = null;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM reaction WHERE reactionid=?");

            p.setInt(i++, reactionId);

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                reactionModel = new ReactionModel();
                reactionModel.setReactionId(rs.getInt(j++));
                reactionModel.setDiscussionId(rs.getInt(j++));
                reactionModel.setPostby(rs.getInt(j++));
                reactionModel.setReactionText(rs.getString(j++));

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

        return reactionModel;
    }

}
