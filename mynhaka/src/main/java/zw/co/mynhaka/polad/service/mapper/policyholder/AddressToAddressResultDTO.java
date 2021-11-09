package zw.co.mynhaka.polad.service.mapper.policyholder;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.policyholder.AddressResultDTO;
import zw.co.mynhaka.polad.domain.model.Address;


@Component
public class AddressToAddressResultDTO implements Converter<Address, AddressResultDTO> {
    @Override
    public AddressResultDTO convert(Address address) {
        AddressResultDTO resultDTO = new AddressResultDTO();
        resultDTO.setCity(address.getCity());
        resultDTO.setStreet(address.getStreet());
        resultDTO.setSuburb(address.getSuburb());

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
