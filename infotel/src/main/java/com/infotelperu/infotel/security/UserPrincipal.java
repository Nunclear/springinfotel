package com.infotelperu.infotel.security;

import com.infotelperu.infotel.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    
    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private Collection<? extends GrantedAuthority> authorities;
    
    public UserPrincipal(Long id, String nombre, String correo, String contrasena, 
                        Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.authorities = authorities;
    }
    
    public static UserPrincipal create(Usuario usuario) {
        Collection<GrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name())
        );
        
        return new UserPrincipal(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getContrasena(),
            authorities
        );
    }
    
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String getUsername() {
        return correo;
    }
    
    @Override
    public String getPassword() {
        return contrasena;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
