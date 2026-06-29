package com.unaempresa.examen_final.config;

import com.unaempresa.examen_final.model.Usuario;
import com.unaempresa.examen_final.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByEmail("admin@email.com").isEmpty()) {
            Usuario admin = new Usuario(
                    "admin@email.com",
                    passwordEncoder.encode("admin123"),
                    "ADMIN"
            );
            usuarioRepository.save(admin);
            log.info("Admin creado: admin@email.com / admin123");
        }
    }
}