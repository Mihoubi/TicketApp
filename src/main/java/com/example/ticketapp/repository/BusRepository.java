package com.example.ticketapp.repository;

import com.example.ticketapp.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
//    @Query(value = "SELECT * FROM Bus b WHERE b.name = :name", nativeQuery = true)
    @Query("SELECT b FROM Bus b WHERE b.name = :name")
    List<Bus> findByName(@Param("name") String name);
}
