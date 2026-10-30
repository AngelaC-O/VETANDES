package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record AnularRequestDTO(@NotBlank @Size(min=10,max=200) String motivo)  {
}
