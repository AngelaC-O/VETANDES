package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record PrestacionResponseDTO(Long id, String codigo, String nombre, BigDecimal precio, String especieAplicable, Boolean activo)  {
}
