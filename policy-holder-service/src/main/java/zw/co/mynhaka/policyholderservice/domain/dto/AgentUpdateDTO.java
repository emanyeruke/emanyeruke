package zw.co.mynhaka.policyholderservice.domain.dto;

import lombok.Data;
import zw.co.mynhaka.policyholderservice.domain.enums.Channel;
import zw.co.mynhaka.policyholderservice.domain.enums.Position;
import zw.co.mynhaka.policyholderservice.domain.enums.Team;
import zw.co.mynhaka.policyholderservice.validation.ContactNumberConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AgentUpdateDTO {
    @NotNull
    private Long agentId;

    @Size(min = 5, max = 50)
    private String name;

    @Size(min = 5, max = 50)
    private String surname;

    @ContactNumberConstraint
    private String contactNumber;

    private Channel channel;

    @Email
    private String email;

    private Team team;

    private Position position;

    @NotNull
    private Long managerId;
}
