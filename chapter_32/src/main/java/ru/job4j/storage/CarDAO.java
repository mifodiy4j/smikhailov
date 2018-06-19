package ru.job4j.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Car;
import java.util.List;
import java.util.Optional;

@Repository
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
}
