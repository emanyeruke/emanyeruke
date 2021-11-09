package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import zw.co.mynhaka.polad.domain.dtos.claim.DeathClaimCreateDto;
import zw.co.mynhaka.polad.domain.dtos.funeral.ClaimFuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.ClaimFuneral;
import zw.co.mynhaka.polad.service.util.IgnoreAuditing;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClaimFuneralMapper {
    @IgnoreAuditing
    ClaimFuneral toClaimFuneral(DeathClaimCreateDto deathClaimCreateDto);

    ClaimFuneralResultDTO toClaimFuneralResultDTO(ClaimFuneral claimFuneral);
}
