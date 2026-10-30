package pe.edu.upeu.vetandes.repository;
import pe.edu.upeu.vetandes.entity.*;
import org.springframework.data.jpa.repository.*;
public interface UsuarioRepository extends JpaRepository<Usuario,Long>  {
    java.util.Optional<Usuario> findByUsername(String username);
}
