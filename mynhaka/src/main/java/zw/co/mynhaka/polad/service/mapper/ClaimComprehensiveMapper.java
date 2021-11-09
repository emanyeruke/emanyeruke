package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ClaimComprehensiveResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimComprehensive;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClaimComprehensiveMapper {
    @IgnoreAuditing
    ClaimComprehensive toClaimComprehensive(DeathClaimCreateDto deathClaimCreateDto);

    ClaimComprehensiveResultDTO toClaimComprehensiveResultDTO(ClaimComprehensive claimComprehensive);
}
