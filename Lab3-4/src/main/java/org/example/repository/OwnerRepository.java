package org.example.repository;

import org.example.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> getAllByName(String name);
    Owner findByEmail(String email);
}