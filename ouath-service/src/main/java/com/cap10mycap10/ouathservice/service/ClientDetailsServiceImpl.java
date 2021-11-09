package com.cap10mycap10.ouathservice.service;

import com.cap10mycap10.ouathservice.authclient.repository.AuthClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import zw.co.invenico.springcommonsmodule.exception.RecordNotFoundException;


@Slf4j
@Primary
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final AuthClientRepository authClientRepository;

    public ClientDetailsServiceImpl(AuthClientRepository authClientRepository) {
        this.authClientRepository = authClientRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        log.info("----> Client details");

        return authClientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RecordNotFoundException("Client details not found"));
    }
}
