package sio.gestionmagazine.Services;

import sio.gestionmagazine.Model.Magazine;
import sio.gestionmagazine.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceMagazine
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceMagazine()
    {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Magazine> getAllMagazines() throws SQLException {
        ArrayList<Magazine> lesMagazines = new ArrayList<>();
      try {
          ps=cnx.prepareStatement("SELECT magazine.idMag,magazine.nomMag,specialite.nomSpe " +
                  "FROM magazine AS magazine " +
                  "JOIN specialite AS specialite ON magazine.numSpecialite = specialite.idSpe");
          rs=ps.executeQuery();
          while (rs.next()) {
              Magazine m= new Magazine(rs.getInt("idMag"),rs.getString("nomMag"),rs.getString("numSpecialite"));
              lesMagazines.add(m);

          }
          ps.close();
          rs.close();

      }catch (SQLException e) {
          Logger.getLogger(ServiceMagazine.class.getName()).log(Level.SEVERE, null, e);
      }
      return lesMagazines;
    }
    public Double getMontantMagazine(int idMag) throws SQLException {
        double montant = 0.0;
        try {

        ps=cnx.prepareStatement("SELECT montant FROM magazine WHERE idMag = ?");
        ps.setInt(1,idMag);
        rs=ps.executeQuery();
        rs.next();
         montant = rs.getDouble("montant");
        ps.close();
        rs.close();

    }catch (SQLException e) {
        Logger.getLogger(ServiceMagazine.class.getName()).log(Level.SEVERE, null, e);
    }
        return montant;
    }


}
