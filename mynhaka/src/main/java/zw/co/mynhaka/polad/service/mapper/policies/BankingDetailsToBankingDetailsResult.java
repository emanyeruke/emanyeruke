package zw.co.mynhaka.polad.service.mapper.policies;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.domain.dtos.BankingDetailsCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.BankingDetailsResultDTO;
import zw.co.mynhaka.polad.domain.model.BankingDetails;



@Component
public class BankingDetailsToBankingDetailsResult implements Converter<BankingDetails, BankingDetailsCreateDTO> {


        @Override
        public BankingDetailsCreateDTO convert(BankingDetails bankingDetails) {
            BankingDetailsCreateDTO resultDTO = new BankingDetailsCreateDTO();
            resultDTO.setAccountName(bankingDetails.getAccountName());
            resultDTO.setBankName(bankingDetails.getBankName());
            resultDTO.setAccountNumber(bankingDetails.getAccountNumber());
            resultDTO.setBranch(bankingDetails.getBranch());


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

