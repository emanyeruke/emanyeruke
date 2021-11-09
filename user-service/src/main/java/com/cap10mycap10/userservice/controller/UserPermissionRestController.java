package com.cap10mycap10.userservice.controller;


import com.cap10mycap10.userservice.dto.AssignUserPermissionRequest;
import com.cap10mycap10.userservice.dto.CreatePermissionsRequest;
import com.cap10mycap10.userservice.model.UserPermission;
import com.cap10mycap10.userservice.service.UserPermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user-permission")
public class UserPermissionRestController {

    private final UserPermissionService userPermissionService;

    public UserPermissionRestController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @PostMapping
    public List<UserPermission> createUserPermission(@RequestBody CreatePermissionsRequest request) {
        return userPermissionService.createUserPermission(request);
    }

    @GetMapping(value = "/", params = {"page", "size"})
    public Page<UserPermission> getAllPermissions(
            @PageableDefault(sort = {"authority"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userPermissionService.findAll(pageable);
    }

    @GetMapping("/all")
    public Collection<UserPermission> getAllPermissions() {
        return userPermissionService.findAll();
    }

    @GetMapping("/{id}")
    public UserPermission getUserPermission(@PathVariable Long id) {
        return userPermissionService.findById(id);
    }

    @GetMapping("/un-assigned/{groupId}")
    public Collection<UserPermission> getAllUnAssignedPermissions(@PathVariable Long groupId) {
        return userPermissionService.findAllUnAssignedPermissions(groupId);
    }

    @GetMapping("/assigned/{groupId}")
    public Collection<UserPermission> getAllAssignedPermissions(@PathVariable Long groupId) {
        return userPermissionService.findAllAssignedPermissions(groupId);
    }

    @PostMapping("/assign-permissions")
    public Collection<UserPermission> assignPermissions(@RequestBody AssignUserPermissionRequest request) {
        return userPermissionService.assignPermissions(request);
    }

    @PostMapping("/un-assign-permissions")
    public Collection<UserPermission> unAssignPermissions(@RequestBody AssignUserPermissionRequest request) {
        return userPermissionService.unAssignPermissions(request);
    }

}
