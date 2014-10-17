/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.RessourceModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author kawibi
 */
public abstract class RessourceDAOImpl implements RessourceDAO {

    @Override
    public int createRessource(RessourceModel ressourceModel) {
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO ressource (datecreated,onlineclassid,has_prezi, has_reveal, has_studytool)"
                    + "VALUES(CURDATE(),?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

//            p.setInt(i++, ressourceModel.getTeacherId());
            p.setInt(i++, ressourceModel.getOnlineClassId());
            p.setString(i++, Character.toString(ressourceModel.getHasPrezi()));
            p.setString(i++, Character.toString(ressourceModel.getHasReveal()));
            p.setString(i++, Character.toString(ressourceModel.getHasStudyTool()));

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
    public void deleteRessource(RessourceModel ressourceModel) {
        int i = 1;

        Connection con = ConnectionDB.getConnInst();
        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM ressource WHERE ressourceid=?");

            p.setInt(i++, ressourceModel.getRessourceId());

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
