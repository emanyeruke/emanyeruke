package com.cap10mycap10.userservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CreatePermissionsRequest {

    Set<String> permissions;

}
