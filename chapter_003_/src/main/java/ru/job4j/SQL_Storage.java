package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SQL_Storage {


    private static final Logger Log = LoggerFactory.getLogger(SQL_Storage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
        String username = "postgres";
        String password = "root";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("insert into users(login, password, create_date) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "rootz");
            st.setString(2, "rootz");
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();

            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
            }

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
}
