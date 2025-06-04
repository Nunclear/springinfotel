package com.infotelperu.infotel.dto;

import jakarta.validation.constraints.NotBlank;

public class ChatbotMensajeRequest {
    
    @NotBlank(message = "El mensaje es requerido")
    private String mensaje;
    
    // Constructores
    public ChatbotMensajeRequest() {}
    
    public ChatbotMensajeRequest(String mensaje) {
        this.mensaje = mensaje;
    }
    
    // Getters y Setters
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
