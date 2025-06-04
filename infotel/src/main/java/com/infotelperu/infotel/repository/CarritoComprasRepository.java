package com.infotelperu.infotel.repository;

import com.infotelperu.infotel.model.CarritoCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Long> {
    
    List<CarritoCompras> findByUsuarioId(Long usuarioId);
    
    Optional<CarritoCompras> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
    
    Optional<CarritoCompras> findByIdAndUsuarioId(Long id, Long usuarioId);
    
    void deleteByUsuarioId(Long usuarioId);
    
    @Query("SELECT SUM(c.cantidad * c.producto.precio) FROM CarritoCompras c WHERE c.usuarioId = :usuarioId")
    BigDecimal calcularTotalCarrito(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT COUNT(c) FROM CarritoCompras c WHERE c.usuarioId = :usuarioId")
    int contarItemsCarrito(@Param("usuarioId") Long usuarioId);
}
