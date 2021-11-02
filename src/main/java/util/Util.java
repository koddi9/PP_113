package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static String dbURL="jdbc:mysql://localhost:3306/dbfor_113?useSSL=false";
    private static String dbUsername="root";
    private static String dbPassword="localdb";


    public static Connection getConnection(){
        Connection connecteion = null;
        try{
            connecteion= DriverManager.getConnection(dbURL,dbUsername,dbPassword);
            connecteion.setAutoCommit(false);
        }
        catch(SQLException sqlE){
            sqlE.printStackTrace();
        }
        return connecteion;
    }
}
