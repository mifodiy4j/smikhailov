package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.SimpleDateFormat;

public class UserStore {

    String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
    String username = "postgres";
    String password = "root";
    Connection conn = null;

    private static final Logger Log = LoggerFactory.getLogger(UserStore.class);

    private static final UserStore instance = new UserStore();

    public static UserStore getInstance() {
        return instance;
    }

    public String selectById(int id) {

        StringBuilder sb = new StringBuilder();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(
                    "SELECT * FROM usersServlet as us where us.id = ?"
            );
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                sb.append(rs.getString("name"));
                sb.append(" ");
                sb.append(rs.getString("login"));
                sb.append(" ");
                sb.append(rs.getString("email"));
                sb.append(" ");
                sb.append(rs.getString("create_date"));
            }

            st.executeUpdate();
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
        return sb.toString();
    }

    public void add(String name, String login, String email, String create_date) {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(
                    "insert into usersServlet(name, login, email, create_date)" +
                            "values(?, ?, ?, ?)"/*, Statement.RETURN_GENERATED_KEYS*/
            );

            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);

            Timestamp ts;
            ts = convertStringToTimestamp(create_date);
            if (ts != null) {
                st.setTimestamp(4, ts);
            } else {
                st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }

            st.executeUpdate();
            /*ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt("id");
            }*/
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

    public int updateNameById(int id, String name) {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(
                    "update usersServlet set name = ? where id = ?"
            );
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();

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
        return 0;
    }

    public void deleteById(int id) {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(
                    "delete FROM usersServlet where id = ?"
            );
            st.setInt(1, id);
            st.executeUpdate();

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

    public static Timestamp convertStringToTimestamp(String str_date) {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = dateFormat.parse(str_date);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) {
            Log.error(e.getMessage(), e);
        }
        return timestamp;
    }

}
