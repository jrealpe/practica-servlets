/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPostgreSQL {

    /**
     * The Constant driver.
     */
    private static final String driver = "org.postgresql.Driver";
    /**
     * The connect string.
     */
    private static String connectString = "jdbc:postgresql://localhost:5432/espol";;
    /**
     * The Constant user.
     */
    private static final String user = "postgres";
    /**
     * The Constant password.
     */
    private static final String password = "1234";
    
    
    /**
     * Instantiates a new conection sql.
     *
     * @param IP the iP
     */
    public ConnectionPostgreSQL() {
        connect2DB();
    }


    public static Connection connect2DB() {
        try {
            Class.forName(driver);
            
            //Do connection.
            return (DriverManager.getConnection(connectString, user, password));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.err.println("Error on connection to espol " + e.toString());
        }
        return null;
    }

    /**
     * Disconnect DB.
     */
    public static void disconnectDB(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
