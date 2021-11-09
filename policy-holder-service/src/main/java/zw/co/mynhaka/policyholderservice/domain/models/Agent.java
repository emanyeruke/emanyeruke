package zw.co.mynhaka.policyholderservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.policyholderservice.domain.enums.Channel;
import zw.co.mynhaka.policyholderservice.domain.enums.Position;
import zw.co.mynhaka.policyholderservice.domain.enums.Team;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agent extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String email;

    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private Team team;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    Manager manager;
}
