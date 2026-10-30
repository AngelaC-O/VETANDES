package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record AtencionRequestDTO(@NotNull Long mascotaId, @NotNull @DecimalMin(value="0",inclusive=false) @Digits(integer=3,fraction=2) BigDecimal peso, @Size(max=300) String diagnostico, @NotNull @Size(min=1,max=8) java.util.List<@NotNull @Valid DetalleRequestDTO> detalles)  {
}
