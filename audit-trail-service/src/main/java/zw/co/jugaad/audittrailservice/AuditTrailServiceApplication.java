package zw.co.jugaad.audittrailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "zw.co.jugaad")
@EnableJpaRepositories(basePackages = {"model.repository"})
@EntityScan(basePackages = {"model.entity"})
@EnableEurekaClient
@EnableFeignClients
public class AuditTrailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditTrailServiceApplication.class, args);
    }

}
