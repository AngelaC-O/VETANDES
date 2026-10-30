package pe.edu.upeu.vetandes.service;
import pe.edu.upeu.vetandes.dto.*;
import java.util.List;
public interface CatalogoService  {
    List<PropietarioResponseDTO> propietarios();
    List<PrestacionResponseDTO> prestaciones(String especie);
    PrestacionResponseDTO prestacion(Long id);
    PrestacionResponseDTO guardarPrestacion(Long id,PrestacionRequestDTO dto);
    void eliminarPrestacion(Long id);
    PrestacionResponseDTO estado(Long id,boolean activo);
    List<MascotaResponseDTO> mascotas(Long propietarioId);
    MascotaResponseDTO mascota(Long id);
    MascotaResponseDTO guardarMascota(Long id,MascotaRequestDTO dto);
    void eliminarMascota(Long id);
}
