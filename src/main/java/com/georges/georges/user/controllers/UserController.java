package com.georges.georges.user.controllers;

import com.georges.georges.user.models.User;
import com.georges.georges.user.repository.UserRepository;
import com.georges.georges.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody User user) {
        if (userService.existsByPseudoOrEmail(user.getPseudo(), user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'utilisateur existe déjà.");
        } else {
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(user.getMotdepasse());
                user.setMotdepasse(encodedPassword);
                userService.createUser(user);
                return new ResponseEntity<>("User créé avec sucess", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Erreur dans la creation de l'user ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id", required = true) User user,
                                         @Valid @RequestBody User userUpdate) {
        if (!userService.existsById(user.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'utilisateur n'existe pas");
        } else {

            userUpdate.setId(user.getId());
            userRepository.save(userUpdate);
            return new ResponseEntity<>("utilisateur mis à jour", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("Utilisateur supprimé avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la suppression de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}