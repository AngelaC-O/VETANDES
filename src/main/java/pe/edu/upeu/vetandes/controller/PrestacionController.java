package pe.edu.upeu.vetandes.controller;
import pe.edu.upeu.vetandes.dto.*;
import pe.edu.upeu.vetandes.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController @RequestMapping("/api/v1/prestaciones") @RequiredArgsConstructor public class PrestacionController  {
    private final CatalogoService service;
    @GetMapping @PreAuthorize("hasAuthority('PRESTACION_LEER')") public List<PrestacionResponseDTO> lista(@RequestParam(required=false) String especie) {
        return service.prestaciones(especie);
    }
    @GetMapping("/{id}") @PreAuthorize("hasAuthority('PRESTACION_LEER')") public PrestacionResponseDTO obtener(@PathVariable Long id) {
        return service.prestacion(id);
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) @PreAuthorize("hasAuthority('PRESTACION_ESCRIBIR')") public PrestacionResponseDTO crear(@Valid @RequestBody PrestacionRequestDTO d) {
        return service.guardarPrestacion(null,d);
    }
    @PutMapping("/{id}") @PreAuthorize("hasAuthority('PRESTACION_ESCRIBIR')") public PrestacionResponseDTO editar(@PathVariable Long id,@Valid @RequestBody PrestacionRequestDTO d) {
        return service.guardarPrestacion(id,d);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) @PreAuthorize("hasAuthority('PRESTACION_ESCRIBIR')") public void eliminar(@PathVariable Long id) {
        service.eliminarPrestacion(id);
    }
    @PatchMapping("/{id}/estado") @PreAuthorize("hasAuthority('PRESTACION_ESCRIBIR')") public PrestacionResponseDTO estado(@PathVariable Long id,@Valid @RequestBody EstadoRequestDTO d) {
        return service.estado(id,d.activo());
    }
}
