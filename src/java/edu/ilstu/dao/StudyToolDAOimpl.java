/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.StudyToolModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class StudyToolDAOimpl extends RessourceDAOImpl implements StudyToolDAO {

    @Override
    public int createStudyRessource(StudyToolModel ressource) {
        int i = 1;
        int identity = super.createRessource(ressource);

        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO studytool (ressourceid,articlelink,videoslink)"
                    + "VALUES(?,?,?)");

            p.setInt(i++, identity);
            p.setString(i++, ressource.getArticleLink());
            p.setString(i++, ressource.getVideolink());

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
    public void updateStudyRessource(StudyToolModel ressource) {

        int i = 1;

        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("UPDATE studytool set articlelink=?,"
                    + "videoslink=? "
                    + "WHERE ressourceid=?");

            p.setString(i++, ressource.getArticleLink());
            p.setString(i++, ressource.getVideolink());
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
    public void deleteStudyRessource(StudyToolModel ressource) {
        super.deleteRessource(ressource);
    }

    @Override
    public StudyToolModel getRessourceById(int ressourceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<StudyToolModel> getStudytoolRessourceByOnlinceClasseId(int onlinceClassId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
