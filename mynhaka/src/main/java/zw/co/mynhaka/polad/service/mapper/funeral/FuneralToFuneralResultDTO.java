package zw.co.mynhaka.polad.service.mapper.funeral;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralResultDTO;
import zw.co.mynhaka.polad.domain.model.Funeral;

import java.util.stream.Collectors;


@Component
public class FuneralToFuneralResultDTO implements Converter<Funeral, FuneralResultDTO> {
    private final FuneralProductToFuneralProductResultDTO toFuneralProductResultDTO;

    public FuneralToFuneralResultDTO(final FuneralProductToFuneralProductResultDTO toFuneralProductResultDTO) {
        this.toFuneralProductResultDTO = toFuneralProductResultDTO;
    }

    @Override
    public FuneralResultDTO convert(Funeral funeral) {

        FuneralResultDTO resultDTO = new FuneralResultDTO();
        resultDTO.setId(funeral.getId());
        resultDTO.setName(funeral.getName());

        if (funeral.getAdminFee() != 0.0) {
            resultDTO.setAdminFee(funeral.getAdminFee());
        }

        if (!funeral.getFuneralProduct().isEmpty()) {
            resultDTO.setFuneralProductResultDTO(
                    funeral.getFuneralProduct()
                            .stream()
                            .map(toFuneralProductResultDTO::convert)
                            .collect(Collectors.toSet()));
        }

        return resultDTO;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
