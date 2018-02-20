package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserStore {

    private static final Logger Log = LoggerFactory.getLogger(UserStore.class);

    String url = "jdbc:postgresql://localhost:5432/java_a_from_z";
    String username = "postgres";
    String password = "root";
    Connection conn = null;

    public String selectById(int id) {

        StringBuilder sb = new StringBuilder();

        try {
            Class.forName("com.example.jdbc.Driver");
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

    public int add(String name, String login, String email, String create_date) {

        try {
            Class.forName("com.example.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(
                    "insert into usersServlet(name, login, email, create_date)" +
                            "values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            Timestamp ts = convertStringToTimestamp(create_date);
            if (ts != null) {
                st.setTimestamp(4, ts);
            } else {
                st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }

            //st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt("id");
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
        return -1;
    }

    public int updateNameById(int id, String name) {

        try {
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

    public int deleteById(int id) {

        try {
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
        return 0;
    }

    public static Timestamp convertStringToTimestamp(String str_date) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = (Date) formatter.parse(str_date);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

}
