package com.georges.georges.user.repository;

import com.georges.georges.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    boolean existsByPseudoOrEmail(String pseudo, String email);

    boolean existsById(Long id);

}
