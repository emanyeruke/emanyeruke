package com.cap10mycap10.userservice.service;

import com.cap10mycap10.userservice.dto.AssignUserPermissionRequest;
import com.cap10mycap10.userservice.dto.CreatePermissionsRequest;
import com.cap10mycap10.userservice.model.UserPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserPermissionService {

    Page<UserPermission> findAll(Pageable pageable);

    Collection<UserPermission> findAll();

    UserPermission findById(Long id);

    Collection<UserPermission> findAllUnAssignedPermissions(Long id);

    Collection<UserPermission> findAllAssignedPermissions(Long groupId);

    List<UserPermission> createUserPermission(CreatePermissionsRequest request);

    Collection<UserPermission> assignPermissions(AssignUserPermissionRequest request);

    Collection<UserPermission> unAssignPermissions(AssignUserPermissionRequest request);
}
