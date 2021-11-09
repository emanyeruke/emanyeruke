package zw.co.mynhaka.polad.search;


import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyAccidentResultDTO;
import zw.co.mynhaka.polad.domain.model.PolicyAccident;
import zw.co.mynhaka.polad.service.mapper.policies.PolicyAccidentToPolicyAccidentDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyAccidentSearchService {

    private final EntityManager entityManager;

    @Autowired
    private PolicyAccidentToPolicyAccidentDTO toPolicyAccidentDTO;

    @Autowired
    public PolicyAccidentSearchService(EntityManagerFactory entityManagerFactory) {
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
    public List<PolicyAccidentResultDTO> fuzzySearch(String searchTerm, int page, int size) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(PolicyAccident.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("policyNumber")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, PolicyAccident.class);
        /*jpaQuery.setFirstResult(page); //start from the 15th element
        jpaQuery.setMaxResults(size); //return 10 elements*/

        // execute search

        List<PolicyAccident> policyAccidentList = null;
        try {
            policyAccidentList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }

        return policyAccidentList.stream()
                .map(toPolicyAccidentDTO::convert)
                .collect(Collectors.toList());


    }
}
