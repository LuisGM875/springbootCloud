package com.example.crud.repository;

import com.example.crud.entity.person;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<person, Long> {

    Optional<person> findPersonByEmail(String email);

}
