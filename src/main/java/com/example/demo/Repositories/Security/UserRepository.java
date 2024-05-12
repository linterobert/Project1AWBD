package com.example.demo.Repositories.Security;

import com.example.demo.Domain.Entities.Security.Role;
import com.example.demo.Domain.Entities.Security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findById(int id);
    Boolean existsByUsername(String username);

    @Query("SELECT u.roles FROM UserEntity u WHERE u.id = :id")
    List<Role> findRolesById(int id);
}
