/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.TPS6P14WebdesignMai2022.model;

import demo.TPS6P14WebdesignMai2022.generic.Attr;
import demo.TPS6P14WebdesignMai2022.generic.ClassAnotation;
import demo.TPS6P14WebdesignMai2022.Connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Cedrick
 */

/**
 *
 * @author Cedrick
 */
@Entity
@ClassAnotation(table = "information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    int id;
    @Attr
    String titre;
    @Attr
    String body;
    @Attr
    String photo;
    
    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl() {
        String url= titre+"-"+id;
        url=url.replaceAll("[,\\s\']+", "-");
        setUrl (url);
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
    public Information() {
    }

    public Information(String titre, String body, String photo) {
        this.titre = titre;
        this.body = body;
        this.photo = photo;
    }
    
    public void delete(int id) throws Exception {
        Connection connection = null;
        PreparedStatement stat = null;
        try {
            Connexion connect = new Connexion();
            connection = connect.con();
            String query = "delete from information where id= ?";
            stat = connection.prepareStatement(query);
            stat.setObject(1, id);
            stat.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            stat.close();
            connection.close();
        }
    }
    public void update(int id,String titre,String body,String hoto) throws Exception {
        Connection connection = null;
       Statement stat = null;
        try {
            Connexion connect = new Connexion();
            connection = connect.con();
            String query = "update information set titre='"+titre+"',body= '"+body+"',photo='"+photo+"' where id="+id;
            stat = connection.createStatement();
            stat.executeUpdate(query);
        } catch (Exception ex) {
            throw ex;
        } finally {
            stat.close();
            connection.close();
        }
    }

 

    public Information findById(int id) throws Exception {
        Information info = null;
       Connection connection = null;
       Statement stat = null;
        PreparedStatement statement=null;
       
        try{
            Connexion connect = new Connexion();
            connection = connect.con();
            String query = "SELECT * FROM information WHERE id = ?";
            statement = connection.prepareStatement(query); 
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                info = new Information();
                info.setId(resultSet.getInt("id"));
                info.setTitre(resultSet.getString("titre"));
                info.setBody(resultSet.getString("body"));
                info.setPhoto(resultSet.getString("photo"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    
    // ...
}
   
}
 // ...


    

