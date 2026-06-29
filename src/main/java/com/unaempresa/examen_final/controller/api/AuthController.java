package com.unaempresa.examen_final.controller.api;

import com.unaempresa.examen_final.dto.LoginRequestDTO;
import com.unaempresa.examen_final.dto.LoginResponseDTO;
import com.unaempresa.examen_final.exception.CredencialesInvalidasException;
import com.unaempresa.examen_final.model.Usuario;
import com.unaempresa.examen_final.repository.UsuarioRepository;
import com.unaempresa.examen_final.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CredencialesInvalidasException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new CredencialesInvalidasException("Credenciales inválidas");
        }

        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}