package com.georges.georges.user.services;

import com.georges.georges.user.models.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    List<User> getAllUsers();

    void deleteUserById(Long id);

    boolean existsByPseudoOrEmail(String pseudo, String email);

    boolean existsById(Long id);

}
