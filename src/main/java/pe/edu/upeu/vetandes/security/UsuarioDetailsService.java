package pe.edu.upeu.vetandes.security;
import pe.edu.upeu.vetandes.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
@Service @RequiredArgsConstructor public class UsuarioDetailsService implements UserDetailsService  {
    private final UsuarioRepository usuarios;
    public UserDetails loadUserByUsername(String username) {
        var u=usuarios.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Credenciales inválidas"));
        String[] permisos=u.getRoles().stream().flatMap(r->PermisosPorRol.obtener(r.getNombre()).stream()).map(Enum::name).distinct().toArray(String[]::new);
        return User.withUsername(u.getUsername()).password(u.getPassword()).disabled(!u.getActivo()).authorities(permisos).build();
    }
}
