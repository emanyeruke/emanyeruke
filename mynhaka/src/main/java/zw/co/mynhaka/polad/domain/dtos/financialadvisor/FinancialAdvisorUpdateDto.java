package zw.co.mynhaka.polad.domain.dtos.financialadvisor;

import lombok.Data;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class FinancialAdvisorUpdateDto {

    private Long financialAdvisorId;

    @Size(min = 5, max = 50)
    private String name;

    @Size(min = 5, max = 50)
    private String surname;

    @Email
    private String email;

    @ContactNumberConstraint
    private String contactNumber;

  /* @Size(min = 5, max = 50)
    private String reference;

    */
}
