package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.dtos.allocation.AllocationCreateDTO;
import zw.co.mynhaka.polad.domain.dtos.allocation.AllocationResultDTO;

public interface AllocationService {

    AllocationResultDTO allocateToAccidentPolicy(AllocationCreateDTO allocationCreateDTO);

    AllocationResultDTO allocateToSavingsPolicy(AllocationCreateDTO allocationCreateDTO);

    AllocationResultDTO allocateToFuneralPolicy(AllocationCreateDTO allocationCreateDTO);

    AllocationResultDTO allocateToComprehensivePolicy(AllocationCreateDTO allocationCreateDTO);

}
