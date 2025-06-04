package com.infotelperu.infotel.config;

import com.infotelperu.infotel.model.Usuario;
import com.infotelperu.infotel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Crear usuario admin por defecto si no existe
        if (!usuarioRepository.existsByCorreo("admin@importadora.com")) {
            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setCorreo("admin@importadora.com");
            admin.setContrasena(passwordEncoder.encode("admin123"));
            admin.setRol(Usuario.Rol.ADMIN);
            admin.setTelefono("1234567890");
            
            usuarioRepository.save(admin);
            System.out.println("Usuario administrador creado: admin@importadora.com / admin123");
        }
    }
}
