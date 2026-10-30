package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record MascotaResponseDTO(Long id, String nombre, String especie, String raza, LocalDate fechaNacimiento, String sexo, Long propietarioId, String propietarioNombre)  {
}
