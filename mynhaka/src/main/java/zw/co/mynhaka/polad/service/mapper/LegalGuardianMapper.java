package zw.co.mynhaka.polad.service.mapper;

import org.mapstruct.Mapper;
import zw.co.mynhaka.polad.domain.dtos.legalguardian.LegalGuardianCreateDto;
import zw.co.mynhaka.polad.domain.model.LegalGuardian;

@Mapper(componentModel = "spring")
public interface LegalGuardianMapper {
    LegalGuardian toLegalGuardian(LegalGuardianCreateDto legalGuardianCreateDto);
}
