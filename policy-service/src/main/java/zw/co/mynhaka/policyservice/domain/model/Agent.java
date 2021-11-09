package zw.co.mynhaka.policyservice.domain.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Agent {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String contactNumber;
    private String channel;
    private String team;
    private String position;
}
