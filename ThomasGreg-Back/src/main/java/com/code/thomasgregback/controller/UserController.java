package com.code.thomasgregback.controller;

import com.code.thomasgregback.dto.LoginUserDto;
import com.code.thomasgregback.dto.ResponseDto;
import com.code.thomasgregback.dto.UserDto;
import com.code.thomasgregback.entity.User;
import com.code.thomasgregback.service.role.RoleService;
import com.code.thomasgregback.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/thomasgreg/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) { this.roleService = roleService; }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto body) {
        try {
            String mail = body.getMail();
            String password = body.getPassword();
            if (!this.userService.existsByMailAndPassword(mail, password))
                return new ResponseEntity<>(new ResponseDto(false, "¡Datos ingresados incorrectamente!", null), HttpStatus.NOT_ACCEPTABLE);

            Optional<User> user = this.userService.findByMailAndPassword(mail, password);

            return new ResponseEntity<>(new ResponseDto(true, "¡Bienvenido a Thomas Greg!", user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al inicar sesión!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            List<User> userList = this.userService.getAll();
            return new ResponseEntity<>(new ResponseDto(true, "Usuarios encontrados con exito", userList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al recuperar los usuarios", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDto body) {
        try {
            String mail = body.getMail();
            if (this.userService.existsByMail(mail))
                return new ResponseEntity<>(new ResponseDto(false, "¡Este usuario ya existe!", null), HttpStatus.NOT_ACCEPTABLE);

            User user = User.builder()
                    .name(body.getName())
                    .lastname(body.getLastname())
                    .mail(body.getMail())
                    .password(body.getPassword())
                    .role(this.roleService.findById(body.getRole()).orElseThrow())
                    .build();

            User userSaved = this.userService.save(user);

            return new ResponseEntity<>(new ResponseDto(true, "Usuario creado con exito!", userSaved), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al crear usuario!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDto body) {
        try {
            Long id = body.getId();
            if (!this.userService.existsById(id))
                return new ResponseEntity<>(new ResponseDto(false, "¡Este usuario no existe!", null), HttpStatus.NOT_ACCEPTABLE);

            User user = User.builder()
                    .id(id)
                    .name(body.getName())
                    .lastname(body.getLastname())
                    .mail(body.getMail())
                    .password(body.getPassword())
                    .role(this.roleService.findById(body.getRole()).orElseThrow())
                    .build();

            User userUpdate = this.userService.save(user);

            return new ResponseEntity<>(new ResponseDto(true, "Usuario actualizado con exito!", userUpdate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al actualizar usuario!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (!this.userService.existsById(id))
                return new ResponseEntity<>(new ResponseDto(false, "¡Este usuario no existe!", null), HttpStatus.NOT_ACCEPTABLE);

            this.userService.deleteById(id);

            return new ResponseEntity<>(new ResponseDto(true, "Usuario eliminado con exito!", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al eliminar usuario!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/by/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            if (!this.userService.existsById(id))
                return new ResponseEntity<>(new ResponseDto(false, "¡Este usuario no existe!", null), HttpStatus.NOT_ACCEPTABLE);

            User user = this.userService.findById(id).orElseThrow();

            return new ResponseEntity<>(new ResponseDto(true, "Usuario encotrado con exito!", user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al buscar el usuario!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
