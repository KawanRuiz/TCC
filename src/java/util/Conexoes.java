package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 11132501346
 */
public class Conexoes {
    public Connection getConnection() throws ClassNotFoundException{
       try{
           Class.forName("org.postgresql.Driver");
           return DriverManager.getConnection("jdbc:postgresql://localhost:5432/adega","postgres","root");
       }catch(SQLException e){
           throw new RuntimeException(e);
       } 
    }
    
    
}
