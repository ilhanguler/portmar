package portmar.DataIO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBIO {

    static boolean testConnetion(String DB_URL, String USER, String PASS) {
        boolean isConnected = false;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
            isConnected = true;
            conn.close();
        } catch (SQLException ex) {
        }
        return isConnected;
    }
    
}
