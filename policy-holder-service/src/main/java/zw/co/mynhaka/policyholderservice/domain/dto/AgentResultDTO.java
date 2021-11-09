package zw.co.mynhaka.policyholderservice.domain.dto;

import lombok.Data;

@Data
public class AgentResultDTO {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String contactNumber;

    private String channel;

    private String team;

    private String position;

    private ManagerAgentResultDTO managerAgentResultDTO;
}
