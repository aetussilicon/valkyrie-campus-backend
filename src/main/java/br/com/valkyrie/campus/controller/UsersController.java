package br.com.valkyrie.campus.controller;

import br.com.valkyrie.campus.model.entities.Users;
import br.com.valkyrie.campus.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @GetMapping
    @RequestMapping("list/{usertag}")
    public final ResponseEntity<Users> getUser(@PathVariable String usertag) {
        return new ResponseEntity<>(service.getUser(usertag), HttpStatus.OK);
    }
}
