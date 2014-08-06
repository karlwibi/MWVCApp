/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.PreziContentModel;
import edu.ilstu.model.RessourceModel;
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
public class PreziContentDAOImpl extends RessourceDAOImpl implements PreziContentDAO {
    
    @Override
    public int createPreziRessource(PreziContentModel ressource) {
        
        int i = 1;
        int identity = super.createRessource(ressource);
        
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {
            
            PreparedStatement p = con.prepareStatement("INSERT INTO prezicontent (ressourceid,preziid)"
                    + "VALUES(?,?)");
            
            p.setInt(i++, identity);
            p.setString(i++, ressource.getPreziId());
            
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
        
        return identity;
    }
    
    @Override
    public void updatePreziRessource(PreziContentModel ressource) {
        int i = 1;
        
        Connection con = ConnectionDB.getConnInst();
        try {
            
            PreparedStatement p = con.prepareStatement("UPDATE prezicontent set preziid=? "
                    + "WHERE ressourceid=?");
            
            p.setString(i++, ressource.getPreziId());
            p.setInt(i++, ressource.getRessourceId());
            
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
    public void deletePreziRessource(PreziContentModel ressource) {
        super.deleteRessource(ressource);
    }
    
    @Override
    public PreziContentModel getPreziRessourcesByRessourceId(int ressourceId) {
        
        int j = 1;
        ResultSet rs = null;
        PreziContentModel preziContentModel = null;
        
        Connection con = ConnectionDB.getConnInst();
        
        try {
            
            PreparedStatement p = con.prepareStatement("Select r.ressourceid as ressourceid,teacherid,datecreated,onlineclassid,has_prezi,has_reveal, has_studytool,preziid"
                    + " FROM ressource r"
                    + " INNER JOIN prezicontent p "
                    + " WHERE r.ressourceid=p.ressourceid AND p.ressourceid=?");
            
            p.setInt(j++, ressourceId);
            
            rs = p.executeQuery();
            
            while (rs.next()) {
                int i = 1;                
                preziContentModel = new PreziContentModel();
                preziContentModel.setRessourceId(rs.getInt(i++));
                preziContentModel.setTeacherId(rs.getInt(i++));
                preziContentModel.setDateCreated(rs.getDate(i++));
                preziContentModel.setOnlineClassId(rs.getInt(i++));
                preziContentModel.setHasPrezi(rs.getString(i++).charAt(0));
                preziContentModel.setHasReveal(rs.getString(i++).charAt(0));
                preziContentModel.setHasStudyTool(rs.getString(i++).charAt(0));
                preziContentModel.setPreziId(rs.getString(i++));
                
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        
        return preziContentModel;
    }
    
    @Override
    public ArrayList<PreziContentModel> getPreziRessourcebyOnlineClassId(int onlineClassId) {
        int j = 1;
        ResultSet rs = null;
        PreziContentModel preziContentModel = null;
        ArrayList<PreziContentModel> list=new ArrayList<>();
        
        Connection con = ConnectionDB.getConnInst();
        
        try {
            
            PreparedStatement p = con.prepareStatement("Select r.ressourceid as ressourceid,teacherid,datecreated,onlineclassid,has_prezi,has_reveal, has_studytool,preziid"
                    + " FROM ressource r"
                    + " INNER JOIN prezicontent p "
                    + " WHERE r.ressourceid=p.ressourceid AND r.onlineclassid=?");
            
            p.setInt(j++, onlineClassId);
            
            rs = p.executeQuery();
            
            while (rs.next()) {
                int i = 1;                
                preziContentModel = new PreziContentModel();
                preziContentModel.setRessourceId(rs.getInt(i++));
                preziContentModel.setTeacherId(rs.getInt(i++));
                preziContentModel.setDateCreated(rs.getDate(i++));
                preziContentModel.setOnlineClassId(rs.getInt(i++));
                preziContentModel.setHasPrezi(rs.getString(i++).charAt(0));
                preziContentModel.setHasReveal(rs.getString(i++).charAt(0));
                preziContentModel.setHasStudyTool(rs.getString(i++).charAt(0));
                preziContentModel.setPreziId(rs.getString(i++));
                
                list.add(preziContentModel);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        
        return list;
    }
    
}
