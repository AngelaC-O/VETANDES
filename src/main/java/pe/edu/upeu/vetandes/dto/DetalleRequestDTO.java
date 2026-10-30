package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record DetalleRequestDTO(@NotNull Long prestacionId, @NotNull @Min(1) @Max(3) Integer cantidad)  {
}
