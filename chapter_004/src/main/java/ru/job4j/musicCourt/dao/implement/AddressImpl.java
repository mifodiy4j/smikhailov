package ru.job4j.musicCourt.dao.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.musicCourt.dao.AddressDAO;
import ru.job4j.musicCourt.domain.Address;
import ru.job4j.musicCourt.dao.ExceptionDAO;
import ru.job4j.musicCourt.dao.Factory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressImpl implements AddressDAO {

    private static final Logger Log = LoggerFactory.getLogger(AddressImpl.class);

    private Factory factory = Factory.getInstance();

    @Override
    public void insert(Address address) {

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "insert into addresses(id, country, city, street, house) " +
                        "values(?, ?, ?, ?, ?)"
        )
        ) {
            st.setInt(1, address.getId());
            st.setString(2, address.getCountry());
            st.setString(3, address.getCity());
            st.setString(4, address.getStreet());
            st.setInt(5, address.getHouse());

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
    public void update(Address address) {

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "update addresses set country=?, city=?, street=?, house=? where id=?"
        )
        ) {
            st.setString(1, address.getCountry());
            st.setString(2, address.getCity());
            st.setString(3, address.getStreet());
            st.setInt(4, address.getHouse());
            st.setInt(5, address.getId());

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
    public void delete(int id) {

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "delete from addresses where id=?"
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
    public Address getById(int id) {
        Address address = null;

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement("SELECT country, city, street, house FROM addresses as adr where adr.id = ?");
        ) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()
            ) {

                while (rs.next()) {

                    address = new Address();
                    address.setId(id);
                    address.setCountry(rs.getString("country"));
                    address.setCity(rs.getString("city"));
                    address.setStreet(rs.getString("street"));
                    address.setHouse(rs.getInt("house"));
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
        return address;
    }

    @Override
    public List<Address> getAll() throws ExceptionDAO {
        List<Address> listRole = new ArrayList<>();
        Address address;
        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "SELECT id, country, city, street, house FROM addresses");
             ResultSet rs = st.executeQuery()
        ) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String street = rs.getString("street");
                int house = rs.getInt("street");

                address = new Address();
                address.setId(id);
                address.setCountry(country);
                address.setCity(city);
                address.setStreet(street);
                address.setHouse(house);

                listRole.add(address);
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

    public int getIdByOtherParametr(Address address) {

        int id = 0;

        Connection conn = null;
        try {
            conn = factory.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (PreparedStatement st = conn.prepareStatement(
                "select id from addresses where country=? and city=? and street=? and house=?");
        ) {
            st.setString(1, address.getCountry());
            st.setString(2, address.getCity());
            st.setString(3, address.getStreet());
            st.setInt(4, address.getHouse());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
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
        return id;

    }

}
