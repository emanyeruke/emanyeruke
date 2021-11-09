package zw.co.mynhaka.polad.service.mapper.funeral;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.model.FuneralProduct;


@Component
public class FuneralProductToFuneralProductResultDTO implements Converter<FuneralProduct, FuneralProductResultDTO> {
    @Override
    public FuneralProductResultDTO convert(FuneralProduct funeralProduct) {

        FuneralProductResultDTO resultDTO = new FuneralProductResultDTO();
        resultDTO.setId(funeralProduct.getId());
        resultDTO.setName(funeralProduct.getName());
        resultDTO.setPersonType(funeralProduct.getPersonType().toString());
        resultDTO.setPremium(funeralProduct.getPremium());
        resultDTO.setSumAssured(funeralProduct.getSumAssured());
        resultDTO.setFuneralId(funeralProduct.getFuneral().getId());


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
