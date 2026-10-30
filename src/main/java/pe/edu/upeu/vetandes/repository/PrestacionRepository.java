package pe.edu.upeu.vetandes.repository;
import pe.edu.upeu.vetandes.entity.*;
import org.springframework.data.jpa.repository.*;
public interface PrestacionRepository extends JpaRepository<Prestacion,Long>  {
    boolean existsByCodigoAndIdNot(String codigo,Long id);
    boolean existsByCodigo(String codigo);
    java.util.List<Prestacion> findByEspecieAplicableIn(java.util.Collection<String> especies);
}
