package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record UsuarioResponseDTO(String username, String nombreCompleto, java.util.Set<String> roles, java.util.Set<String> permisos)  {
}
