package com.cap10mycap10.userservice.service;

import com.cap10mycap10.userservice.dto.AssignUserPermissionRequest;
import com.cap10mycap10.userservice.dto.CreatePermissionsRequest;
import com.cap10mycap10.userservice.model.UserPermission;
import com.cap10mycap10.userservice.repository.RoleRepository;
import com.cap10mycap10.userservice.repository.UserPermissionRepository;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.invenico.springcommonsmodule.exception.RecordNotFoundException;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toSet;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    private final UserPermissionRepository userPermissionRepository;

    private final RoleRepository roleRepository;

    public UserPermissionServiceImpl(
            UserPermissionRepository userPermissionRepository, RoleRepository roleRepository) {
        this.userPermissionRepository = userPermissionRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<UserPermission> findAll(Pageable pageable) {
        return userPermissionRepository.findAll(pageable);
    }

    @Override
    public Collection<UserPermission> findAll() {
        return userPermissionRepository.findAll();
    }

    @Override
    public UserPermission findById(Long id) {
        return userPermissionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User permission with id " + id + " not found"));
    }

    @Override
    public Collection<UserPermission> findAllUnAssignedPermissions(Long id) {

        val allAssignedPermissions = findAllAssignedPermissions(id);

        return userPermissionRepository.findAll()
                .parallelStream()
                .filter(userPermission -> !allAssignedPermissions.contains(userPermission))
                .collect(toSet());
    }

    @Override
    public Collection<UserPermission> findAllAssignedPermissions(Long groupId) {
        val role = roleRepository.findById(groupId)
                .orElseThrow(() -> new RecordNotFoundException("User role with id " + groupId + " not found"));
        return role.getPermissions();
    }

    @Override
    public List<UserPermission> createUserPermission(CreatePermissionsRequest request) {
        val allPermissions = userPermissionRepository.findAll()
                .parallelStream()
                .map(UserPermission::getAuthority)
                .collect(toSet());

        val newPermissions = request.getPermissions()
                .parallelStream()
                .filter(permission -> !allPermissions.contains(permission))
                .map(UserPermission::create)
                .collect(toSet());

        return userPermissionRepository.saveAll(newPermissions);
    }

    @Override
    public Collection<UserPermission> assignPermissions(AssignUserPermissionRequest request) {
        val role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RecordNotFoundException("Role not found"));
        val userPermissions = userPermissionRepository.findAllById(request.getAuthoritiesId());
        role.getPermissions().addAll(userPermissions);
        roleRepository.save(role);
        return role.getPermissions();
    }

    @Override
    public Collection<UserPermission> unAssignPermissions(AssignUserPermissionRequest request) {
        val role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RecordNotFoundException("Role not found"));
        val userPermissions = userPermissionRepository.findAllById(request.getAuthoritiesId());
        role.getPermissions().removeAll(userPermissions);
        roleRepository.save(role);
        return role.getPermissions();
    }

}
