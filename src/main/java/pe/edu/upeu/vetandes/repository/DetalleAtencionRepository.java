package pe.edu.upeu.vetandes.repository;
import pe.edu.upeu.vetandes.entity.*;
import org.springframework.data.jpa.repository.*;
public interface DetalleAtencionRepository extends JpaRepository<DetalleAtencion,Long>  {
    boolean existsByPrestacionId(Long prestacionId);
}
