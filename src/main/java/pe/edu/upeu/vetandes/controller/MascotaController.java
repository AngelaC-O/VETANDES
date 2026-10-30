package pe.edu.upeu.vetandes.controller;
import pe.edu.upeu.vetandes.dto.*;
import pe.edu.upeu.vetandes.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController @RequestMapping("/api/v1/mascotas") @RequiredArgsConstructor public class MascotaController  {
    private final CatalogoService service;
    @GetMapping @PreAuthorize("hasAuthority('MASCOTA_LEER')") public List<MascotaResponseDTO> lista(@RequestParam(required=false) Long propietarioId) {
        return service.mascotas(propietarioId);
    }
    @GetMapping("/{id}") @PreAuthorize("hasAuthority('MASCOTA_LEER')") public MascotaResponseDTO obtener(@PathVariable Long id) {
        return service.mascota(id);
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) @PreAuthorize("hasAuthority('MASCOTA_ESCRIBIR')") public MascotaResponseDTO crear(@Valid @RequestBody MascotaRequestDTO d) {
        return service.guardarMascota(null,d);
    }
    @PutMapping("/{id}") @PreAuthorize("hasAuthority('MASCOTA_ESCRIBIR')") public MascotaResponseDTO editar(@PathVariable Long id,@Valid @RequestBody MascotaRequestDTO d) {
        return service.guardarMascota(id,d);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) @PreAuthorize("hasAuthority('MASCOTA_ESCRIBIR')") public void eliminar(@PathVariable Long id) {
        service.eliminarMascota(id);
    }
}
