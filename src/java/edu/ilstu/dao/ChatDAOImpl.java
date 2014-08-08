/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.ChatModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class ChatDAOImpl implements ChatDAO {

    @Override
    public int createChat(ChatModel chat) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO chat (onlineclassid)"
                    + "VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            
            p.setInt(i++, chat.getOnlineClassId());

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
    public void updateChat(ChatModel chat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteChat(ChatModel chat) {
         int i = 1;
        
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM chat "
                    + "WHERE chatid=?");
            
            p.setInt(i++, chat.getChatid());

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
    public ChatModel getChatById(int chatId) {
       
        int i=1;
        ChatModel chatModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM chat where chatid=?");
            
            p.setInt(i++, chatId);
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                chatModel = new ChatModel();
                chatModel.setChatid(rs.getInt(j++));
                chatModel.setOnlineClassId(rs.getInt(j++));
                chatModel.setChatdate(rs.getDate(j++));
                

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

        return chatModel;
    }

    @Override
    public ArrayList<ChatModel> getRoomChatsInstance(int onlineClassId) {
        
        int i=1;
        ArrayList<ChatModel> list = new ArrayList<>();
        ChatModel chatModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM chat where onlineclassid=?");

            p.setInt(i++, onlineClassId);
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                chatModel = new ChatModel();
                chatModel.setChatid(rs.getInt(j++));
                chatModel.setOnlineClassId(rs.getInt(j++));
                chatModel.setChatdate(rs.getDate(j++));
                
                list.add(chatModel);

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
    public ChatModel getChatByDate(Date date) {
        
        int i=1;
      
        ChatModel chatModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM chat where chatDate=?");

            p.setDate(i++, date);
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                chatModel = new ChatModel();
                chatModel.setChatid(rs.getInt(j++));
                chatModel.setOnlineClassId(rs.getInt(j++));
                chatModel.setChatdate(rs.getDate(j++));
                
                

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

        return chatModel ;
    }
    
}
