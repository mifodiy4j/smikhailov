package ru.job4j.musicCourt.dao.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.musicCourt.dao.*;
import ru.job4j.musicCourt.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDAO, UserRepository<User>{

    private static final Logger Log = LoggerFactory.getLogger(MusicTypeImpl.class);

    private Factory factory = Factory.getInstance();

    @Override
    public void insert(User model) throws ExceptionDAO {
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "insert into users(id, name, age, address_id, role_id, musictype_id, password)" +
                        "values(?, ?, ?, ?, ?, ?, ?)"
        )
        ) {

            st.setInt(1, model.getId());
            st.setString(2, model.getName());
            st.setInt(3, model.getAge());

            Address address = model.getAdress();
            st.setInt(4, address.getId());

            Role role = model.getRole();
            st.setInt(5, role.getId());

            MusicType musicType = model.getMusicType();
            st.setInt(6, musicType.getId());

            st.setString(7, model.getPassword());

            st.executeUpdate();

            conn.commit();

            factory.close();

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
    }

    @Override
    public void update(User model) throws ExceptionDAO {
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "update users set name=?, age=?, address_id=?, role_id=?, musictype_id=?, password=? where id=?"
        )
        ) {
            st.setString(1, model.getName());
            st.setInt(2, model.getAge());

            Address address = model.getAdress();
            st.setInt(3, address.getId());

            Role role = model.getRole();
            st.setInt(4, role.getId());

            MusicType musicType = model.getMusicType();
            st.setInt(5, musicType.getId());

            st.setString(6, model.getPassword());

            st.setInt(7, model.getId());

            st.executeUpdate();
            conn.commit();
            factory.close();

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
    }

    @Override
    public void delete(int id) throws ExceptionDAO {
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "delete from users where id=?"
        )
        ) {
            st.setInt(1, id);

            st.executeUpdate();
            conn.commit();
            factory.close();

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
    }

    @Override
    public User getById(int id) throws ExceptionDAO {
        User user = null;

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("SELECT name, age, address_id, role_id, musictype_id, password FROM users where id = ?");
        ) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()
            ) {

                while (rs.next()) {

                    user = new User();
                    user.setId(id);
                    user.setName(rs.getString("name"));
                    user.setAge(rs.getInt("age"));

                    int address_id = rs.getInt("address_id");
                    AddressImpl addressImpl = new AddressImpl();
                    Address address = addressImpl.getById(address_id);
                    user.setAdress(address);

                    int role_id = rs.getInt("role_id");
                    RoleImpl roleImpl = new RoleImpl();
                    Role role = roleImpl.getById(role_id);
                    user.setRole(role);

                    int musicType_id = rs.getInt("musictype_id");
                    MusicTypeImpl musicTypeImpl = new MusicTypeImpl();
                    MusicType musicType = musicTypeImpl.getById(musicType_id);
                    user.setMusicType(musicType);

                    user.setPassword(rs.getString("password"));
                }
            }

            conn.commit();
            factory.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() throws ExceptionDAO {
        List<User> listUser = new ArrayList<>();
        User user;
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "SELECT id, name, age, address_id, role_id, musictype_id, password FROM users");
             ResultSet rs = st.executeQuery()
        ) {

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));

                int address_id = rs.getInt("address_id");
                AddressImpl addressImpl = new AddressImpl();
                Address address = addressImpl.getById(address_id);
                user.setAdress(address);

                int role_id = rs.getInt("role_id");
                RoleImpl roleImpl = new RoleImpl();
                Role role = roleImpl.getById(role_id);
                user.setRole(role);

                int musicType_id = rs.getInt("musictype_id");
                MusicTypeImpl musicTypeImpl = new MusicTypeImpl();
                MusicType musicType = musicTypeImpl.getById(musicType_id);
                user.setMusicType(musicType);

                user.setPassword(rs.getString("password"));

                listUser.add(user);
            }

            conn.commit();
            Factory.getInstance().close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
        return listUser;
    }

    @Override
    public List<Model> getFullEntity(Specification specification) throws ExceptionDAO {
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        List<Model> entityList = new ArrayList<>();

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                sqlSpecification.toSqlQuery());
             ResultSet rs = st.executeQuery()
        ) {

            while (rs.next()) {

                int address_id = rs.getInt("address_id");
                AddressImpl addressImpl = new AddressImpl();
                Address address = addressImpl.getById(address_id);
                entityList.add(address);

                int role_id = rs.getInt("role_id");
                RoleImpl roleImpl = new RoleImpl();
                Role role = roleImpl.getById(role_id);
                entityList.add(role);

                int musicType_id = rs.getInt("musictype_id");
                MusicTypeImpl musicTypeImpl = new MusicTypeImpl();
                MusicType musicType = musicTypeImpl.getById(musicType_id);
                entityList.add(musicType);
            }

            conn.commit();
            factory.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
        return entityList;
    }

    @Override
    public void insert(Specification specification) throws ExceptionDAO {

        SqlSpecification sqlSpecification = (SqlSpecification) specification;

        String sql = sqlSpecification.toSqlQuery();

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(sql);
        ) {
            st.executeUpdate();
            conn.commit();
            factory.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }

    }

    @Override
    public User getByParameter(Specification specification) throws ExceptionDAO {

        SqlSpecification sqlSpecification = (SqlSpecification) specification;

        String sql = sqlSpecification.toSqlQuery();

        User user = new User();

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(sql);
        ) {

            try (ResultSet rs = st.executeQuery()
            ) {
                while (rs.next()) {

                    int id = rs.getInt("id");
                    user.setId(id);
                    String name = rs.getString("name");
                    user.setName(name);
                    int age = rs.getInt("age");
                    user.setAge(age);
                    int address_id = rs.getInt("address_id");
                    Address address = new Address(address_id);
                    user.setAdress(address);
                    int role_id = rs.getInt("role_id");
                    Role role = new Role(role_id);
                    user.setRole(role);
                    int musictype_id = rs.getInt("musictype_id");
                    MusicType musicType = new MusicType(musictype_id);
                    user.setMusicType(musicType);
                    String password = rs.getString("password");
                    user.setPassword(password);
                }
            }

            //st.executeUpdate();
            conn.commit();
            factory.close();
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

    public boolean isCredentional(String name, String password) {

        String passwordInDB = "";

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("SELECT password FROM users WHERE name=?")
        ) {

            st.setString(1, name);

            try (ResultSet rs = st.executeQuery()
            ) {

                while (rs.next()) {
                    passwordInDB = rs.getString("password");
                }
            }

            conn.commit();
            factory.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }

        return passwordInDB.equals(password);
    }

}
