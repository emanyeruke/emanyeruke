package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.BankingDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressCreateDto;
import zw.co.mynhaka.polad.domain.model.Address;
import zw.co.mynhaka.polad.domain.model.BankingDetails;


@Component
public class BankingDetailsCreateDtoToBankingDetails implements Converter<BankingDetailsCreateDTO, BankingDetails> {
    @Override
    public BankingDetails convert(BankingDetailsCreateDTO bankingDetailsCreateDTO) {
        return null;
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
