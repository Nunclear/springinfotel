package com.infotelperu.infotel.service;

import com.infotelperu.infotel.dto.CambiarContrasenaRequest;
import com.infotelperu.infotel.dto.LoginRequest;
import com.infotelperu.infotel.dto.RegistroRequest;
import com.infotelperu.infotel.model.Usuario;
import com.infotelperu.infotel.repository.UsuarioRepository;
import com.infotelperu.infotel.security.UserPrincipal;
import com.infotelperu.infotel.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public Map<String, Object> registrarUsuario(RegistroRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        // Verificar si el correo ya existe
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            response.put("error", "El correo electrónico ya está registrado");
            return response;
        }
        
        try {
            // Crear nuevo usuario
            Usuario usuario = new Usuario();
            usuario.setNombre(request.getNombre());
            usuario.setCorreo(request.getCorreo());
            usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
            usuario.setDireccion(request.getDireccion());
            usuario.setTelefono(request.getTelefono());
            usuario.setRol(Usuario.Rol.CLIENTE);
            
            Usuario usuarioGuardado = usuarioRepository.save(usuario);
            
            response.put("message", "Usuario registrado exitosamente");
            response.put("usuario", convertirADto(usuarioGuardado));
            
        } catch (Exception e) {
            response.put("error", "Error al crear usuario: " + e.getMessage());
        }
        
        return response;
    }
    
    public Map<String, Object> autenticarUsuario(LoginRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena())
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateToken(authentication);
            
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Usuario usuario = usuarioRepository.findById(userPrincipal.getId()).orElse(null);
            
            response.put("message", "Login exitoso");
            response.put("token", jwt);
            response.put("usuario", convertirADto(usuario));
            
        } catch (Exception e) {
            response.put("error", "Credenciales inválidas");
        }
        
        return response;
    }
    
    public Map<String, Object> cambiarContrasena(Long usuarioId, CambiarContrasenaRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            response.put("error", "Usuario no encontrado");
            return response;
        }
        
        // Verificar contraseña actual
        if (!passwordEncoder.matches(request.getContrasenaActual(), usuario.getContrasena())) {
            response.put("error", "Contraseña actual incorrecta");
            return response;
        }
        
        try {
            // Actualizar contraseña
            usuario.setContrasena(passwordEncoder.encode(request.getContrasenaNueva()));
            usuarioRepository.save(usuario);
            
            response.put("message", "Contraseña cambiada exitosamente");
            
        } catch (Exception e) {
            response.put("error", "Error al cambiar contraseña: " + e.getMessage());
        }
        
        return response;
    }
    
    private Map<String, Object> convertirADto(Usuario usuario) {
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", usuario.getId());
        dto.put("nombre", usuario.getNombre());
        dto.put("correo", usuario.getCorreo());
        dto.put("rol", usuario.getRol().name());
        dto.put("direccion", usuario.getDireccion());
        dto.put("telefono", usuario.getTelefono());
        dto.put("fecha_creacion", usuario.getFechaCreacion());
        return dto;
    }
}
