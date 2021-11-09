package zw.co.mynhaka.polad.service.iface.reports;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.repository.ClaimAccidentRepository;
import zw.co.mynhaka.polad.repository.ClaimComprehensiveRepository;
import zw.co.mynhaka.polad.repository.ClaimFuneralRepository;
import zw.co.mynhaka.polad.repository.dto.ClaimAccidentTotal;
import zw.co.mynhaka.polad.repository.dto.ClaimComprehensiveTotal;
import zw.co.mynhaka.polad.repository.dto.ClaimFuneralTotal;
import zw.co.mynhaka.polad.service.iface.reports.dtos.DeathClaimsPerProductResult;
import zw.co.mynhaka.polad.service.iface.reports.dtos.MonthlyReportResult;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class DeathClaimsReportServiceImpl implements DeathClaimsReportService {


    private final ClaimAccidentRepository claimAccidentRepository;
    private final ClaimComprehensiveRepository claimComprehensiveRepository;
    private final ClaimFuneralRepository claimFuneralRepository;


    public DeathClaimsReportServiceImpl(ClaimAccidentRepository claimAccidentRepository,
                                        ClaimComprehensiveRepository claimComprehensiveRepository,
                                        ClaimFuneralRepository claimFuneralRepository) {
        this.claimAccidentRepository = claimAccidentRepository;
        this.claimComprehensiveRepository = claimComprehensiveRepository;
        this.claimFuneralRepository = claimFuneralRepository;

    }


    @Override
    public List<MonthlyReportResult> getMonthlyReport() {

        Stream<ClaimAccidentTotal> accidentClaimsPaidTotal = claimAccidentRepository.getSumOfClaimsPaid().stream();
        Stream<ClaimComprehensiveTotal> comprehensiveClamsPaidTotal = claimComprehensiveRepository.getSumOfClaimsPaid().stream();
        Stream<ClaimFuneralTotal> funeralClaimsPaidTotal = claimFuneralRepository.getSumOfClaimsPaid().stream();

        List<MonthlyReportResult> monthlyReportResultsList = new ArrayList<>();
        MonthlyReportResult mon = new MonthlyReportResult();

        for (int i = 1; i < 13; i++) {
            int finalI = i;
            List<ClaimAccidentTotal> claimAccidentTotal = accidentClaimsPaidTotal
                    .filter(claimAccidentTotal1 -> claimAccidentTotal1.getMonth() == finalI)
                    .collect(Collectors.toList());
            List<ClaimComprehensiveTotal> claimComprehensiveTotal = comprehensiveClamsPaidTotal
                    .filter(claimComprehensiveTotal1 -> claimComprehensiveTotal1.getMonth() == finalI)
                    .collect(Collectors.toList());
            List<ClaimFuneralTotal> claimFuneralTotal = funeralClaimsPaidTotal
                    .filter(claimFuneralTotal1 -> claimFuneralTotal1.getMonth() == finalI)
                    .collect(Collectors.toList());
            MonthlyReportResult monthlyReportResult = new MonthlyReportResult();
            monthlyReportResult.setMonth(Month.of(i).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH));
            monthlyReportResult.setInsuranceClaims(claimAccidentTotal.get(i - 1).getTotalPaid().add(claimComprehensiveTotal.get(i - 1).getTotalPaid().add(claimFuneralTotal.get(i - 1).getTotalPaid())));
            monthlyReportResultsList.add(monthlyReportResult);
        }

        return monthlyReportResultsList;
    }

    @Override
    public DeathClaimsPerProductResult getDeathClaimsPerProduct() {

        return null;
    }
}
