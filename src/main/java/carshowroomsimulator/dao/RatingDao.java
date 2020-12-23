package carshowroomsimulator.dao;

import carshowroomsimulator.model.CarShowroom;
import carshowroomsimulator.model.Rating;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
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

    public double getAverageRating(long showroomID){
        Optional<CarShowroom> carShowroom = new ShowroomDao().get(showroomID);
//        Rating rating1 = carShowroom.get().getRating().get(0);
//

     //   Rating rating1 = new Rating();

       // CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
       // CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
       // Root<Rating> rating = query.from(Rating.class);

       // ParameterExpression<Rating> param = criteriaBuilder.parameter(Rating.class);
        Double result = (double) entityManager.createQuery("select avg(rating) from Rating r where r.showroom = :showroomID").setParameter("showroomID", carShowroom.get()).getSingleResult();

//        TypedQuery<Double> queryRating = entityManager.createQuery(query);
//
//        ParameterExpression<Rating> param = criteriaBuilder.parameter(Rating.class);
//        query.multiselect(criteriaBuilder.avg(rating.<Number>get("rating")), param).where(criteriaBuilder.equal(rating.get("showroom"), showroomID));
//
//        TypedQuery<Double> queryRating = entityManager.createQuery(query);
//
//        queryRating.setParameter(param, rating1);
//        Double results = queryRating.getSingleResult();
        return result;

       // query.multiselect(rating.get("rating"),criteriaBuilder.count(rating)).groupBy(rating.get("showroom_id"));

    }

    public long getRatingsCount(long showroomID){
        Optional<CarShowroom> carShowroom = new ShowroomDao().get(showroomID);
        long result = (long) entityManager.createQuery("select count(rating) from Rating r where r.showroom = :showroomID").setParameter("showroomID", carShowroom.get()).getSingleResult();

        return result;
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
