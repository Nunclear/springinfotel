package com.infotelperu.infotel.repository;

import com.infotelperu.infotel.model.ChatbotHistorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotHistorialRepository extends JpaRepository<ChatbotHistorial, Long> {
    
    Page<ChatbotHistorial> findByUsuarioIdOrderByFechaDesc(Long usuarioId, Pageable pageable);
    
    ChatbotHistorial findByIdAndUsuarioId(Long id, Long usuarioId);
    
    void deleteByUsuarioId(Long usuarioId);
}
