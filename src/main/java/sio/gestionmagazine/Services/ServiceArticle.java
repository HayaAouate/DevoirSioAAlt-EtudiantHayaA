package sio.gestionmagazine.Services;

import sio.gestionmagazine.Model.Article;
import sio.gestionmagazine.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceArticle
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceArticle()
    {
        cnx = ConnexionBDD.getCnx();
    }


    public ArrayList<Article> getAllArticles() throws SQLException {
        ArrayList<Article> lesArticles = new ArrayList<>();
        ps=cnx.prepareStatement("SELECT article.idArticle,article.titreArticle,article.nbFeuillets,pigiste.nomPigiste " +
                "FROM article AS article " +
                "JOIN pigiste AS pigiste ON article.numPig = pigiste.idPigiste");
        rs=ps.executeQuery();
        while (rs.next()) {
            Article a= new Article(rs.getInt("idArticle"),rs.getString("titreArticle"),rs.getInt("nbPages"),rs.getString("nomPigiste"));
            lesArticles.add(a);
        }
        ps.close();
        rs.close();
        return lesArticles;



    }
    public String getMontantArticle(int idArticle) throws SQLException {
        double montant = 0.0;
        ps=cnx.prepareStatement("SSELECT a.idArticle, a.titreArticle, a.nbFeuillets, p.nomPigiste, (a.nbFeuillets * p.prixFeuillet) AS montant " +
                "FROM article AS a JOIN pigiste AS p ON a.numPig = p.idPigiste");
        ps.setInt(1,idArticle);
        rs=ps.executeQuery();
        rs.next();
        montant = rs.getDouble("montant");
        ps.close();
        rs.close();
        return montant;
    }
}
