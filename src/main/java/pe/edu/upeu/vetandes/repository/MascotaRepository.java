package pe.edu.upeu.vetandes.repository;
import pe.edu.upeu.vetandes.entity.*;
import org.springframework.data.jpa.repository.*;
public interface MascotaRepository extends JpaRepository<Mascota,Long>  {
    boolean existsByNombreAndPropietarioIdAndIdNot(String nombre,Long propietarioId,Long id);
    boolean existsByNombreAndPropietarioId(String nombre,Long propietarioId);
    java.util.List<Mascota> findByPropietarioId(Long propietarioId);
}
