package br.com.facol.uservault.controller;

import br.com.facol.uservault.dto.AuthResponse;
import br.com.facol.uservault.dto.CredentialsDTO;
import br.com.facol.uservault.security.UserSS;
import br.com.facol.uservault.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid CredentialsDTO credentials){
        var usernamePassword = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserSS) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
