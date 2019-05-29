package com.grsu.game.platform.web;

import com.grsu.game.platform.web.websockets.GamePlatformSpringConfigurator;
import com.grsu.game.platform.web.websockets.GameEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableWebSocket
public class GamePlatformApplication {

    @Bean
    public GameEndpoint gameEndpoint() {
        return new GameEndpoint();
    }

    @Bean
    public ServerEndpointExporter endpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public GamePlatformSpringConfigurator customSpringConfigurator() {
        return new GamePlatformSpringConfigurator(); // This is just to get context
    }

    public static void main(String[] args) {
        SpringApplication.run(GamePlatformApplication.class, args);
    }
}
