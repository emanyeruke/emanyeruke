package zw.co.mynhaka.polad.service.mapper.comprehensive;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.comprehensivefuneral.ComprehensiveFuneralProductResultDTO;
import zw.co.mynhaka.polad.domain.model.ComprehensiveFuneralProduct;


@Component
public class ComprehensiveProductToComprehensiveProductResultDTO implements Converter<ComprehensiveFuneralProduct, ComprehensiveFuneralProductResultDTO> {
    @Override
    public ComprehensiveFuneralProductResultDTO convert(ComprehensiveFuneralProduct comprehensiveFuneralProduct) {

        ComprehensiveFuneralProductResultDTO resultDTO = new ComprehensiveFuneralProductResultDTO();
        resultDTO.setId(comprehensiveFuneralProduct.getId());
        resultDTO.setName(comprehensiveFuneralProduct.getName());
        resultDTO.setPersonType(comprehensiveFuneralProduct.getPersonType().toString());
        resultDTO.setPremium(comprehensiveFuneralProduct.getPremium());
        resultDTO.setSumAssured(comprehensiveFuneralProduct.getSumAssured());
        resultDTO.setTerm(comprehensiveFuneralProduct.getTerm().toString());
        resultDTO.setComprehensiveFuneralId(comprehensiveFuneralProduct.getComprehensiveFuneral().getId());

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
