package com.infotelperu.infotel.repository;

import com.infotelperu.infotel.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    Page<Producto> findByCategoriaId(Long categoriaId, Pageable pageable);
    
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    
    Page<Producto> findByCategoriaIdAndNombreContainingIgnoreCase(Long categoriaId, String nombre, Pageable pageable);
    
    List<Producto> findByStockLessThan(Integer stock);
    
    @Query("SELECT p FROM Producto p WHERE p.stock = 0")
    List<Producto> findProductosSinStock();
    
    @Query("SELECT SUM(p.precio * p.stock) FROM Producto p")
    Double calcularValorInventario();
    
    @Query("SELECT p FROM Producto p JOIN p.pedidoDetalles pd GROUP BY p.id ORDER BY SUM(pd.cantidad) DESC")
    Page<Producto> findProductosMasVendidos(Pageable pageable);
}
