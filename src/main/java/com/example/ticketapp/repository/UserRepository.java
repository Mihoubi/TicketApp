package com.example.ticketapp.repository;

import com.example.ticketapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u.password FROM User u WHERE u.name = :userName")
    String findByUserName(String userName);

    @Query(value = "SELECT u.id FROM User u WHERE u.name = :email")
    Long getIdByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE u.id = :id")
    User getUserById(Long id);
}
