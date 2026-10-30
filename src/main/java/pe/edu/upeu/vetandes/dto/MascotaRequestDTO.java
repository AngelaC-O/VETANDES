package pe.edu.upeu.vetandes.dto;
import java.math.BigDecimal;
import java.time.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public record MascotaRequestDTO(@NotBlank @Size(min=2,max=40) String nombre, @NotNull @Pattern(regexp="PERRO|GATO|OTRO") String especie, @Size(max=40) String raza, @NotNull @PastOrPresent LocalDate fechaNacimiento, @NotNull @Pattern(regexp="M|H") String sexo, @NotNull Long propietarioId)  {
}
