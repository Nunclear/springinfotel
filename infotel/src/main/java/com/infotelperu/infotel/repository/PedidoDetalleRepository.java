package com.infotelperu.infotel.repository;

import com.infotelperu.infotel.model.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
    
    List<PedidoDetalle> findByPedidoId(Long pedidoId);
    
    @Query("SELECT pd.productoId, SUM(pd.cantidad), SUM(pd.cantidad * pd.precioUnitario) " +
           "FROM PedidoDetalle pd GROUP BY pd.productoId ORDER BY SUM(pd.cantidad) DESC")
    List<Object[]> findProductosMasVendidos();
}
