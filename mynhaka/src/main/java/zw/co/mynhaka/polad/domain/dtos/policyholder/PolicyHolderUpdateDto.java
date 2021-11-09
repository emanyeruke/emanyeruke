package zw.co.mynhaka.polad.domain.dtos.policyholder;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.mynhaka.polad.domain.dtos.BankDetailsCreateDto;
import zw.co.mynhaka.polad.domain.dtos.MobileMoneyDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.financialadvisor.FinancialAdvisorCreateDto;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianCreateDto;
import zw.co.mynhaka.polad.domain.enums.ClientType;
import zw.co.mynhaka.polad.domain.enums.Gender;
import zw.co.mynhaka.polad.domain.enums.PremiumPayer;
import zw.co.mynhaka.polad.domain.enums.Title;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;
import zw.co.mynhaka.polad.validation.IdNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PolicyHolderUpdateDto {

    private long policyHolderId;


    @NotNull(message = "Please select title")
    private Title title;


    @Size(max = 32)
    private String firstname;


    @Size(max = 32)
    private String lastname;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;


   // @IdNumberConstraint
    private String idNumber;

    private Gender gender;

    @ContactNumberConstraint
    private String workTelephone;


    @ContactNumberConstraint
    private String mobile;

    @Email
    private String email;


    @Size(max = 32)
    private String employeeNumber;

    private String occupation;

    //private double balance;


    AddressCreateDto physicalAddress;

    AddressCreateDto postalAddress;

    private Long employerId;


    private PremiumPayer premiumPayer;

    private ClientType clientType;
}
