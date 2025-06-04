package com.infotelperu.infotel.dto;

import com.infotelperu.infotel.model.Pedido;
import jakarta.validation.constraints.NotNull;

public class ActualizarEstadoPedidoRequest {
    
    @NotNull(message = "El estado es requerido")
    private Pedido.Estado estado;
    
    // Constructores
    public ActualizarEstadoPedidoRequest() {}
    
    public ActualizarEstadoPedidoRequest(Pedido.Estado estado) {
        this.estado = estado;
    }
    
    // Getters y Setters
    public Pedido.Estado getEstado() { return estado; }
    public void setEstado(Pedido.Estado estado) { this.estado = estado; }
}
