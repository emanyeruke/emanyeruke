package com.cap10mycap10.userservice.repository;


import com.cap10mycap10.userservice.model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
}
