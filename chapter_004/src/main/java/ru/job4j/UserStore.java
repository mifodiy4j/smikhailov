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

    /**
     * Подключение к базе данных <code>url</code> используя
     * логин <code>username</code> и пароль <code>password</code>
     * @return объект класса Connection - сеанс работы с БД
     * @throws SQLException
     */
    private Connection getConn() throws SQLException {
        Connection result;

        if (conn != null) {
            result = conn;
        } else {
            result = DriverManager.getConnection(url, username, password);
        }

        return result;
    }

    /**
     * Метод возвращает объект класса <code>User</code> по заданному <code>id</code>
     * @param id искомой записи в БД
     * @return объект класса <code>User</code> с заданным <code>id</code>
     */
    public User selectById(int id) {

        User user = new User();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = getConn();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM usersServlet as us where us.id = ?")
            ) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getString("create_date"));
            }

            st.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
        return user;
    }

    /**
     * Метод добавляет новую запись в БД
     * @param name - имя пользователя
     * @param login - login пользователя
     * @param email - email пользователя
     * @param create_date дата создания в формате dd/MM/yyyy. Если указано неверно или
     *                    <code>null</code> то текущая дата
     */
    public void add(String name, String login, String email, String create_date) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = getConn();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "insert into usersServlet(name, login, email, create_date)" +
                        "values(?, ?, ?, ?)"/*, Statement.RETURN_GENERATED_KEYS*/
                )
            ){

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
            conn.commit();
            /*ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt("id");
            }*/
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
    }

    /**
     * Присваимвает пользователю с <code>id</code> имя <code>name</code>.
     * @param id пользователя, имя которого необходимо поменять
     * @param name новое имя пользователя
     * @return объект класса <code>User</code> с заданным id после имзменения
     */
    public User updateNameById(int id, String name) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = getConn();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "update usersServlet set name = ? where id = ?"
                )
        ) {

            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return selectById(id);
    }

    /**
     * Удаляет запись в БД по заданному <code>id</code>
     * @param id записи для удаления
     */
    public void deleteById(int id) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = getConn();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "delete FROM usersServlet where id = ?"
                )
        ){
            st.setInt(1, id);
            st.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
    }

    /**
     * Преобразует запись даты из формата <code>String</code>
     * в формат <code>Timestamp</code>
     * @param str_date
     * @return объект класса Timestamp
     */
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
