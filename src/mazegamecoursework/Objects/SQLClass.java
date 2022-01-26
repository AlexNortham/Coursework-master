
package mazegamecoursework.Objects;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.ResultSet;
import java.sql.Statement;
public class SQLClass {
     private static final String DatabaseLocation = System.getProperty("user.dir") + "\\Database.accdb"; //This creates a string describing the address of the database


    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            return con;
            //This joins the Connection object to the database and returns it

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public static void insert(Connection con, String input){
         try{
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); //Creates a Statement object
            statement.executeUpdate(input); //Executes an SQL update based on the parametric string
            
         }catch(Exception e){
             e.printStackTrace();
             
         }
     }
     

    public static ResultSet query(Connection con, String input){
        try{
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); //Creates a Statement object
            ResultSet rs = statement.executeQuery(input);
            statement.close();
          //  con.close();
            return rs; //Executes an SQL update based on the parametric string and returns the ResultSet from the database
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void update(Connection con, String input){
        try{
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); //Creates a Statement object
            statement.executeUpdate(input); //Executes an SQL update based on the parametric string

        }catch(Exception e){
            e.printStackTrace();

        }
    }


}
