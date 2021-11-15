package com.vlad.tms.diploma.repository;

import com.vlad.tms.diploma.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
