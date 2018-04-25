package ru.job4j.musicCourt.dao.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.musicCourt.dao.ExceptionDAO;
import ru.job4j.musicCourt.dao.Factory;
import ru.job4j.musicCourt.dao.MusicTypeDAO;
import ru.job4j.musicCourt.domain.MusicType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicTypeImpl implements MusicTypeDAO {

    private static final Logger Log = LoggerFactory.getLogger(MusicTypeImpl.class);

    private Factory factory = Factory.getInstance();

    @Override
    public void insert(MusicType model) throws ExceptionDAO {
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "insert into musicTypes(id, type) " +
                        "values(?, ?)"
        )
        ) {
            st.setInt(1, model.getId());
            st.setString(2, model.getMusicType());

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
    public void update(MusicType model) throws ExceptionDAO {

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "update musicTypes set type=? where id=?"
        )
        ) {
            st.setString(1, model.getMusicType());
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
                "delete from musicTypes where id=?"
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
    public MusicType getById(int id) throws ExceptionDAO {
        MusicType musicType = new MusicType();

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("SELECT type FROM musicTypes where id = ?");
        ) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()
            ) {

                while (rs.next()) {
                    musicType.setId(id);
                    musicType.setMusicType(rs.getString("type"));
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
        return musicType;
    }

    @Override
    public List<MusicType> getAll() throws ExceptionDAO {
        List<MusicType> listMusicType = new ArrayList<>();
        MusicType musicType;
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "SELECT id, type FROM musicTypes");
             ResultSet rs = st.executeQuery()
        ) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");

                musicType = new MusicType();
                musicType.setId(id);
                musicType.setMusicType(type);

                listMusicType.add(musicType);
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
        return listMusicType;
    }
}
