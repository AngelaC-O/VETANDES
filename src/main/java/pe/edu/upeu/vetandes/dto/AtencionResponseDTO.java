package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record AtencionResponseDTO(Long id, String numero, LocalDateTime fecha, Long mascotaId, String mascotaNombre, String propietarioNombre, String especie, BigDecimal peso, String diagnostico, String estado, BigDecimal subtotal, BigDecimal descuento, BigDecimal total, String usuarioRegistro, String motivoAnulacion, java.util.List<DetalleResponseDTO> detalles)  {
}
