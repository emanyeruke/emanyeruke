package zw.co.mynhaka.polad.domain.dtos.manager;


import lombok.Data;
import zw.co.mynhaka.polad.domain.dtos.agent.AgentResultDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class ManagerResultDTO {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String branch;

    List<AgentResultDTO> agents = new ArrayList<>();

}
