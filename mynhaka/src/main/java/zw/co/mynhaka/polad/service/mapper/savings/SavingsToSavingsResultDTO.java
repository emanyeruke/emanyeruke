package zw.co.mynhaka.polad.service.mapper.savings;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.savings.SavingsResultDTO;
import zw.co.mynhaka.polad.domain.model.Savings;

import java.util.stream.Collectors;


@Component
public class SavingsToSavingsResultDTO implements Converter<Savings, SavingsResultDTO> {

    private final SavingsProductToSavingsProductResultDTO toSavingsProductResultDTO;

    public SavingsToSavingsResultDTO(final SavingsProductToSavingsProductResultDTO toSavingsProductResultDTO) {
        this.toSavingsProductResultDTO = toSavingsProductResultDTO;
    }

    @Override
    public SavingsResultDTO convert(Savings savings) {

        SavingsResultDTO resultDTO = new SavingsResultDTO();
        resultDTO.setAdminFee(savings.getAdminFee());
        resultDTO.setId(savings.getId());
        resultDTO.setName(savings.getName());

        if (!savings.getSavingsProduct().isEmpty()) {
            resultDTO.setSavingsProductResultDTO(
                    savings.getSavingsProduct()
                            .stream()
                            .map(toSavingsProductResultDTO::convert)
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
