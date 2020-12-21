package carshowroomsimulator.dao;

import carshowroomsimulator.model.Rating;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RatingDao implements Dao<Rating> {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Optional<Rating> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Rating> getAll() {
        return null;
    }

    @Override
    public void save(Rating rating) {
        executeInsideTransaction(entityManager -> entityManager.persist(rating));
    }

    @Override
    public void update(Rating rating, String[] params) {

    }

    @Override
    public void delete(Rating rating) {

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
