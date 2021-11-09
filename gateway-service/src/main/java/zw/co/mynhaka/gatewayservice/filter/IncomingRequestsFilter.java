package zw.co.mynhaka.gatewayservice.filter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class IncomingRequestsFilter implements GlobalFilter, Ordered {

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getURI().toURL()));
        log.info("Parameters {}", request.getQueryParams());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
