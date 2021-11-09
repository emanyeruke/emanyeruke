package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.mynhaka.polad.domain.model.ClaimAccident;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.reports.claims.IAccidentClaimAllReport;
import zw.co.mynhaka.polad.reports.claims.IAccidentClaimMonthlyReport;
import zw.co.mynhaka.polad.reports.claims.IAccidentClaimReport;
import zw.co.mynhaka.polad.repository.dto.ClaimAccidentTotal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClaimAccidentRepository extends JpaRepository<ClaimAccident, Long> {


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
            "from claim_accident\n" +
            "where claim_status = 'PAID' and created_date between ?1 and ?2\n" +
            "group by claimDate, month, year;", nativeQuery = true)
    List<IAccidentClaimReport> countClaimPaymentDuration(LocalDate startDate, LocalDate endDate);


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
            "from claim_accident\n" +
            "where claim_status = 'PAID' \n" +
            "group by month, year;", nativeQuery = true)
    List<IAccidentClaimMonthlyReport> countClaimPaymentDurationByMonth();

    List<ClaimAccident> findByPolicyNumber(String policyNumber);

    //ClaimAccident findByPolicyNumberAndBAndBeneficiary(String policyNumber, Long beneficiaryId);

    @Query(value = "select * from claim_accident where claim_status = ?1", nativeQuery = true)
    Page<ClaimAccident> findAllByClaimStatus(String claimStatus, Pageable pageable);

    ClaimAccident findByIdAndClaimStatus(Long id, String claimStatus);

    List<ClaimAccident> findAllByPolicyHolder(PolicyHolder policyHolder);

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
            "where claim_status = 'PAID' \n", nativeQuery = true)
    IAccidentClaimAllReport countClaimPaymentDuratioFromBeginning();

    @Query(value = "select year(created_date) as year, month(created_date) as month,  sum(amount) as totalPaid from claim_accident where claim_status='PAID' and year(created_date) = year(current_date()) group by year, month", nativeQuery = true)
    List<ClaimAccidentTotal> getSumOfClaimsPaid();
}
