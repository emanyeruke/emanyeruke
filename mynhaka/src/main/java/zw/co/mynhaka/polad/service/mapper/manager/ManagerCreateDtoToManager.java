package zw.co.mynhaka.polad.service.mapper.manager;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.manager.ManagerCreateDTO;
import zw.co.mynhaka.polad.domain.model.Manager;


@Component
public class ManagerCreateDtoToManager implements Converter<ManagerCreateDTO, Manager> {
    @Override
    public Manager convert(ManagerCreateDTO managerCreateDTO) {
        Manager manager = new Manager();
        manager.setName(managerCreateDTO.getName());
        manager.setSurname(managerCreateDTO.getSurname());
        manager.setEmail(managerCreateDTO.getEmail());
        return manager;
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
