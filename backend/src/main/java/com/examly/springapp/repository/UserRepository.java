package com.examly.springapp.repository;

import com.examly.springapp.model.Role;
import com.examly.springapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByIsActiveTrue();
    boolean existsByEmail(String email);
}