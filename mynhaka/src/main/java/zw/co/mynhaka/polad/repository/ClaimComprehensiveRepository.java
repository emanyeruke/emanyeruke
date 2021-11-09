package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.mynhaka.polad.domain.model.ClaimComprehensive;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.reports.claims.IComprehensiveClaimReport;
import zw.co.mynhaka.polad.repository.dto.ClaimComprehensiveTotal;

import java.time.LocalDate;
import java.util.List;

public interface ClaimComprehensiveRepository extends JpaRepository<ClaimComprehensive, Long> {


    List<ClaimComprehensive> findAllByPolicyHolder(PolicyHolder policyHolder);

    List<ClaimComprehensive> findByPolicyNumber(String policyNumber);

    @Query(value = "select year(created_date) as year, month(created_date) as month,  sum(amount) as totalPaid from claim_comprehensive where claim_status='PAID' and year(created_date) = year(current_date()) group by year, month", nativeQuery = true)
    List<ClaimComprehensiveTotal> getSumOfClaimsPaid();


    @Query(value = "select date(created_date) as claimDate,\n" +
            "       month(created_date) as month,\n" +
            "       year(created_date) as year,\n" +

            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 30 then claim_status\n" +
            "                 else null end) as firstTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 30 and\n" +
            "                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 60 then claim_status\n" +
            "                 else null end) as secondTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 60 and\n" +
            "                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 240 then claim_status\n" +
            "                 else null end) as thirdTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 240 then claim_status\n" +
            "                 else null end) as fourthTier\n" +
            "\n" +
            "from claim_comprehensive\n" +
            "where claim_status = 'PAID' and created_date between ?1 and ?2\n" +
            "group by claimDate, month, year;", nativeQuery = true)
    List<IComprehensiveClaimReport> countClaimPaymentDuration(LocalDate startDate, LocalDate endDate);


    @Query(value = "select  month(created_date) as month,\n" +
            "       year(created_date) as year,\n" +

            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 30 then claim_status\n" +
            "                 else null end) as firstTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 30 and\n" +
            "                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 60 then claim_status\n" +
            "                 else null end) as secondTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 60 and\n" +
            "                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 240 then claim_status\n" +
            "                 else null end) as thirdTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 240 then claim_status\n" +
            "                 else null end) as fourthTier\n" +
            "\n" +
            "from claim_comprehensive\n" +
            "where claim_status = 'PAID' \n" +
            "group by month, year;", nativeQuery = true)
    List<IComprehensiveClaimReport> countClaimPaymentDurationByMonth();

    @Query(value = "select  count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 30 then claim_status\n" +
            "                 else null end) as firstTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 30 and\n" +
            "                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 60 then claim_status\n" +
            "                 else null end) as secondTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 60 and\n" +
            "                      TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) < 240 then claim_status\n" +
            "                 else null end) as thirdTier,\n" +
            "       count(case\n" +
            "                 when TIMESTAMPDIFF(MINUTE, created_date, last_modified_date) > 240 then claim_status\n" +
            "                 else null end) as fourthTier\n" +
            "\n" +
            "from claim_accident\n" +
            "where claim_comprehensive = 'PAID' \n", nativeQuery = true)
    IComprehensiveClaimReport countClaimPaymentDuratioFromBeginning();

    Page<ClaimComprehensive> findAllByClaimStatus(String status, PageRequest of);
}
