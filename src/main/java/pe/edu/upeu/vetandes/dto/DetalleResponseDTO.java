package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record DetalleResponseDTO(Long id, Long prestacionId, String prestacionNombre, Integer cantidad, BigDecimal precioUnitario, BigDecimal subtotal)  {
}
