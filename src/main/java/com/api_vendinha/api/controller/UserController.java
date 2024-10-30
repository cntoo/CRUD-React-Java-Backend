package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciar operações relacionadas aos usuários.
 */
@RestController
@RequestMapping("/api/users") // Define o caminho base para as requisições deste controlador.
@CrossOrigin(origins = "*")  // Permite requisições CORS do React
public class UserController {

    // Injeção de dependência do serviço de usuários.
    private final UserServiceInterface userService;

    /**
     * Construtor para injeção de dependência do serviço de usuários.
     *
     * @param userService O serviço de usuários a ser injetado.
     */
    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Método para salvar um novo usuário.
     *
     * @param userRequestDto DTO que contém os dados do usuário a ser salvo.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado.
     */
    @PostMapping
    public UserResponseDto salvar(@RequestBody UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);
    }

    /**
     * Método para atualizar um usuário existente.
     *
     * @param userRequestDto DTO com os dados do usuário atualizados.
     * @param id            ID do usuário a ser atualizado.
     * @return DTO com as informações atualizadas do usuário.
     */
    @PutMapping("/user/{id}")
    public UserResponseDto atualizar(@RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        return userService.atualizar(userRequestDto, id);
    }

    /**
     * Método para buscar um usuário pelo ID.
     *
     * @param id ID do usuário a ser buscado.
     * @return DTO com as informações do usuário encontrado.
     */
    @GetMapping("/user/{id}")
    public UserResponseDto buscar(@PathVariable Long id) {
        return userService.buscar(id);
    }

    /**
     * Método para atualizar o status de um usuário.
     *
     * @param id     ID do usuário a ser atualizado.
     * @param status Novo status do usuário.
     * @return DTO com as informações atualizadas do usuário.
     */
    @PutMapping("/user/{id}/{status}")
    public UserResponseDto atualizarStatus(@PathVariable Long id, @PathVariable boolean status) {
        return userService.atualizarStatus(status, id);
    }

    @GetMapping("/")
    public List<UserResponseDto> listarUsers() {
        return userService.listarUsers();
    }
}
