package zw.co.mynhaka.polad.service.mapper.funeral;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.funeral.FuneralCreateDto;
import zw.co.mynhaka.polad.domain.model.Funeral;


@Component
public class FuneralCreateDtoToFuneral implements Converter<FuneralCreateDto, Funeral> {
    @Override
    public Funeral convert(FuneralCreateDto funeralCreateDto) {
        final Funeral funeral = new Funeral();
        funeral.setName(funeralCreateDto.getName());
        return funeral;
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
