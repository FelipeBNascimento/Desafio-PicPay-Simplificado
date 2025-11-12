package com.picpay.desafiopicpay.controller;

import com.picpay.desafiopicpay.business.UserService;
import com.picpay.desafiopicpay.dtos.requests.UserRequest;
import com.picpay.desafiopicpay.dtos.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criarusuario(@RequestBody UserRequest request){

        service.criarUsuario(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserResponse> visualizarusuario(@RequestParam Long id){

        return ResponseEntity.ok(service.visualizar(id));
    }
}
