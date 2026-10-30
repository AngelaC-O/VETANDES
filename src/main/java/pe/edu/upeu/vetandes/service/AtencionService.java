package pe.edu.upeu.vetandes.service;
import pe.edu.upeu.vetandes.dto.*;
import java.util.List;
import java.time.LocalDate;
public interface AtencionService  {
    AtencionResponseDTO registrar(AtencionRequestDTO dto,String usuario);
    AtencionResponseDTO anular(Long id,String motivo);
    AtencionResponseDTO obtener(Long id);
    List<AtencionResponseDTO> consultar(String mascota,String especie,String estado,LocalDate desde,LocalDate hasta);
    List<ReporteEspecieDTO> reporte(LocalDate desde,LocalDate hasta);
}
