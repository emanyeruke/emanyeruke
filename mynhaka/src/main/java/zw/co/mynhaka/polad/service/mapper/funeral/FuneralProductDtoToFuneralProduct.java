package zw.co.mynhaka.polad.service.mapper.funeral;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralProductCreateDto;
import zw.co.mynhaka.polad.domain.model.FuneralProduct;


@Component
public class FuneralProductDtoToFuneralProduct implements Converter<FuneralProductCreateDto, FuneralProduct> {
    @Override
    public FuneralProduct convert(FuneralProductCreateDto funeralProductCreateDto) {
        FuneralProduct funeralProduct = new FuneralProduct();
        funeralProduct.setName(funeralProductCreateDto.getName());
        funeralProduct.setPersonType(funeralProductCreateDto.getPersonType());
        funeralProduct.setPremium(funeralProductCreateDto.getPremium());
        funeralProduct.setSumAssured(funeralProductCreateDto.getSumAssured());
        return funeralProduct;
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
