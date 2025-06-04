package com.infotelperu.infotel.repository;

import com.infotelperu.infotel.model.Reporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    
    Page<Reporte> findAllByOrderByFechaGeneradoDesc(Pageable pageable);
    
    Page<Reporte> findByNombreReporteContainingIgnoreCase(String nombreReporte, Pageable pageable);
}
