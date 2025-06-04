package com.infotelperu.infotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfotelApplication.class, args);
        System.out.println("🚀 Servidor Spring Boot iniciado en http://localhost:5000/api");
        System.out.println("🔗 Health check: http://localhost:5000/api/health");
        System.out.println("👤 Usuario admin por defecto: admin@importadora.com / admin123");
    }

}
