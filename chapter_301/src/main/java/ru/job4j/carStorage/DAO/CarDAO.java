package ru.job4j.carStorage.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.carStorage.models.Car;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class CarDAO {

    private Session session;

    private Transaction transaction;

    public static final CarDAO instance = new CarDAO();

    public static CarDAO getInstance() {
        return instance;
    }

    public Session openCurrentSessionwithTransaction() {
        SessionFactory factory = getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeCurrentSessionwithTransaction() {
        transaction.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        return HibernateFactory.getFactory();
    }

    public Session getCurrentSession() {
        return session;
    }

    public void save(Car car){
        getCurrentSession().save(car);
    }

    public void update(Car car) {
        getCurrentSession().update(car);
    }

    public List<Car> getAll(){
        List<Car> cars = getCurrentSession().createQuery("from Car as c left join fetch c.author " +
                "left join fetch c.body " +
                "left join fetch c.engine " +
                "left join fetch c.transmission").list();
        return cars;
    }

    public Optional<Car> findById(final int id){
        Optional<Car> result = Optional.empty();
        Car car = getCurrentSession().get(Car.class, id);
        if (car != null) {
            result = Optional.of(car);
        }
        return result;
    }

    public List<Car> findWithFoto(){
        Query query = getCurrentSession().createQuery("from Car as c left join fetch c.author " +
                "left join fetch c.body " +
                "left join fetch c.engine " +
                "left join fetch c.transmission " +
                "where c.foto is not null");
        List<Car> result = query.list();
        return result;
    }

    public List<Car> findForLastDay(){
        long now = System.currentTimeMillis();
        long nowMinus1Day = now - (24L * 60L * 60L * 1000L);
        Timestamp nowMinus1DayAsTimestamp = new Timestamp(nowMinus1Day);

        Query query = getCurrentSession().createQuery("from Car as c left join fetch c.author " +
                "left join fetch c.body " +
                "left join fetch c.engine " +
                "left join fetch c.transmission " +
                "where c.created >= :limit");
        query.setParameter("limit", nowMinus1DayAsTimestamp);
        List<Car> result = query.list();
        return result;
    }

    public List<String> getListBrand(){
        List<String> brands = getCurrentSession().createQuery("select c.brand from Car as c " +
                "group by brand order by brand").list();
        return brands;
    }

    public List<Car> findWithSpecificBrand(String brand){
        Query query = getCurrentSession().createQuery("from Car as c left join fetch c.author " +
                "left join fetch c.body " +
                "left join fetch c.engine " +
                "left join fetch c.transmission " +
                "where c.brand = :brand");
        query.setParameter("brand", brand);
        List<Car> result = query.list();
        return result;
    }
}
