package pe.edu.upeu.vetandes.controller;
import pe.edu.upeu.vetandes.dto.*;
import pe.edu.upeu.vetandes.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController @RequestMapping("/api/v1/propietarios") @RequiredArgsConstructor public class PropietarioController  {
    private final CatalogoService service;
    @GetMapping @PreAuthorize("hasAuthority('MASCOTA_LEER')") public List<PropietarioResponseDTO> lista() {
        return service.propietarios();
    }
}
