package pe.edu.upeu.vetandes.config;
import org.springframework.context.annotation.*;
import org.springframework.boot.CommandLineRunner;
import pe.edu.upeu.vetandes.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class UsuariosSemilla  {
    @Bean CommandLineRunner cifrarSemilla(UsuarioRepository usuarios,PasswordEncoder encoder) {
        return args-> {
            for(var u:usuarios.findAll()) {
                if(u.getPassword()==null||u.getPassword().isBlank()) {
                    u.setPassword(encoder.encode("Vet2026*"));
                    usuarios.save(u);
                }
            }
        }
        ;
    }
}
