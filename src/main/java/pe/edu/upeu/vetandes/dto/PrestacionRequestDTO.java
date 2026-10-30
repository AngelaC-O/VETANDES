package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record PrestacionRequestDTO(@NotBlank @Pattern(regexp="PRS-\\d{3}") String codigo, @NotBlank @Size(min=3,max=80) String nombre, @NotNull @DecimalMin(value="0",inclusive=false) BigDecimal precio, @NotNull @Pattern(regexp="PERRO|GATO|TODAS") String especieAplicable, Boolean activo)  {
}
