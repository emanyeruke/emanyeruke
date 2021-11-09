package zw.co.mynhaka.gatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.CLIENT_RESPONSE_CONN_ATTR;

@Slf4j
public class OutgoingRequestsFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        Connection connection = exchange.getAttribute(CLIENT_RESPONSE_CONN_ATTR);
//        connection.inbound().receive().retain().map(factory::wrap)

        Mono<Void> result = chain.filter(exchange);

        Connection connection = exchange.getAttribute(CLIENT_RESPONSE_CONN_ATTR);

        exchange.getAttributes().forEach( (k, v) -> log.info(k + " -> " + v));

        ServerHttpResponse response = exchange.getResponse();

        NettyDataBufferFactory factory = (NettyDataBufferFactory) response.bufferFactory();

        if (connection != null) {
            final Mono<NettyDataBuffer> body = connection.inbound()
                    .receive()
                    .retain()
                    .map(factory::wrap).next();



            body.subscribe(nettyDataBuffer -> {
                byte[] dst = new byte[nettyDataBuffer.capacity()];
                nettyDataBuffer.getNativeBuffer().slice().getBytes(0, dst);
                log.info("Response Body: {}", new String(dst));
            });
        } else {
            log.info("Response Body: {}", "");
        }
        return result;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
