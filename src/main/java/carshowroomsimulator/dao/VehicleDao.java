package carshowroomsimulator.dao;

import carshowroomsimulator.model.CarShowroom;
import carshowroomsimulator.model.Vehicle;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class VehicleDao implements Dao<Vehicle>{
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Optional<Vehicle> get(long id) {
        return Optional.ofNullable(entityManager.find(Vehicle.class, id));
    }

    @Override
    public List<Vehicle> getAll() {
        Query query = entityManager.createQuery("SELECT v FROM Vehicle v");
        return query.getResultList();
    }

    @Override
    public void save(Vehicle vehicle) {
        executeInsideTransaction(new Consumer<EntityManager>() {
            @Override
            public void accept(EntityManager entityManager) {
                entityManager.persist(vehicle);
            }
        });
    }

    @Override
    public void update(Vehicle vehicle, String[] params) {
//        vehicle.setBrand(Objects.requireNonNull(params[0], "Brand cannot be null"));
//        vehicle.setModel(Objects.requireNonNull(params[1], "Model cannot be null"));
//        executeInsideTransaction(entityManager -> entityManager.merge(vehicle))
    }

    @Override
    public void delete(Vehicle vehicle) {
        executeInsideTransaction(entityManager -> entityManager.remove(vehicle));
    }

    public Vehicle searchCar(String carBrand){
        Query query = entityManager.createQuery("select v from Vehicle v where v.brand = :carBrand");
        query.setParameter("carBrand", carBrand);
        return (Vehicle) query.getSingleResult();
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {

        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
            entityManager.close();
            entityManagerFactory.close();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
