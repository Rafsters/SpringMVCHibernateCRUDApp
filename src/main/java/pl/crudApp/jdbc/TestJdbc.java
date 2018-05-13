package pl.crudApp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class TestJdbc {
    public static void main(String[] args) {


        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String user = "springstudent";
        String password = "springstudent";


        try{
            System.out.println("Connectting to database: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl,user,password);
            System.out.println("Connection successful");


        }catch (Exception exc){
            exc.printStackTrace();
        }

    }
}
