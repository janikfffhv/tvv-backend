package at.fhv.tvv.backend;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
public class HibernateService {

    private static EntityManager entityManager;


    public static EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("Factory").createEntityManager();

        }
        return entityManager;
    }
}
