package com.cap10mycap10.ouathservice.feignclient;

import com.cap10mycap10.ouathservice.entity.AccessTokenResponse;
import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "akupay-api-gateway", url = "${oauth-service.url}")
@RibbonClient("AKUPAY-OAUTH-SERVICE")
public interface OauthFeignClientService {

    @PostMapping("/oauth/token")
    @Headers("Content-Type: application/json")
    AccessTokenResponse accessToken(@RequestHeader("Authorization") String bearerToken,
                                    @RequestParam String grant_type,
                                    @RequestParam String password,
                                    @RequestParam String username);

    default AccessTokenResponse getAccessToken(String username, String password) {
        byte[] encodedBytes = Base64Utils.encode(("appclient" + ":" + "appclient@123").getBytes());
        String authHeader = "Basic " + new String(encodedBytes);
        return accessToken(authHeader, "password", password, username);
    }

    default AccessTokenResponse getAccessToken(String username, String password, String clientId, String clientSecret) {
        //byte[] encodedBytes = Base64Utils.encode((clientId + ":" + clientSecret).getBytes());
        byte[] encodedBytes = Base64Utils.encode(("appclient" + ":" + "appclient@123").getBytes());
        String authHeader = "Basic " + new String(encodedBytes);
        return accessToken(authHeader, "password", password, username);
    }
}