package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveCreateDto;
import zw.co.mynhaka.polad.domain.dtos.policyholderproduct.PolicyComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.model.Agent;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneralProduct;
import zw.co.mynhaka.polad.domain.model.PolicyComprehensive;
import zw.co.mynhaka.polad.domain.model.PolicyHolder;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PolicyComprehensiveMapper {

    @IgnoreAuditing
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicationForm_url", source = "policyComprehensiveCreateDto.applicationForm_url")
    @Mapping(target = "nextInvoicingDate", source = "policyComprehensiveCreateDto.commencementDate", qualifiedByName = "nextInvoicingDate")
    PolicyComprehensive toPolicyComprehensive(
            PolicyComprehensiveCreateDto policyComprehensiveCreateDto,
            ComprehensiveFuneralProduct comprehensiveFuneralProduct,
            PolicyHolder policyHolder, Agent agent);

    @Mapping(target = "agent", expression = "java(policyComprehensive.getAgent().getName() + \" \" + policyComprehensive.getAgent().getSurname())")
    PolicyComprehensiveResultDTO toPolicyComprehensiveResultDTO(PolicyComprehensive policyComprehensive);

    @Named("nextInvoicingDate")
    static LocalDate nextInvoicingDate(LocalDate commencementDate) {
        return commencementDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
    }
}
