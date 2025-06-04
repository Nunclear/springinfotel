package com.infotelperu.infotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CambiarContrasenaRequest {
    
    @NotBlank(message = "La contraseña actual es requerida")
    private String contrasenaActual;
    
    @NotBlank(message = "La nueva contraseña es requerida")
    @Size(min = 6, message = "La nueva contraseña debe tener al menos 6 caracteres")
    private String contrasenaNueva;
    
    // Constructores
    public CambiarContrasenaRequest() {}
    
    public CambiarContrasenaRequest(String contrasenaActual, String contrasenaNueva) {
        this.contrasenaActual = contrasenaActual;
        this.contrasenaNueva = contrasenaNueva;
    }
    
    // Getters y Setters
    public String getContrasenaActual() { return contrasenaActual; }
    public void setContrasenaActual(String contrasenaActual) { this.contrasenaActual = contrasenaActual; }
    
    public String getContrasenaNueva() { return contrasenaNueva; }
    public void setContrasenaNueva(String contrasenaNueva) { this.contrasenaNueva = contrasenaNueva; }
}
