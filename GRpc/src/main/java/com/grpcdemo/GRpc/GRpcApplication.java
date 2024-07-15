package com.grpcdemo.GRpc;

import com.grpcdemo.service.ChatServices;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GRpcApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GRpcApplication.class, args);
    }

    @Bean
    public Server grpcServer() {
        // Construir y configurar el servidor gRPC
        Server server = ServerBuilder.forPort(9090)  // Puerto en el que escucha el servidor gRPC
                .addService(new ChatServices())  // Añadir tus servicios gRPC aquí
                .build();

        // Iniciar el servidor gRPC
        try {
            server.start();
            System.out.println("Servidor gRPC iniciado en el puerto " + server.getPort());
            return server;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
