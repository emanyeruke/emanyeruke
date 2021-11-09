package zw.co.mynhaka.polad.service.mapper.comprehensive;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveProductCreateDto;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneralProduct;


@Component
public class ComprehensiveProductDtoToComprehensiveProduct implements Converter<ComprehensiveProductCreateDto, ComprehensiveFuneralProduct> {
    @Override
    public ComprehensiveFuneralProduct convert(ComprehensiveProductCreateDto item) {
        ComprehensiveFuneralProduct comprehensiveFuneralProduct = new ComprehensiveFuneralProduct();
        comprehensiveFuneralProduct.setName(item.getName());
        comprehensiveFuneralProduct.setPersonType(item.getPersonType());
        comprehensiveFuneralProduct.setSumAssured(item.getSumAssured());
        comprehensiveFuneralProduct.setTerm(item.getTerm());
        comprehensiveFuneralProduct.setPremium(item.getPremium());
        return comprehensiveFuneralProduct;
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
