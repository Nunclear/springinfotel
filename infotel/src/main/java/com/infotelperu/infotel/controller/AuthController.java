package com.infotelperu.infotel.controller;

import com.infotelperu.infotel.dto.ApiResponse;
import com.infotelperu.infotel.dto.CambiarContrasenaRequest;
import com.infotelperu.infotel.dto.LoginRequest;
import com.infotelperu.infotel.dto.RegistroRequest;
import com.infotelperu.infotel.model.Usuario;
import com.infotelperu.infotel.repository.UsuarioRepository;
import com.infotelperu.infotel.security.UserPrincipal;
import com.infotelperu.infotel.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistroRequest request) {
        try {
            Map<String, Object> result = authService.registrarUsuario(request);
            
            if (result.containsKey("error")) {
                return ResponseEntity.badRequest().body(ApiResponse.error(result.get("error").toString()));
            }
            
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error interno: " + e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@Valid @RequestBody LoginRequest request) {
        try {
            Map<String, Object> result = authService.autenticarUsuario(request);
            
            if (result.containsKey("error")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error(result.get("error").toString()));
            }
            
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error interno: " + e.getMessage()));
        }
    }
    
    @PostMapping("/change-password")
    public ResponseEntity<?> cambiarContrasena(@Valid @RequestBody CambiarContrasenaRequest request,
                                               Authentication authentication) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Map<String, Object> result = authService.cambiarContrasena(userPrincipal.getId(), request);
            
            if (result.containsKey("error")) {
                return ResponseEntity.badRequest().body(ApiResponse.error(result.get("error").toString()));
            }
            
            return ResponseEntity.ok(ApiResponse.success(result.get("message").toString()));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error interno: " + e.getMessage()));
        }
    }
    
    @GetMapping("/profile")
    public ResponseEntity<?> obtenerPerfil(Authentication authentication) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Usuario usuario = usuarioRepository.findById(userPrincipal.getId()).orElse(null);
            
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }
            
            Map<String, Object> usuarioDto = new HashMap<>();
            usuarioDto.put("id", usuario.getId());
            usuarioDto.put("nombre", usuario.getNombre());
            usuarioDto.put("correo", usuario.getCorreo());
            usuarioDto.put("rol", usuario.getRol().name());
            usuarioDto.put("direccion", usuario.getDireccion());
            usuarioDto.put("telefono", usuario.getTelefono());
            usuarioDto.put("fecha_creacion", usuario.getFechaCreacion());
            
            Map<String, Object> response = new HashMap<>();
            response.put("usuario", usuarioDto);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error interno: " + e.getMessage()));
        }
    }
}
