package zw.co.mynhaka.polad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import zw.co.mynhaka.polad.search.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/*@EnableAutoConfiguration
@Configuration*/
public class HibernateSearchConfiguration {


    private final EntityManager entityManager;


    @Autowired
    public HibernateSearchConfiguration(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Bean
    PolicyHolderSearchService hibernateSearchService() {
        PolicyHolderSearchService policyHolderSearchService = new PolicyHolderSearchService(entityManager.getEntityManagerFactory());
        policyHolderSearchService.initializeHibernateSearch();
        return policyHolderSearchService;
    }

    @Bean
    PolicyAccidentSearchService hibernateAccidentSearchService() {
        PolicyAccidentSearchService policyAccidentSearchService = new PolicyAccidentSearchService(entityManager.getEntityManagerFactory());
        policyAccidentSearchService.initializeHibernateSearch();
        return policyAccidentSearchService;
    }

    @Bean
    PolicyFuneralSearchService hibernateFuneralSearchService() {
        PolicyFuneralSearchService policyFuneralSearchService = new PolicyFuneralSearchService(entityManager.getEntityManagerFactory());
        policyFuneralSearchService.initializeHibernateSearch();
        return policyFuneralSearchService;
    }

    @Bean
    PolicySavingsSearchService hibernateSavingsSearchService() {
        PolicySavingsSearchService policySavingsSearchService = new PolicySavingsSearchService(entityManager.getEntityManagerFactory());
        policySavingsSearchService.initializeHibernateSearch();
        return policySavingsSearchService;
    }

    @Bean
    PolicyComprehensiveSearchService hibernateComprehensiveSearchService() {
        PolicyComprehensiveSearchService policyComprehensiveSearchService = new PolicyComprehensiveSearchService(entityManager.getEntityManagerFactory());
        policyComprehensiveSearchService.initializeHibernateSearch();
        return policyComprehensiveSearchService;
    }


}
