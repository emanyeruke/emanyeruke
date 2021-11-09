package zw.co.mynhaka.polad.search;


import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mynhaka.polad.domain.dtos.policyholder.PolicyHolderResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.mapper.policyholder.PolicyHolderToPolicyHolderResultDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyHolderSearchService {


    private final EntityManager entityManager;

    @Autowired
    private PolicyHolderToPolicyHolderResultDTO toPolicyHolderResultDTO;


    @Autowired
    public PolicyHolderSearchService(final EntityManagerFactory entityManagerFactory) {
        super();
        this.entityManager = entityManagerFactory.createEntityManager();

    }


    public void initializeHibernateSearch() {

        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<PolicyHolderResultDTO> fuzzySearch(String searchTerm, int page, int size) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(PolicyHolder.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("firstname", "lastname",
                "idNumber", "mobile", "email", "employeeNumber", "occupation")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, PolicyHolder.class);
        jpaQuery.setFirstResult(page);
        jpaQuery.setMaxResults(size);
        // execute search

        List<PolicyHolder> policyHolderList = null;
        try {
            policyHolderList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }

        return policyHolderList.stream()
                .map(toPolicyHolderResultDTO::convert)
                .collect(Collectors.toList());


    }
}
