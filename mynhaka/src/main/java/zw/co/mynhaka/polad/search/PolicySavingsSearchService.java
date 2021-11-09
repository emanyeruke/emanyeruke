package zw.co.mynhaka.polad.search;


import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicySavingsResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicySavings;
import zw.co.mynhaka.polad.service.mapper.policies.PolicySavingsToPolicySavingsDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicySavingsSearchService {

    private final EntityManager entityManager;

    @Autowired
    private PolicySavingsToPolicySavingsDTO toPolicySavingsDTO;

    @Autowired
    public PolicySavingsSearchService(EntityManagerFactory entityManagerFactory) {
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
    public List<PolicySavingsResultDTO> fuzzySearch(String searchTerm, int page, int size) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(PolicySavings.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("policyNumber")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, PolicySavings.class);
        /*jpaQuery.setFirstResult(page);
        jpaQuery.setMaxResults(size);*/
        // execute search

        List<PolicySavings> policySavingsList = null;
        try {
            policySavingsList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }

        return policySavingsList.stream()
                .map(toPolicySavingsDTO::convert)
                .collect(Collectors.toList());


    }
}
