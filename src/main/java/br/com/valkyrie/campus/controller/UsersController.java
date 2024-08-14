package br.com.valkyrie.campus.controller;

import br.com.valkyrie.campus.model.dtos.UsersSignupDto;
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
public class UsersController {

    private final UsersService service;

    @PostMapping
    @RequestMapping("signup")
    public final ResponseEntity<Users> signupUser(@RequestBody @Valid UsersSignupDto dto) {
        return new ResponseEntity<>(service.createUser(dto), HttpStatus.CREATED);
    }

    //TODO - Mudar para controller próprio de usuário.
    @GetMapping
    @RequestMapping("list/{usertag}")
    public final ResponseEntity<Users> getUser(@PathVariable String usertag) {
        return new ResponseEntity<>(service.getUser(usertag), HttpStatus.OK);
    }
}
