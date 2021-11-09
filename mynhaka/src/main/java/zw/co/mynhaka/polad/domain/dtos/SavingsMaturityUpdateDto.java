package zw.co.mynhaka.polad.domain.dtos;

import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.MaturityStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SavingsMaturityUpdateDto {
    Long id;

    private String policyNumber;

    private LocalDate effectiveDate;

    private String identityDocument;

    // private BankingDetailsResultDTO bankingDetails;

    private BigDecimal maturityValue;

    private MaturityStatus maturityStatus;
}
