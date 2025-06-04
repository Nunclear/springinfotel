package com.infotelperu.infotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "chatbot_historial")
public class ChatbotHistorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El usuario es requerido")
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
    
    @NotBlank(message = "El mensaje del usuario es requerido")
    @Column(name = "mensaje_usuario", nullable = false, columnDefinition = "TEXT")
    private String mensajeUsuario;
    
    @NotBlank(message = "La respuesta del chatbot es requerida")
    @Column(name = "respuesta_chatbot", nullable = false, columnDefinition = "TEXT")
    private String respuestaChatbot;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;
    
    @CreationTimestamp
    private LocalDateTime fecha;
    
    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario usuario;
    
    // Constructores
    public ChatbotHistorial() {}
    
    public ChatbotHistorial(Long usuarioId, String mensajeUsuario, String respuestaChatbot, Tipo tipo) {
        this.usuarioId = usuarioId;
        this.mensajeUsuario = mensajeUsuario;
        this.respuestaChatbot = respuestaChatbot;
        this.tipo = tipo;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    
    public String getMensajeUsuario() { return mensajeUsuario; }
    public void setMensajeUsuario(String mensajeUsuario) { this.mensajeUsuario = mensajeUsuario; }
    
    public String getRespuestaChatbot() { return respuestaChatbot; }
    public void setRespuestaChatbot(String respuestaChatbot) { this.respuestaChatbot = respuestaChatbot; }
    
    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public enum Tipo {
        INFORMATIVO, RECOMENDACION
    }
}
