package pe.edu.upeu.vetandes.service;
import pe.edu.upeu.vetandes.dto.*;
import pe.edu.upeu.vetandes.entity.*;
import pe.edu.upeu.vetandes.repository.*;
import pe.edu.upeu.vetandes.mapper.VetMapper;
import pe.edu.upeu.vetandes.exception.DominioException;
import java.util.*;
import java.time.*;
import java.math.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service @RequiredArgsConstructor @Slf4j @Transactional(readOnly=true) public class AtencionServiceImpl implements AtencionService  {
    private final AtencionRepository atenciones;
    private final MascotaRepository mascotas;
    private final PrestacionRepository prestaciones;
    private final VetMapper mapper;
    private Atencion buscar(Long id) {
        return atenciones.findById(id).orElseThrow(()->new DominioException(404,"Atención no encontrada"));
    }
    @Transactional public AtencionResponseDTO registrar(AtencionRequestDTO d,String usuario) {
        if(d.detalles()==null||d.detalles().isEmpty()||d.detalles().size()>8||d.peso()==null||d.peso().signum()<=0)throw new DominioException(400,"Peso o cantidad de detalles inválidos");
        Mascota mascota=mascotas.findById(d.mascotaId()).orElseThrow(()->new DominioException(404,"Mascota no encontrada"));
        Atencion a=new Atencion();
        a.setMascota(mascota);
        a.setPeso(d.peso());
        a.setDiagnostico(d.diagnostico());
        a.setFecha(LocalDateTime.now());
        a.setEstado("REGISTRADA");
        a.setUsuarioRegistro(usuario);
        a.setNumero("AT-%06d".formatted(atenciones.siguienteNumero()));
        Set<Long> ids=new HashSet<>();
        BigDecimal suma=BigDecimal.ZERO;
        for(DetalleRequestDTO linea:d.detalles()) {
            if(!ids.add(linea.prestacionId())||linea.cantidad()==null||linea.cantidad()<1||linea.cantidad()>3)throw new DominioException(400,"Prestación repetida o cantidad fuera de 1 a 3");
            Prestacion p=prestaciones.findById(linea.prestacionId()).orElseThrow(()->new DominioException(404,"Prestación no encontrada"));
            if(!p.getActivo()||!(p.getEspecieAplicable().equals("TODAS")||p.getEspecieAplicable().equals(mascota.getEspecie())))throw new DominioException(409,"Prestación inactiva o incompatible con la especie: "+p.getNombre());
            DetalleAtencion l=new DetalleAtencion();
            l.setAtencion(a);
            l.setPrestacion(p);
            l.setCantidad(linea.cantidad());
            l.setPrecioUnitario(p.getPrecio());
            l.setSubtotal(p.getPrecio().multiply(BigDecimal.valueOf(linea.cantidad())).setScale(2,RoundingMode.HALF_UP));
            a.getDetalles().add(l);
            suma=suma.add(l.getSubtotal());
        }
        a.setSubtotal(suma);
        a.setDescuento(ids.size()>=3?suma.multiply(new BigDecimal("0.10")).setScale(2,RoundingMode.HALF_UP):BigDecimal.ZERO.setScale(2));
        a.setTotal(suma.subtract(a.getDescuento()));
        atenciones.saveAndFlush(a);
        log.info("Atención registrada {} por {}",a.getNumero(),usuario);
        return mapper.atencion(a);
    }
    @Transactional public AtencionResponseDTO anular(Long id,String motivo) {
        Atencion a=buscar(id);
        if(motivo==null||motivo.trim().length()<10||motivo.trim().length()>200)throw new DominioException(400,"El motivo debe tener entre 10 y 200 caracteres");
        if(!a.getEstado().equals("REGISTRADA")||LocalDateTime.now().isAfter(a.getFecha().plusHours(48)))throw new DominioException(409,"Solo se anulan atenciones registradas dentro de las 48 horas");
        a.setEstado("ANULADA");
        a.setMotivoAnulacion(motivo.trim());
        log.info("Atención anulada {}",a.getNumero());
        return mapper.atencion(a);
    }
    public AtencionResponseDTO obtener(Long id) {
        return mapper.atencion(buscar(id));
    }
    private void rango(LocalDate d,LocalDate h) {
        if(d!=null&&h!=null&&d.isAfter(h))throw new DominioException(400,"Desde no puede ser posterior a hasta");
    }
    public List<AtencionResponseDTO> consultar(String mascota,String especie,String estado,LocalDate desde,LocalDate hasta) {
        rango(desde,hasta);
        return atenciones.consultar(mascota,especie,estado,desde==null?null:desde.atStartOfDay(),hasta==null?null:hasta.plusDays(1).atStartOfDay()).stream().map(mapper::atencion).toList();
    }
    public List<ReporteEspecieDTO> reporte(LocalDate desde,LocalDate hasta) {
        rango(desde,hasta);
        return atenciones.reporte(desde==null?null:desde.atStartOfDay(),hasta==null?null:hasta.plusDays(1).atStartOfDay());
    }
}
