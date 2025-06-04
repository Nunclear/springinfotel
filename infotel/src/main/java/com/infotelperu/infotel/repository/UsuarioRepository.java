package com.infotelperu.infotel.repository;

import com.infotelperu.infotel.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByCorreo(String correo);
    
    boolean existsByCorreo(String correo);
    
    Page<Usuario> findAll(Pageable pageable);
    
    @Query("SELECT u FROM Usuario u LEFT JOIN u.pedidos p GROUP BY u.id ORDER BY COUNT(p.id) DESC")
    Page<Usuario> findUsuariosConMasPedidos(Pageable pageable);
    
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = :rol")
    long countByRol(@Param("rol") Usuario.Rol rol);
}
