package pe.edu.upeu.vetandes.service;
import pe.edu.upeu.vetandes.dto.*;
import pe.edu.upeu.vetandes.entity.*;
import pe.edu.upeu.vetandes.repository.*;
import pe.edu.upeu.vetandes.mapper.VetMapper;
import pe.edu.upeu.vetandes.exception.DominioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
@Service @RequiredArgsConstructor @Slf4j @Transactional(readOnly=true) public class CatalogoServiceImpl implements CatalogoService  {
    private final PropietarioRepository propietarios;
    private final PrestacionRepository prestaciones;
    private final MascotaRepository mascotas;
    private final AtencionRepository atenciones;
    private final DetalleAtencionRepository detalles;
    private final VetMapper mapper;
    private Prestacion obtenerPrestacion(Long id) {
        return prestaciones.findById(id).orElseThrow(()->new DominioException(404,"Prestación no encontrada"));
    }
    private Mascota obtenerMascota(Long id) {
        return mascotas.findById(id).orElseThrow(()->new DominioException(404,"Mascota no encontrada"));
    }
    public List<PropietarioResponseDTO> propietarios() {
        return propietarios.findAll().stream().map(mapper::propietario).toList();
    }
    public List<PrestacionResponseDTO> prestaciones(String especie) {
        return (especie==null?prestaciones.findAll():prestaciones.findByEspecieAplicableIn(List.of(especie,"TODAS"))).stream().map(mapper::prestacion).toList();
    }
    public PrestacionResponseDTO prestacion(Long id) {
        return mapper.prestacion(obtenerPrestacion(id));
    }
    @Transactional public PrestacionResponseDTO guardarPrestacion(Long id,PrestacionRequestDTO d) {
        Prestacion p=id==null?new Prestacion():obtenerPrestacion(id);
        if(id==null?prestaciones.existsByCodigo(d.codigo()):prestaciones.existsByCodigoAndIdNot(d.codigo(),id))throw new DominioException(409,"El código de prestación ya existe");
        p.setCodigo(d.codigo());
        p.setNombre(d.nombre());
        p.setPrecio(d.precio());
        p.setEspecieAplicable(d.especieAplicable());
        p.setActivo(d.activo()==null?true:d.activo());
        prestaciones.save(p);
        log.info("Prestación guardada {}",p.getId());
        return mapper.prestacion(p);
    }
    @Transactional public void eliminarPrestacion(Long id) {
        Prestacion p=obtenerPrestacion(id);
        if(detalles.existsByPrestacionId(id))throw new DominioException(409,"Prestación usada en atenciones; utilice la baja por estado");
        prestaciones.delete(p);
        log.info("Prestación eliminada {}",id);
    }
    @Transactional public PrestacionResponseDTO estado(Long id,boolean activo) {
        Prestacion p=obtenerPrestacion(id);
        p.setActivo(activo);
        log.info("Estado de prestación {}: {}",id,activo);
        return mapper.prestacion(p);
    }
    public List<MascotaResponseDTO> mascotas(Long propietarioId) {
        return (propietarioId==null?mascotas.findAll():mascotas.findByPropietarioId(propietarioId)).stream().map(mapper::mascota).toList();
    }
    public MascotaResponseDTO mascota(Long id) {
        return mapper.mascota(obtenerMascota(id));
    }
    @Transactional public MascotaResponseDTO guardarMascota(Long id,MascotaRequestDTO d) {
        Mascota m=id==null?new Mascota():obtenerMascota(id);
        Propietario p=propietarios.findById(d.propietarioId()).orElseThrow(()->new DominioException(404,"Propietario no encontrado"));
        if(id==null?mascotas.existsByNombreAndPropietarioId(d.nombre(),p.getId()):mascotas.existsByNombreAndPropietarioIdAndIdNot(d.nombre(),p.getId(),id))throw new DominioException(409,"El propietario ya tiene una mascota con ese nombre");
        m.setNombre(d.nombre());
        m.setEspecie(d.especie());
        m.setRaza(d.raza());
        m.setFechaNacimiento(d.fechaNacimiento());
        m.setSexo(d.sexo());
        m.setPropietario(p);
        mascotas.save(m);
        log.info("Mascota guardada {}",m.getId());
        return mapper.mascota(m);
    }
    @Transactional public void eliminarMascota(Long id) {
        Mascota m=obtenerMascota(id);
        if(atenciones.existsByMascotaId(id))throw new DominioException(409,"No se puede eliminar una mascota con atenciones");
        mascotas.delete(m);
        log.info("Mascota eliminada {}",id);
    }
}
