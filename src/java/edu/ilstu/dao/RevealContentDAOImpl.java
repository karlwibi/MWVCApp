/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.RevealContentModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class RevealContentDAOImpl extends RessourceDAOImpl implements RevealContentDAO {

    @Override
    public int createRevealRessource(RevealContentModel ressource) {
        int i = 1;
        int identity = super.createRessource(ressource);

        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO revealcontent (ressourceid)"
                    + "VALUES(?)");

            p.setInt(i++, identity);

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
    public void updateRevealRessource(RevealContentModel ressource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRevealRessource(RevealContentModel ressource) {
        super.deleteRessource(ressource);
    }

    @Override
    public RevealContentModel getRessourceById(int ressourceId) {
        int j = 1;
        ResultSet rs = null;
        RevealContentModel revealContentModel = null;

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("Select r.ressourceid as ressourceid,teacherid,datecreated,onlineclassid,has_prezi,has_reveal, has_studytool,revealid "
                    + " FROM ressource r"
                    + " INNER JOIN revealcontent re "
                    + " WHERE r.ressourceid=re.ressourceid AND re.ressourceid=?");

            p.setInt(j++, ressourceId);

            rs = p.executeQuery();

            while (rs.next()) {
                int i = 1;
                revealContentModel = new RevealContentModel();
                revealContentModel.setRessourceId(rs.getInt(i++));
                revealContentModel.setTeacherId(rs.getInt(i++));
                revealContentModel.setDateCreated(rs.getDate(i++));
                revealContentModel.setOnlineClassId(rs.getInt(i++));
                revealContentModel.setHasPrezi(rs.getString(i++).charAt(0));
                revealContentModel.setHasReveal(rs.getString(i++).charAt(0));
                revealContentModel.setHasStudyTool(rs.getString(i++).charAt(0));
                revealContentModel.setRevealId(rs.getInt(i++));

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

        return revealContentModel;
    }

    @Override
    public ArrayList<RevealContentModel> getRevealRessourceByOnlinceClasseId(int onlinceClassId) {
        int j = 1;
        ResultSet rs = null;
        RevealContentModel revealContentModel = null;
        ArrayList<RevealContentModel> list = new ArrayList<>();

        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("Select r.ressourceid as ressourceid,teacherid,datecreated,onlineclassid,has_prezi,has_reveal, has_studytool,revealid "
                    + " FROM ressource r"
                    + " INNER JOIN revealcontent re "
                    + " WHERE r.ressourceid=re.ressourceid AND r.onlineclassid=?");

            p.setInt(j++, onlinceClassId);

            rs = p.executeQuery();

            while (rs.next()) {
                int i = 1;
                revealContentModel = new RevealContentModel();
                revealContentModel.setRessourceId(rs.getInt(i++));
                revealContentModel.setTeacherId(rs.getInt(i++));
                revealContentModel.setDateCreated(rs.getDate(i++));
                revealContentModel.setOnlineClassId(rs.getInt(i++));
                revealContentModel.setHasPrezi(rs.getString(i++).charAt(0));
                revealContentModel.setHasReveal(rs.getString(i++).charAt(0));
                revealContentModel.setHasStudyTool(rs.getString(i++).charAt(0));
                revealContentModel.setRevealId(rs.getInt(i++));

                list.add(revealContentModel);

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
