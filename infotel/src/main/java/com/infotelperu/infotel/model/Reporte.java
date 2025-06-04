package com.infotelperu.infotel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "reportes")
public class Reporte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre del reporte es requerido")
    @Size(max = 100)
    @Column(name = "nombre_reporte", nullable = false, length = 100)
    private String nombreReporte;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @CreationTimestamp
    @Column(name = "fecha_generado")
    private LocalDateTime fechaGenerado;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "JSON")
    private Map<String, Object> datos;
    
    // Constructores
    public Reporte() {}
    
    public Reporte(String nombreReporte, String descripcion, Map<String, Object> datos) {
        this.nombreReporte = nombreReporte;
        this.descripcion = descripcion;
        this.datos = datos;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombreReporte() { return nombreReporte; }
    public void setNombreReporte(String nombreReporte) { this.nombreReporte = nombreReporte; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDateTime getFechaGenerado() { return fechaGenerado; }
    public void setFechaGenerado(LocalDateTime fechaGenerado) { this.fechaGenerado = fechaGenerado; }
    
    public Map<String, Object> getDatos() { return datos; }
    public void setDatos(Map<String, Object> datos) { this.datos = datos; }
}
