package pl.sda.arppl4.hiebranterental.dao;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.arppl4.hiebranterental.model.Car;
import pl.sda.arppl4.hiebranterental.util.HibernateUtill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDao implements ICarDao {
    @Override
    public void addCar(Car car) {
        SessionFactory fabrykaPolaczen = HibernateUtill.getSessionFactory();

        Transaction transaction = null;

        try (Session session = fabrykaPolaczen.openSession()) {
            transaction = session.beginTransaction();

            session.merge(car);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void deleteCar(Car car) {
        SessionFactory fabrykaPolaczen = HibernateUtill.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.remove(car);

            transaction.commit();
        }

    }

    @Override
    public Optional<Car> returnCar(Long id) {

        SessionFactory fabrykaPolaczen = HibernateUtill.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            Car obiektCar = session.get(Car.class, id);
            return Optional.ofNullable(obiektCar);
//            if (obiektCar == null){
//                return Optional.empty();
//            }else if(obiektCar!=null){
//                return Optional.of(obiektCar);
//            }
        }
    }

    @Override
    public List<Car> returnListCars() {
        List<Car> listCars = new ArrayList<>();

        SessionFactory fabrykaPolaczen = HibernateUtill.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            TypedQuery<Car> zapytanie = session.createQuery("from Car", Car.class);
            List<Car> wynikZapytania = zapytanie.getResultList();

            listCars.addAll(wynikZapytania);
        } catch (SessionException sessionException) {
            System.err.println("Błąd wczytywania danych.");
        }
        return listCars;
    }

    @Override
    public void updateCar(Car car) {

        SessionFactory fabrykaPolaczen = HibernateUtill.getSessionFactory();

        Transaction transaction = null;
        try (Session session = fabrykaPolaczen.openSession()) {
            transaction = session.beginTransaction();

            session.merge(car);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }
}



