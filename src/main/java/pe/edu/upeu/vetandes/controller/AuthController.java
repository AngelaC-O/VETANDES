package pe.edu.upeu.vetandes.controller;
import pe.edu.upeu.vetandes.dto.*;
import pe.edu.upeu.vetandes.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController @RequestMapping("/api/v1/auth") @RequiredArgsConstructor public class AuthController  {
    private final AuthService service;
    @PostMapping("/login") public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO d) {
        return service.login(d);
    }
    @GetMapping("/me") public UsuarioResponseDTO me(java.security.Principal p) {
        return service.actual(p.getName());
    }
}
