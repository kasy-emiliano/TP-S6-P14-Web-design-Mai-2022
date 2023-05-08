package demo.TPS6P14WebdesignMai2022.Connexion;


import java.sql.DriverManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Cedrick
 */
public class Connexion {
    
    @Autowired
        DataSource datasource;
    
     public java.sql.Connection con() throws Exception{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://containers-us-west-180.railway.app:6027/railway","postgres","WpZhHXX7Dl3KMFNqu9fu");
//        return datasource.getConnection();
     }
    
}
