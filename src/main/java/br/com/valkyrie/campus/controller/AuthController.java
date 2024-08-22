package br.com.valkyrie.campus.controller;

import br.com.valkyrie.campus.model.dtos.UsersSignupDto;
import br.com.valkyrie.campus.model.dtos.responses.UsersResponseDto;
import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersService service;

    @PostMapping
    @RequestMapping("signup")
    public final ResponseEntity<UsersResponseDto> signupUser(@RequestBody @Valid UsersSignupDto dto) {
        return new ResponseEntity<>(service.createUser(dto), HttpStatus.CREATED);
    }
}
