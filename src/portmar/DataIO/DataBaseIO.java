package portmar.DataIO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseIO {

    static final String DB_URL = "jdbc:derby:db\\test";
    static final String USER = "";
    static final String PASS = "";
    public String query = "";

    public Statement connect() {
        Statement st = null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            st = stmt;
        } catch (SQLException ex) {
        }
        return st;
    }

    public void fetchTable(String query) {
    }

    public void fetchData(String query) {
    }

    public void fetchRows(String query) {
    }

    public void createTable(String query) {
    }

    public void deleteTable(String query) {
    }

    public void insertRows(String query) {
    }

    public void updateRows(String query) {
    }

    public void deleteRows(String query) {
    }
}
