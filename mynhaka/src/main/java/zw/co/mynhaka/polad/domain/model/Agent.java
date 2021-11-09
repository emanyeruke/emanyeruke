package zw.co.mynhaka.polad.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.mynhaka.polad.domain.enums.Channel;
import zw.co.mynhaka.polad.domain.enums.Position;
import zw.co.mynhaka.polad.domain.enums.Team;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

//    @OneToMany(
//            mappedBy = "agent",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    Set<PolicyHolder> policyHolderList = new HashSet<>();

    @OneToMany(
            mappedBy = "agent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<PolicyAccident> accidentPolicies = new HashSet<>();

    @OneToMany(
            mappedBy = "agent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<PolicyComprehensive> comprehensivePolicies = new HashSet<>();

    @OneToMany(
            mappedBy = "agent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<PolicyFuneral> funeralPolicies = new HashSet<>();

    @OneToMany(
            mappedBy = "agent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<PolicySavings> savingsPolicies = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    Manager manager;
}
