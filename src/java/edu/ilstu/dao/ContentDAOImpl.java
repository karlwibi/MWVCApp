/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.ContentModel;
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
public class ContentDAOImpl implements ContentDAO {

    @Override
    public int createContent(ContentModel content) {
       
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO content (revealid,page,contenttext)"
                    + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setInt(i++, content.getRevealId());
            p.setInt(i++, content.getPage());
            p.setString(i++, content.getContentText());

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
    public void updateContent(ContentModel content) {
        
        int i = 1;

        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("UPDATE content"
                    + "SET revealid=?,"
                    + "page=?,"
                    + "contenttext=? "
                    + "WHERE contentid=?", Statement.RETURN_GENERATED_KEYS);

            p.setInt(i++, content.getRevealId());
            p.setInt(i++, content.getPage());
            p.setString(i++, content.getContentText());
            p.setInt(i++, content.getContentId());

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
    public ArrayList<ContentModel> getAllContents() {

        ArrayList<ContentModel> list = new ArrayList<>();
        ContentModel contentModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM content");

            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                contentModel = new ContentModel();
                contentModel.setContentId(rs.getInt(j++));
                contentModel.setRevealId(rs.getInt(j++));
                contentModel.setPage(rs.getInt(j++));
                contentModel.setContentText(rs.getString(j++));

                list.add(contentModel);

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
    public ArrayList<ContentModel> getContentsByRevealId(int revealId) {
        
        int i=1;
        ArrayList<ContentModel> list = new ArrayList<>();
        ContentModel contentModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM content WHERE revealid=?");

            p.setInt(i++, revealId);
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                contentModel = new ContentModel();
                contentModel.setContentId(rs.getInt(j++));
                contentModel.setRevealId(rs.getInt(j++));
                contentModel.setPage(rs.getInt(j++));
                contentModel.setContentText(rs.getString(j++));

                list.add(contentModel);

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
    public void deleteContent(ContentModel content) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM content"
                    + "WHERE contentid=?");

            p.setInt(i++, content.getContentId());

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

}
