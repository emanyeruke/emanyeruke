package zw.co.mynhaka.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import zw.co.mynhaka.gatewayservice.filter.IncomingRequestsFilter;
import zw.co.mynhaka.gatewayservice.filter.OutgoingRequestsFilter;

@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("oauth-service",
						r -> r.path("/oauth-service/**")
								.filters(f -> f.stripPrefix(1))
								.uri("lb://oauth-service"))
				.route("mynhaka-polad",
						r -> r.path("/mynhaka-polad/**")
								.filters(f -> f.stripPrefix(1))
								.uri("lb://mynhaka-polad"))
				.route("file-storage-service",
						r -> r.path("/file-storage-service/**")
								.filters(f -> f.stripPrefix(1))
								.uri("lb://file-storage-service"))
				.route("policy-service",
						r -> r.path("/policy-service/**")
                                .filters(f -> f.stripPrefix(1))
								.uri("lb://policy-service"))
				.route("policy-holder-service",
						r -> r.path("/policy-holder-service/**")
								.filters(f -> f.stripPrefix(1))
								.uri("lb://policy-holder-service"))
				.build();
	}

	@Bean
	public IncomingRequestsFilter incomingRequestsFilter() {
		return new IncomingRequestsFilter();
	}

	@Bean
	public OutgoingRequestsFilter outgoingRequestsFilter() {
		return new OutgoingRequestsFilter();
	}
}
