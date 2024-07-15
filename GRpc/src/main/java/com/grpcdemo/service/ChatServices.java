package com.grpcdemo.service;

import com.grpcDemo.interfaces.ChatMessage;
import com.grpcDemo.interfaces.ChatResponse;
import com.grpcDemo.interfaces.ChatServiceGrpc.ChatServiceImplBase;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.Random;

@GRpcService
public class ChatServices extends ChatServiceImplBase {
private Random random = new Random();
    @Override
    public void sendMessage(ChatMessage request, StreamObserver<ChatResponse> responseObserver) {

        ChatResponse response = ChatResponse.newBuilder()
                .setFrom(random.nextInt())
                .setMessage("Mensaje recibido:")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<ChatMessage> sendMuiltiMessage(StreamObserver<ChatResponse> responseObserver) {
        return new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage chatMessage) {
                // Lógica para manejar múltiples mensajes aquí
                ChatResponse response = ChatResponse.newBuilder()
                        .setMessage("Mensaje múltiple recibido: " + chatMessage.getMessage())
                        .build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                // Manejo de errores
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // Completar la respuesta
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void reciveMuiltiMessage(ChatMessage request, StreamObserver<ChatResponse> responseObserver) {
        // Implementa la lógica de recepción de mensajes múltiples aquí
        ChatResponse response = ChatResponse.newBuilder()
                .setMessage("Mensaje recibido: " + request.getMessage())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<ChatMessage> sendReciveMuiltiMessage(StreamObserver<ChatResponse> responseObserver) {
        return new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage chatMessage) {
                // Lógica para enviar y recibir múltiples mensajes aquí
                ChatResponse response = ChatResponse.newBuilder()
                        .setMessage("Mensaje recibido y respondido: " + chatMessage.getMessage())
                        .build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                // Manejo de errores
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // Completar la respuesta
                responseObserver.onCompleted();
            }
        };
    }
}
