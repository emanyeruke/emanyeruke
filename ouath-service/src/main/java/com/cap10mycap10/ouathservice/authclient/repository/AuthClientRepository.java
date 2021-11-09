package com.cap10mycap10.ouathservice.authclient.repository;


import com.cap10mycap10.ouathservice.authclient.model.AuthClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthClientRepository extends JpaRepository<AuthClient, Long> {

    Optional<AuthClient> findByClientId(String clientId);

}
