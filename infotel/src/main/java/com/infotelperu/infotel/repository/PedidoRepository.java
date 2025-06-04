package com.infotelperu.infotel.repository;

import com.infotelperu.infotel.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    Page<Pedido> findByUsuarioId(Long usuarioId, Pageable pageable);
    
    Page<Pedido> findByEstado(Pedido.Estado estado, Pageable pageable);
    
    List<Pedido> findByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.estado = :estado")
    long countByEstado(@Param("estado") Pedido.Estado estado);
    
    @Query("SELECT SUM(p.total) FROM Pedido p WHERE p.estado IN ('ENVIADO', 'ENTREGADO')")
    BigDecimal calcularTotalVentas();
    
    @Query("SELECT SUM(p.total) FROM Pedido p WHERE p.estado IN ('ENVIADO', 'ENTREGADO') AND p.fechaPedido BETWEEN :fechaInicio AND :fechaFin")
    BigDecimal calcularVentasPorPeriodo(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
    
    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.fechaPedido >= :fechaInicio")
    long countPedidosDelMes(@Param("fechaInicio") LocalDateTime fechaInicio);
}
