package sio.gestionmagazine.Services;

import sio.gestionmagazine.Model.Magazine;
import sio.gestionmagazine.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicePigiste
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicePigiste()
    {
        cnx = ConnexionBDD.getCnx();
    }
  public ArrayList<String> getAllPigistes() throws SQLException {
        ArrayList<String> lesPigistes = new ArrayList<>();
        try {
        ps=cnx.prepareStatement("SELECT nomPigiste FROM pigiste");
        rs=ps.executeQuery();
        while (rs.next()) {
            lesPigistes.add(rs.getString("nomPigiste"));
        }
        ps.close();
        rs.close();

    }catch (SQLException e) {
        Logger.getLogger(ServicePigiste.class.getName()).log(Level.SEVERE, null, e);
    }
        return lesPigistes;
    }

}
