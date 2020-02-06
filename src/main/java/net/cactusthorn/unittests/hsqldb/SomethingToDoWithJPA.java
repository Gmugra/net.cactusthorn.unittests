package net.cactusthorn.unittests.hsqldb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SomethingToDoWithJPA {

    private final EntityManagerFactory factory;

    public SomethingToDoWithJPA(final EntityManagerFactory factory) {
        this.factory = factory;
    }

    public String getCol1() {

        EntityManager em = null;
        try {
            em = factory.createEntityManager();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TestTable> criteriaQuery = cb.createQuery(TestTable.class);
            Root<TestTable> root = criteriaQuery.from(TestTable.class);
            criteriaQuery.select(root);

            TypedQuery<TestTable> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setFirstResult(0);
            typedQuery.setMaxResults(1);
            List<TestTable> results = typedQuery.getResultList();

            return results.get(0).getCol1();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
