package pe.edu.upeu.vetandes.repository;
import pe.edu.upeu.vetandes.entity.*;
import org.springframework.data.jpa.repository.*;
public interface AtencionRepository extends JpaRepository<Atencion,Long>  {
    boolean existsByMascotaId(Long mascotaId);
    @Query("select a from Atencion a join fetch a.mascota m join fetch m.propietario where (:mascota is null or lower(m.nombre) like lower(concat('%',:mascota,'%'))) and (:especie is null or m.especie=:especie) and (:estado is null or a.estado=:estado) and (:desde is null or a.fecha>=:desde) and (:hasta is null or a.fecha<:hasta) order by a.fecha desc") java.util.List<Atencion> consultar(String mascota,String especie,String estado,java.time.LocalDateTime desde,java.time.LocalDateTime hasta);
    @Query("select new pe.edu.upeu.vetandes.dto.ReporteEspecieDTO(a.mascota.especie,count(a),sum(a.total)) from Atencion a where a.estado='REGISTRADA' and (:desde is null or a.fecha>=:desde) and (:hasta is null or a.fecha<:hasta) group by a.mascota.especie order by sum(a.total) desc") java.util.List<pe.edu.upeu.vetandes.dto.ReporteEspecieDTO> reporte(java.time.LocalDateTime desde,java.time.LocalDateTime hasta);
    @Query(value="select atencion_numero_seq.nextval from dual",nativeQuery=true) Long siguienteNumero();
}
