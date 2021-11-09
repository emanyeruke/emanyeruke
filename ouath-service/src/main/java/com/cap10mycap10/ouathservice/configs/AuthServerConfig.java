package com.cap10mycap10.ouathservice.configs;

import com.cap10mycap10.ouathservice.service.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Qualifier("userDetailsService")
    private final UserDetailsService userDetailsService;
    @Autowired
    DataSource ds;
    @Autowired
    AuthenticationManager authMgr;
    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    public AuthServerConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean("clientPasswordEncoder")
    PasswordEncoder clientPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("JWTKey@123");
        return converter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer cfg) throws Exception {

        // Enable /oauth/token_key URL used by resource server to validate JWT tokens
        cfg.tokenKeyAccess("permitAll");

        // Enable /oauth/check_token URL
        cfg.checkTokenAccess("permitAll");

        // BCryptPasswordEncoder(8) is used for oauth_client_details.user_secret
        cfg.passwordEncoder(clientPasswordEncoder());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(ds);
        clients.withClientDetails(clientDetailsService)
                .jdbc()
                .dataSource(ds);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.accessTokenConverter(jwtAccessTokenConverter());
        endpoints.authenticationManager(authMgr);
        //  endpoints.userDetailsService(userDetailsService);
    }
}
