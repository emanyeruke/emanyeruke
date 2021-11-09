package zw.co.mynhaka.policyholderservice.domain.dto;

import lombok.Data;
import zw.co.mynhaka.policyholderservice.domain.enums.Channel;
import zw.co.mynhaka.policyholderservice.domain.enums.Position;
import zw.co.mynhaka.policyholderservice.domain.enums.Team;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class AgentCreateDTO {
    @Size(min = 5, max = 50)
    private String name;

    @Size(min = 5, max = 50)
    private String surname;

    @Email
    private String email;

    private String contactNumber;

    private Channel channel;

    private Team team;

    private Position position;

    private Long managerId;
}
