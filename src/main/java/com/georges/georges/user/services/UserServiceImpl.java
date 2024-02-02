package com.georges.georges.user.services;

import com.georges.georges.user.models.User;
import com.georges.georges.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
    }

    @Override
    public boolean existsByPseudoOrEmail(String pseudo, String email) {
        return userRepository.existsByPseudoOrEmail(pseudo, email);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

}
