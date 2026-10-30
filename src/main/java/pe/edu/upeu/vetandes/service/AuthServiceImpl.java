package pe.edu.upeu.vetandes.service;
import pe.edu.upeu.vetandes.dto.*;
import pe.edu.upeu.vetandes.repository.*;
import pe.edu.upeu.vetandes.security.*;
import pe.edu.upeu.vetandes.config.JwtService;
import pe.edu.upeu.vetandes.exception.DominioException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor @Transactional(readOnly=true) public class AuthServiceImpl implements AuthService  {
    private final UsuarioRepository usuarios;
    private final UsuarioDetailsService details;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    public UsuarioResponseDTO actual(String username) {
        var u=usuarios.findByUsername(username).orElseThrow(()->new DominioException(401,"Credenciales inválidas"));
        return new UsuarioResponseDTO(u.getUsername(),u.getNombreCompleto(),u.getRoles().stream().map(r->r.getNombre()).collect(Collectors.toSet()),u.getRoles().stream().flatMap(r->PermisosPorRol.obtener(r.getNombre()).stream()).map(Enum::name).collect(Collectors.toSet()));
    }
    public LoginResponseDTO login(LoginRequestDTO d) {
        var u=usuarios.findByUsername(d.username()).orElse(null);
        if(u==null||!u.getActivo()||!encoder.matches(d.password(),u.getPassword()))throw new DominioException(401,"Credenciales inválidas");
        var a=actual(u.getUsername());
        return new LoginResponseDTO(jwt.generarToken(details.loadUserByUsername(u.getUsername())),"Bearer",jwt.getExpiracionSegundos(),a.username(),a.nombreCompleto(),a.roles(),a.permisos());
    }
}
