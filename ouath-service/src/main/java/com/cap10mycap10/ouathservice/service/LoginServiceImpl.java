package com.cap10mycap10.ouathservice.service;

import com.cap10mycap10.ouathservice.audit.Audit;
import com.cap10mycap10.ouathservice.authclient.model.AuthClient;
import com.cap10mycap10.ouathservice.authclient.repository.AuthClientRepository;
import com.cap10mycap10.ouathservice.dao.UserRepository;
import com.cap10mycap10.ouathservice.dto.UserLogin;
import com.cap10mycap10.ouathservice.dto.UserLoginRequest;
import com.cap10mycap10.ouathservice.entity.AccessTokenResponse;
import com.cap10mycap10.ouathservice.entity.ResultDTO;
import com.cap10mycap10.ouathservice.entity.Role;
import com.cap10mycap10.ouathservice.entity.User;
import com.cap10mycap10.ouathservice.enums.ClientID;
import com.cap10mycap10.ouathservice.enums.Roles;
import com.cap10mycap10.ouathservice.feignclient.OauthFeignClientService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import zw.co.invenico.springcommonsmodule.exception.AccessDeniedException;
import zw.co.invenico.springcommonsmodule.exception.RecordNotFoundException;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final OauthFeignClientService oauthFeignClientService;

    private final UserRepository userRepository;

    private final AuthClientRepository authClientRepository;

    @Value("${oauth-service.refresh}")
    private String refreshUrl;

    public LoginServiceImpl(OauthFeignClientService oauthFeignClientService, UserRepository userRepository,
                            AuthClientRepository authClientRepository) {
        this.oauthFeignClientService = oauthFeignClientService;
        this.userRepository = userRepository;
        this.authClientRepository = authClientRepository;
    }

    @Override
    @Audit(resource = "Login", action = "User Login")
    public ResultDTO userLogin(UserLogin userLogin) throws IllegalAccessException {
        User user = userRepository.findByUsername(userLogin.getUsername())
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
        log.info("user status: {}", user.isEnabled());
        if (user.isEnabled()) {
            ResultDTO resultDTO = getToken(userLogin);
            resultDTO.setId(user.getId());
            resultDTO.setFirstname(user.getFirstName());
            resultDTO.setLastname(user.getLastName());
            resultDTO.setRoles(user.getRoles());
            resultDTO.setAgentId(user.getAgentId());
            resultDTO.setClientId(user.getClientId());
            return resultDTO;
        } else {
            log.info("User {}", user.getFirstName());
            throw new AccessDeniedException("User not yet activated");
        }
    }

    @Override
    @Audit(resource = "Login", action = "User Login")
    public ResultDTO refreshToken(String token, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
        ResultDTO resultDTO = refreshToken(token);
        resultDTO.setFirstname(user.getFirstName());
        resultDTO.setLastname(user.getLastName());
        resultDTO.setRoles(user.getRoles());
        return resultDTO;
    }

    @Override
    @Audit(resource = "Login", action = "User Login")
    public ResultDTO clientLogin(UserLoginRequest request) {

        AuthClient authClient = authClientRepository.findByClientId(request.getClientId())
                .orElseThrow(() -> new AccessDeniedException("Access denied"));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RecordNotFoundException("User not found"));

        if (authClient.getClientId().equalsIgnoreCase(ClientID.ADMIN.name())) {
            Optional<Role> optionalRole = user.getRoles()
                    .stream()
                    .filter(role -> role.getName().equalsIgnoreCase(Roles.ROLE_SYSTEM_ADMIN.name()) ||
                            role.getName().equalsIgnoreCase(Roles.ROLE_SYSTEM_CLERK.name()))
                    .findFirst();

            if (!optionalRole.isPresent()) {
                throw new AccessDeniedException("Access denied");
            }
        }
        if (authClient.getClientId().equalsIgnoreCase(ClientID.AGENT.name())) {
            Optional<Role> optionalRole = user.getRoles()
                    .stream()
                    .filter(role -> role.getName().equalsIgnoreCase(Roles.ROLE_AGENT_ADMIN.name()) ||
                            role.getName().equalsIgnoreCase(Roles.ROLE_AGENT_CLERK.name()))
                    .findFirst();

            if (!optionalRole.isPresent()) {
                throw new AccessDeniedException("Access denied");
            }
        }
        if (authClient.getClientId().equalsIgnoreCase(ClientID.CLIENT.name())) {
            Optional<Role> optionalRole = user.getRoles()
                    .stream()
                    .filter(role -> role.getName().equalsIgnoreCase(Roles.ROLE_CLIENT.name()))
                    .findFirst();

            if (!optionalRole.isPresent()) {
                throw new AccessDeniedException("Access denied");
            }
        }
        ResultDTO resultDTO = getToken(request);
        resultDTO.setId(user.getId());
        resultDTO.setLastname(user.getLastName());
        resultDTO.setRoles(user.getRoles());
        resultDTO.setFirstname(user.getFirstName());
        resultDTO.setAgentId(user.getAgentId());
        resultDTO.setClientId(user.getClientId());
        return resultDTO;
    }

    private ResultDTO getToken(UserLogin userLogin) {
        AccessTokenResponse accessTokenResponse;
        if (userLogin instanceof UserLoginRequest) {
            UserLoginRequest userLoginRequest = (UserLoginRequest) userLogin;
            accessTokenResponse = oauthFeignClientService.getAccessToken(userLogin.getUsername(),
                    userLogin.getPassword(), userLoginRequest.getClientId(), userLoginRequest.getClientSecret());
        } else {
            accessTokenResponse = oauthFeignClientService.getAccessToken(userLogin.getUsername(),
                    userLogin.getPassword());
        }
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setAccessToken(accessTokenResponse.getAccessToken());
        resultDTO.setRefreshToken(accessTokenResponse.getRefreshToken());
        resultDTO.setExpireIn(accessTokenResponse.getExpiresIn());
        resultDTO.setTokenType(accessTokenResponse.getTokenType());
        resultDTO.setScope(accessTokenResponse.getScope());

        return resultDTO;
    }

    private ResultDTO refreshToken(String token) {
        //"http://localhost:8762/api/v1/oauth-server/oauth/token"
        final String url = refreshUrl;
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", token); // oauth client identifier

        String authStr = "appclient:appclient@123";
        String base64Creds = Base64.encodeBase64String(authStr.getBytes());

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        // create request
        HttpEntity request = new HttpEntity(body, headers);

        // make a request
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);


        // get JSON response

        ResultDTO resultDTO = new ResultDTO();

        JsonObject jsonObject = new JsonParser().parse(Objects.requireNonNull(response.getBody())).getAsJsonObject();
        resultDTO.setAccessToken(replaceSlashes(String.valueOf(jsonObject.get("access_token"))));
        resultDTO.setExpireIn(replaceSlashes(String.valueOf(jsonObject.get("expires_in"))));
        resultDTO.setRefreshToken(replaceSlashes(String.valueOf(jsonObject.get("refresh_token"))));
        resultDTO.setTokenType(replaceSlashes(String.valueOf(jsonObject.get("token_type"))));
        resultDTO.setScope(replaceSlashes(String.valueOf(jsonObject.get("scope"))));

        return resultDTO;
    }


    private String replaceSlashes(String withSlashes) {
        return withSlashes.replaceAll("\"", "");
    }


}
