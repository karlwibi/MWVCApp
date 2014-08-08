/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.ChatReactionModel;
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
public class ChatReactionDAOImpl implements ChatReactionDAO {

    @Override
    public int postNewReaction(ChatReactionModel chatReaction) {

        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO chatreaction (chatid,userid,chatmessage,isprivate)"
                    + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setInt(i++, chatReaction.getChatid());
            p.setInt(i++, chatReaction.getUserid());
            p.setString(i++, chatReaction.getChatmessage());
            p.setString(i++, Character.toString(chatReaction.getIsPrivate()));

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
    public void updateChatreaction(ChatReactionModel chatReaction) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("UPDATE chatreaction "
                    + "chatmessage=?,"
                    + "isprivate=?"
                    + "WHERE chatid=? and userid=?");

            p.setString(i++, chatReaction.getChatmessage());
            p.setString(i++, Character.toString(chatReaction.getIsPrivate()));
            p.setInt(i++, chatReaction.getChatid());
            p.setInt(i++, chatReaction.getUserid());

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
    public void DeleteChatReaction(ChatReactionModel chatReaction) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM chatreaction "
                    + "WHERE chatid=? and userid=?");

            p.setInt(i++, chatReaction.getChatid());
            p.setInt(i++, chatReaction.getUserid());

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
    public ArrayList<ChatReactionModel> getChatReactionById(int chatId) {
        int i = 1;
        ArrayList<ChatReactionModel> list = new ArrayList<>();
        ChatReactionModel chatReactionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM chatreaction where chatid=?");

            p.setInt(i++, chatId);
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                chatReactionModel = new ChatReactionModel();
                chatReactionModel.setChatid(rs.getInt(j++));
                chatReactionModel.setUserid(rs.getInt(j++));
                chatReactionModel.setChatmessage(rs.getString(j++));
                chatReactionModel.setIsPrivate(rs.getString(j++).charAt(0));
                chatReactionModel.setReactionDate(rs.getDate(j++));
                chatReactionModel.setReactionTime(rs.getTime(j++));

                list.add(chatReactionModel);

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
    public ChatReactionModel getChatReactionForUser(int chatId, int userId) {
        int i = 1;
        
        ChatReactionModel chatReactionModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM chatreaction where chatid=? and userid=?");

            p.setInt(i++, chatId);
            p.setInt(i++, userId);
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                chatReactionModel = new ChatReactionModel();
                chatReactionModel.setChatid(rs.getInt(j++));
                chatReactionModel.setUserid(rs.getInt(j++));
                chatReactionModel.setChatmessage(rs.getString(j++));
                chatReactionModel.setIsPrivate(rs.getString(j++).charAt(0));
                chatReactionModel.setReactionDate(rs.getDate(j++));
                chatReactionModel.setReactionTime(rs.getTime(j++));

               

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

        return chatReactionModel;
    }

}
