package com.cap10mycap10.userservice.repository;

import com.cap10mycap10.userservice.model.Role;
import com.cap10mycap10.userservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByEmail(String email);

    @Override
    void delete(User user);

    Optional<User> findByUsername(String name);

    void deleteByUsername(String username);

    List<User> findByRolesIn(Collection<Role> roles);

    Page<User> findAllByAgentId(Long agentId, Pageable pageable);

    Page<User> findAllByClientId(Long clientId, Pageable pageable);


    @Query(value = "select * from users where agent_id is null and client_id is null and worker_id is null", nativeQuery = true)
    Page<User> findAllAdminUsers(Pageable pageable);

    @Query(value = "UPDATE users set enabled=:active where agent_id=:agent_id", nativeQuery = true)
    void activateAgentUsers(@Param("agent_id") Long agentId, @Param("active") boolean active);

    @Query(value = "UPDATE users set enabled=:active where client_id=:client_id", nativeQuery = true)
    void activateClientUsers(@Param("client_id") Long clientId, @Param("active") boolean active);


}
