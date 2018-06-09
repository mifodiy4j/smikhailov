package ru.job4j.carStorage.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.carStorage.models.Car;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class CarDAO {

    private SessionFactory factory = HibernateFactory.getFactory();
    private static final CarDAO instance = new CarDAO();

    public static CarDAO getInstance() {
        return instance;
    }

    public void save(Car car){
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Car car) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(car);
        session.getTransaction().commit();
        session.close();
    }

    public List<Car> getAll(){
        Session session = factory.openSession();
        session.beginTransaction();
        List<Car> cars = session.createQuery("from Car as c left join fetch c.author " +
                "left join fetch c.body " +
                "left join fetch c.engine " +
                "left join fetch c.transmission").list();
        session.getTransaction().commit();
        session.close();
        return cars;
    }

    public Optional<Car> findById(final int id){
        Session session = factory.openSession();
        session.beginTransaction();
        Optional<Car> result = Optional.empty();
        Car car = session.get(Car.class, id);
        if (car != null) {
            result = Optional.of(car);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Car> findWithFoto(){
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Car as c left join fetch c.author " +
                "left join fetch c.body " +
                "left join fetch c.engine " +
                "left join fetch c.transmission " +
                "where c.foto is not null");
        List<Car> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Car> findForLastDay(){
        Session session = factory.openSession();
        session.beginTransaction();
        long now = System.currentTimeMillis();
        long nowMinus1Day = now - (24L * 60L * 60L * 1000L);
        Timestamp nowMinus1DayAsTimestamp = new Timestamp(nowMinus1Day);

        Query query = session.createQuery("from Car as c left join fetch c.author " +
                "left join fetch c.body " +
                "left join fetch c.engine " +
                "left join fetch c.transmission " +
                "where c.created >= :limit");
        query.setParameter("limit", nowMinus1DayAsTimestamp);
        List<Car> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<String> getListBrand(){
        Session session = factory.openSession();
        session.beginTransaction();
        List<String> brands = session.createQuery("select c.brand from Car as c " +
                "group by brand order by brand").list();
        session.getTransaction().commit();
        session.close();
        return brands;
    }

    public List<Car> findWithSpecificBrand(String brand){
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Car as c left join fetch c.author " +
                "left join fetch c.body " +
                "left join fetch c.engine " +
                "left join fetch c.transmission " +
                "where c.brand = :brand");
        query.setParameter("brand", brand);
        List<Car> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
