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

            PreparedStatement p = con.prepareStatement("INSERT INTO studytool (ressourceid,articlelink,description)"
                    + "VALUES(?,?,?)");

            p.setInt(i++, identity);
            p.setString(i++, ressource.getArticleLink());
            p.setString(i++, ressource.getDescription());

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
                    + "description=? "
                    + "WHERE ressourceid=?");

            p.setString(i++, ressource.getArticleLink());
            p.setString(i++, ressource.getDescription());
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
        int j = 1;
        ResultSet rs = null;
        StudyToolModel studyToolModel = null;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("Select r.ressourceid as ressourceid,teacherid,datecreated,onlineclassid,has_prezi,has_reveal, has_studytool,articlelink,description "
                    + " FROM ressource r"
                    + " INNER JOIN studytool s "
                    + " WHERE r.ressourceid=s.ressourceid AND s.ressourceid=?");

            p.setInt(j++, ressourceId);

            rs = p.executeQuery();

            while (rs.next()) {
                int i = 1;
                studyToolModel = new StudyToolModel();
                studyToolModel.setRessourceId(rs.getInt(i++));
                studyToolModel.setTeacherId(rs.getInt(i++));
                studyToolModel.setDateCreated(rs.getDate(i++));
                studyToolModel.setOnlineClassId(rs.getInt(i++));
                studyToolModel.setHasPrezi(rs.getString(i++).charAt(0));
                studyToolModel.setHasReveal(rs.getString(i++).charAt(0));
                studyToolModel.setHasStudyTool(rs.getString(i++).charAt(0));
                studyToolModel.setArticleLink(rs.getString(i++));
                studyToolModel.setDescription(rs.getString(i++));

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

        return studyToolModel;
    }

    @Override
    public ArrayList<StudyToolModel> getStudytoolRessourceByOnlinceClasseId(int onlinceClassId) {
         int j = 1;
        ResultSet rs = null;
        StudyToolModel studyToolModel = null;
        ArrayList<StudyToolModel> list=new ArrayList<>();

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("Select r.ressourceid as ressourceid,teacherid,datecreated,onlineclassid,has_prezi,has_reveal, has_studytool,articlelink,description "
                    + " FROM ressource r"
                    + " INNER JOIN studytool s "
                    + " WHERE r.ressourceid=s.ressourceid AND r.onlineclassid=?");

            p.setInt(j++, onlinceClassId);

            rs = p.executeQuery();

            while (rs.next()) {
                int i = 1;
                studyToolModel = new StudyToolModel();
                studyToolModel.setRessourceId(rs.getInt(i++));
                studyToolModel.setTeacherId(rs.getInt(i++));
                studyToolModel.setDateCreated(rs.getDate(i++));
                studyToolModel.setOnlineClassId(rs.getInt(i++));
                studyToolModel.setHasPrezi(rs.getString(i++).charAt(0));
                studyToolModel.setHasReveal(rs.getString(i++).charAt(0));
                studyToolModel.setHasStudyTool(rs.getString(i++).charAt(0));
                studyToolModel.setArticleLink(rs.getString(i++));
                studyToolModel.setDescription(rs.getString(i++));

                list.add(studyToolModel);
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
