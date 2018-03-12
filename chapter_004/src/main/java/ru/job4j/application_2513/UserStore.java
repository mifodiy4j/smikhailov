package ru.job4j.application_2513;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class UserStore {

    private InitialContext ic;
    private DataSource ds;

    Connection conn = null;

    private static final Logger Log = LoggerFactory.getLogger(UserStore.class);

    /**
     * Подключение к базе данных <code>url</code> используя
     * логин <code>username</code> и пароль <code>password</code>
     * @return объект класса Connection - сеанс работы с БД
     * @throws SQLException
     */
    private Connection getConn() throws SQLException, NamingException {

        ic = new InitialContext();
        ds = (DataSource) ic.lookup("java:/comp/env/jdbc/usersServlet");
        return ds.getConnection();
    }

    /**
     * Метод возвращает объект класса <code>User</code> по заданному <code>id</code>
     * @param id искомой записи в БД
     * @return объект класса <code>User</code> с заданным <code>id</code>
     */
    public User selectById(int id) {

        User user = new User();

        try {
            conn = getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
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
            conn = getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
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

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try (PreparedStatement st = conn.prepareStatement(
                "update usersServlet set name = ? where id = ?"
                )
        ) {

            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try (PreparedStatement st = conn.prepareStatement(
                "delete FROM usersServlet where id = ?"
                )
        ){
            st.setInt(1, id);
            st.executeUpdate();

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Возвращает список всех <code>id</code> пользователей в БД
     * @return <code>List<Integer></code>
     */
    public List<Integer> getListId() {

        List<Integer> listId = new ArrayList<>();

        try {
            conn = getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try (PreparedStatement st = conn.prepareStatement(
                "SELECT id FROM usersServlet")
        ) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                listId.add(Integer.parseInt(rs.getString("id")));
            }

            st.executeUpdate();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return listId;
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
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date) formatter.parse(str_date);
            timestamp = new Timestamp(date.getTime());
        } catch(Exception e) {
            Log.error(e.getMessage(), e);
        }
        return timestamp;
    }

}
