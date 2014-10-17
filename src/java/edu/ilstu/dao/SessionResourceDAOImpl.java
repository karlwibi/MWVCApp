/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.SessionResourceModel;
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
public class SessionResourceDAOImpl implements SessionResourceDAO{

    @Override
    public void createSessionRessource(SessionResourceModel sessionResourceModel) {
        int i = 1;
       
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO sessionResource (sessionid, resourceid)"
                    + "VALUES(?,?)");

            p.setInt(i++, sessionResourceModel.getSessionId());
            p.setInt(i++, sessionResourceModel.getResourceId());
           
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
    public void deleteASessionResource(SessionResourceModel sessionRessource) {
        int i = 1;
       
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM sessionResource "
                    + "WHERE resourceid=?");

           
            p.setInt(i++, sessionRessource.getResourceId());
           
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
    public ArrayList<SessionResourceModel> getSessionRessoureForASession(int sessionId) {
          int i=1;
        ArrayList<SessionResourceModel> list =new ArrayList<>();
       SessionResourceModel srm = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM sessionResource WHERE sessionid=?");

            p.setInt(i++,sessionId);
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                srm = new SessionResourceModel();
                srm.setSessionId(rs.getInt(j++));
                srm.setResourceId(rs.getInt(j++));
               
                
                list.add( srm);

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
    public void deleteAllSessionResource(SessionResourceModel sessionRessource) {
       int i = 1;
       
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM sessionResource "
                    + "WHERE sessionid=?");

           
            p.setInt(i++, sessionRessource.getSessionId());
           
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
