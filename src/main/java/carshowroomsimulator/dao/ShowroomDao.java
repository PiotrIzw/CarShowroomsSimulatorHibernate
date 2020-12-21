package carshowroomsimulator.dao;

import carshowroomsimulator.model.CarShowroom;
import carshowroomsimulator.model.Vehicle;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ShowroomDao implements Dao<CarShowroom> {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public Optional<CarShowroom> get(long id) {
        return Optional.ofNullable(entityManager.find(CarShowroom.class, id));
    }

    @Override
    public List<CarShowroom> getAll() {
        Query query = entityManager.createQuery("SELECT cs FROM CarShowroom cs");
        return query.getResultList();
    }

    @Override
    public void save(CarShowroom carShowroom) {
        executeInsideTransaction(entityManager -> entityManager.persist(carShowroom));
    }

    @Override
    public void update(CarShowroom carShowroom, String[] params) {
//        vehicle.setBrand(Objects.requireNonNull(params[0], "Brand cannot be null"));
//        vehicle.setModel(Objects.requireNonNull(params[1], "Model cannot be null"));
//        executeInsideTransaction(entityManager -> entityManager.merge(vehicle))
    }

    @Override
    public void delete(CarShowroom carShowroom) {
        executeInsideTransaction(entityManager -> entityManager.remove(carShowroom));
    }

    public void reserveCar(Vehicle vehicle){
        vehicle.setReserved(true);
        executeInsideTransaction(entityManager -> entityManager.merge(vehicle));
    }

    public CarShowroom findShowroomByName(String name){
        Query query = entityManager.createQuery("select cs from CarShowroom cs where cs.showroom_name = :name");
        return (CarShowroom) query.getSingleResult();
    }

    public void getProduct(Vehicle vehicle) {
        if(vehicle.getAmount() > 1){
            vehicle.setAmount(vehicle.getAmount() - 1);
            executeInsideTransaction(entityManager -> entityManager.merge(vehicle));
        }
        else{
            executeInsideTransaction(entityManager -> entityManager.remove(vehicle));
        }


    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {


        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

}
