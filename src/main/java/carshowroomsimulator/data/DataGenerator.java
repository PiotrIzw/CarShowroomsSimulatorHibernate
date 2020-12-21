package carshowroomsimulator.data;

import carshowroomsimulator.dao.Dao;
import carshowroomsimulator.dao.ShowroomDao;
import carshowroomsimulator.dao.VehicleDao;
import carshowroomsimulator.model.CarShowroom;
import carshowroomsimulator.model.ItemCondition;
import carshowroomsimulator.model.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public final class DataGenerator {

    private static DataGenerator instance = null;

    public static DataGenerator getInstance() {
        if(instance != null){
            System.err.println("Instance already exists!");
        }
        if (instance == null) {
            instance = new DataGenerator();
            return instance;
        }
        return null;
    }

    public void setDatabaseData(){
        Vehicle car1 = new Vehicle("Fiat", "Uno", ItemCondition.NEW, 40000.0, 2020, 180, 1.6, 1, false);
        Vehicle car2 = new Vehicle("Mercedes", "W213", ItemCondition.USED, 20000.0, 2005, 180000, 2.6, 1, false);
        Vehicle car3 = new Vehicle("Toyota", "Supra", ItemCondition.DAMAGED, 30000.0, 2000, 65000, 4.2, 1, true);
        Vehicle car4 = new Vehicle("Ford", "Focus", ItemCondition.USED, 3000.0, 2000, 365000, 1.4, 1, false);
        Vehicle car5 = new Vehicle("Opel", "Corsa", ItemCondition.USED, 900.0, 1995, 155000, 1.2, 1, false);
        Vehicle car6 = new Vehicle("Scoda", "Felicia", ItemCondition.DAMAGED, 30000.0, 2000, 65000, 4.2, 1, false);

        CarShowroom carShowroom1 = new CarShowroom("Cars Showroom 1", 5);
        CarShowroom carShowroom2 = new CarShowroom("Cars Showroom 2", 4);
        CarShowroom carShowroom3 = new CarShowroom("Cars Showroom 3", 30);
        CarShowroom carShowroom4 = new CarShowroom("Cars Showroom 4", 100);
        CarShowroom carShowroom5 = new CarShowroom("Cars Showroom 5", 1);

        List<Vehicle> carShowroom1Cars = new ArrayList<>();
        List<Vehicle> carShowroom2Cars = new ArrayList<>();
        List<Vehicle> carShowroom3Cars = new ArrayList<>();
        List<Vehicle> carShowroom4Cars = new ArrayList<>();
        List<Vehicle> carShowroom5Cars = new ArrayList<>();


        carShowroom1Cars.add(car1);
        carShowroom1Cars.add(car6);



        carShowroom2Cars.add(car2);


        carShowroom3Cars.add(car3);


        carShowroom4Cars.add(car4);

        carShowroom5Cars.add(car5);


        carShowroom1.setCarList(carShowroom1Cars);
        carShowroom2.setCarList(carShowroom2Cars);
        carShowroom3.setCarList(carShowroom3Cars);
        carShowroom4.setCarList(carShowroom4Cars);
        carShowroom5.setCarList(carShowroom5Cars);

//
//        Dao<CarShowroom> carShowroomDao = new ShowroomDao();
//        carShowroomDao.save(carShowroom1);
//        carShowroomDao.save(carShowroom2);
//        carShowroomDao.save(carShowroom3);
//        carShowroomDao.save(carShowroom4);
//        carShowroomDao.save(carShowroom5);
//        carShowroomDao.save(carShowroom6);
//
//        Dao<Vehicle> vehicleDao = new VehicleDao();
//        vehicleDao.save(car1);
//        vehicleDao.save(car2);
//        vehicleDao.save(car3);
//        vehicleDao.save(car4);
//        vehicleDao.save(car5);
//        vehicleDao.save(car6);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(carShowroom1);
            entityManager.persist(carShowroom2);
            entityManager.persist(carShowroom3);
            entityManager.persist(carShowroom4);
            entityManager.persist(carShowroom5);
            //entityManager.persist(car1);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }

    }
}
