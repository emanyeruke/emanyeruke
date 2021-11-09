package zw.co.mynhaka.polad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.mynhaka.polad.domain.model.ClaimFuneral;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.reports.claims.IFuneralClaimReport;
import zw.co.mynhaka.polad.repository.dto.ClaimFuneralTotal;

import java.time.LocalDate;
import java.util.List;

public interface ClaimFuneralRepository extends JpaRepository<ClaimFuneral, Long> {

    List<ClaimFuneral> findAllByPolicyHolder(PolicyHolder policyHolder);

    List<ClaimFuneral> findByPolicyNumber(String policyNumber);

    @Query(value = "select year(created_date) as year, month(created_date) as month, sum(amount) as totalPaid from claim_funeral where claim_status='PAID' and and year(created_date) = year(current_date()) group by year, month", nativeQuery = true)
    List<ClaimFuneralTotal> getSumOfClaimsPaid();

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
            "from claim_funeral\n" +
            "where claim_status = 'PAID' and created_date between ?1 and ?2\n" +
            "group by claimDate, month, year;", nativeQuery = true)
    List<IFuneralClaimReport> countClaimPaymentDuration(LocalDate startDate, LocalDate endDate);


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
            "from claim_funeral\n" +
            "where claim_status = 'PAID' \n" +
            "group by month, year;", nativeQuery = true)
    List<IFuneralClaimReport> countClaimPaymentDurationByMonth();

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
            "from claim_funeral\n" +
            "where claim_status = 'PAID' \n", nativeQuery = true)
    IFuneralClaimReport countClaimPaymentDuratioFromBeginning();

    Page<ClaimFuneral> findAllByClaimStatus(String status, PageRequest of);
}
