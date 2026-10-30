package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record EstadoRequestDTO(@NotNull Boolean activo)  {
}
