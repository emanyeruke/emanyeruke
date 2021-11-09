package zw.co.mynhaka.polad.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@Slf4j
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

    private final Environment environment;

    public SecurityConfiguration(final Environment environment) {
        this.environment = environment;
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) {
        resources.resourceId(environment.getRequiredProperty("security.oauth2.resource.id"));
    }

    @Override
    @SneakyThrows
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().permitAll();
    }

    @Bean("clientPasswordEncoder")
    PasswordEncoder clientPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}