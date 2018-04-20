package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectToDb {

    private static final Logger Log = LoggerFactory.getLogger(UserStore.class);

    String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
    String username = "postgres";
    String password = "postgres";
    Connection conn = null;

    public void select() {

        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(
                    "SELECT * FROM usersServlet;"
            );

            ResultSet rs = st.executeQuery();
            //st.executeUpdate();

            while (rs.next()) {
                System.out.println("1");
            }

            rs.close();
            st.close();

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void main(String[] args) {
        ConnectToDb connectToDb = new ConnectToDb();
        connectToDb.select();
    }

}
