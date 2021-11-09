package com.cap10mycap10.userservice.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class AssignUserPermissionRequest {

    private Set<Long> authoritiesId;

    @NotNull(message = "Role id should be provided")
    private Long roleId;
}
