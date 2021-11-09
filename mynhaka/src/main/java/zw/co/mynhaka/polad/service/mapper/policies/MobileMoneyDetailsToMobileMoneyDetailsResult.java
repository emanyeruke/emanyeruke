package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.MobileMoneyDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.MobileMoneyDetailsResultDTO;
import zw.co.mynhaka.polad.domain.model.MobileMoneyDetails;


@Component
public class MobileMoneyDetailsToMobileMoneyDetailsResult implements Converter<MobileMoneyDetails, MobileMoneyDetailsCreateDTO> {

    @Override
    public MobileMoneyDetailsCreateDTO convert(MobileMoneyDetails mobileMoneyDetails) {
        MobileMoneyDetailsCreateDTO resultDTO = new MobileMoneyDetailsCreateDTO();
        resultDTO.setMobileNumber(mobileMoneyDetails.getMobileNumber());
        resultDTO.setMobileAccountName(mobileMoneyDetails.getMobileAccountName());



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