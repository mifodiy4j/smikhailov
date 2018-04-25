package ru.job4j.musicCourt.dao.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.musicCourt.dao.*;
import ru.job4j.musicCourt.domain.Model;
import ru.job4j.musicCourt.domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleImpl implements RoleDAO, RoleRepository<Role> {

    private static final Logger Log = LoggerFactory.getLogger(MusicTypeImpl.class);

    private Factory factory = Factory.getInstance();

    @Override
    public void insert(Role model) {
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "insert into roles(id, role) " +
                        "values(?, ?)"
        )
        ) {
            st.setInt(1, model.getId());
            st.setString(2, model.getRole());

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
    public void update(Role model) throws ExceptionDAO {

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "update roles set role=? where id=?"
        )
        ) {
            st.setString(1, model.getRole());
            st.setInt(2, model.getId());

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
                "delete from roles where id=?"
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
    public Role getById(int id) throws ExceptionDAO {
        Role role = new Role();

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("SELECT role FROM roles where id = ?");
        ) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()
            ) {

                while (rs.next()) {
                    role.setId(id);
                    role.setRole(rs.getString("role"));
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
        return role;
    }

    @Override
    public List<Role> getAll() throws ExceptionDAO {
        List<Role> listRole = new ArrayList<>();
        Role role;
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "SELECT id, role FROM roles");
             ResultSet rs = st.executeQuery()
        ) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String roleString = rs.getString("role");

                role = new Role();
                role.setId(id);
                role.setRole(roleString);

                listRole.add(role);
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
        return listRole;
    }

    @Override
    public List<Model> getFullEntity(Specification specification) throws ExceptionDAO {
        return null;
    }
}
