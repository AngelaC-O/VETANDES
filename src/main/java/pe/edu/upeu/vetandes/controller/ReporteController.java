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
@RestController @RequestMapping("/api/v1/reportes") @RequiredArgsConstructor public class ReporteController  {
    private final AtencionService service;
    @GetMapping("/atenciones-por-especie") @PreAuthorize("hasAuthority('REPORTE_VER')") public List<ReporteEspecieDTO> reporte(@RequestParam(required=false) LocalDate desde,@RequestParam(required=false) LocalDate hasta) {
        return service.reporte(desde,hasta);
    }
}
