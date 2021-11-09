package com.cap10mycap10.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name = "permission")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String authority;

    @Lob
    @Column
    private String description;

    public static UserPermission create(String permission) {
        return UserPermission
                .builder()
                .authority(permission)
                .description(permission.replace("_", " ").toLowerCase())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserPermission that = (UserPermission) o;
        return authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authority);
    }
}
