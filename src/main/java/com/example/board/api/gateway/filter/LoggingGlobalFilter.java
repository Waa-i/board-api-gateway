package com.example.board.api.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var request = exchange.getRequest();
        long start = System.currentTimeMillis();

        log.info("[Request] - ID: [{}], Method: [{}], Path: [{}]",
                request.getId(), request.getMethod(), request.getPath());

        return chain.filter(exchange)
                .doFinally(signalType -> {
                    var response = exchange.getResponse();
                    var status = response.getStatusCode();
                    long elapsedTime = System.currentTimeMillis() - start;

                    log.info("[Response] - Path: [{}], Status: [{}], Time: [{}], Signal: [{}]",
                            exchange.getRequest().getPath(), status != null ? status.value() : "null" , elapsedTime, signalType);
                });
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
