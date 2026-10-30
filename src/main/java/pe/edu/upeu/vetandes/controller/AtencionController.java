package pe.edu.upeu.vetandes.controller;
import pe.edu.upeu.vetandes.dto.*;
import pe.edu.upeu.vetandes.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.time.LocalDate;
@RestController @RequestMapping("/api/v1/atenciones") @RequiredArgsConstructor public class AtencionController  {
    private final AtencionService service;
    @PostMapping @ResponseStatus(HttpStatus.CREATED) @PreAuthorize("hasAuthority('ATENCION_REGISTRAR')") public AtencionResponseDTO registrar(@Valid @RequestBody AtencionRequestDTO d,java.security.Principal p) {
        return service.registrar(d,p.getName());
    }
    @GetMapping @PreAuthorize("hasAuthority('ATENCION_LEER')") public List<AtencionResponseDTO> consultar(@RequestParam(required=false) String mascota,@RequestParam(required=false) String especie,@RequestParam(required=false) String estado,@RequestParam(required=false) LocalDate desde,@RequestParam(required=false) LocalDate hasta) {
        return service.consultar(mascota,especie,estado,desde,hasta);
    }
    @GetMapping("/{id}") @PreAuthorize("hasAuthority('ATENCION_LEER')") public AtencionResponseDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }
    @PatchMapping("/{id}/anular") @PreAuthorize("hasAuthority('ATENCION_ANULAR')") public AtencionResponseDTO anular(@PathVariable Long id,@Valid @RequestBody AnularRequestDTO d) {
        return service.anular(id,d.motivo());
    }
}
