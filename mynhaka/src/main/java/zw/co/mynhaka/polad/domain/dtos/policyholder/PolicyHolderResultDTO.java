package zw.co.mynhaka.polad.domain.dtos.policyholder;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.dtos.BankingDetailsResultDTO;
import zw.co.mynhaka.polad.domain.dtos.MobileMoneyDetailsResultDTO;
import zw.co.mynhaka.polad.domain.dtos.employer.EmployerResultDTO;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorResultDTO;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianResultDTO;
import zw.co.mynhaka.polad.domain.enums.ClientType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PolicyHolderResultDTO {

    private Long id;

    private String title;

    private String firstname;

    private String lastname;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private String idNumber;

    private String gender;

    private String workTelephone;

    private String mobile;

    private String email;

    private EmployerResultDTO employer;

    private String employeeNumber;

    private String occupation;

    private double balance;

    private AddressResultDTO physicalAddress;

    private AddressResultDTO postalAddress;


    private String personType;

    private String clientType;
}
