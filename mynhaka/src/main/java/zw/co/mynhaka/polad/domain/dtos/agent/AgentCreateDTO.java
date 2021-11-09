package zw.co.mynhaka.polad.domain.dtos.agent;


import lombok.Data;
import zw.co.mynhaka.polad.domain.enums.Channel;
import zw.co.mynhaka.polad.domain.enums.Position;
import zw.co.mynhaka.polad.domain.enums.Team;
import zw.co.mynhaka.polad.validation.ContactNumberConstraint;

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

    @ContactNumberConstraint
    private String contactNumber;

    private Channel channel;

    private Team team;

    private Position position;

    private Long managerId;
}
