package com.example.practiceSecurity.Dao;

import com.example.practiceSecurity.Domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // JpaRepository provides all basic CRUD methods
}
