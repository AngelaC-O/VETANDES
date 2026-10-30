package pe.edu.upeu.vetandes.service;
import pe.edu.upeu.vetandes.dto.*;
public interface AuthService  {
    LoginResponseDTO login(LoginRequestDTO dto);
    UsuarioResponseDTO actual(String username);
}
