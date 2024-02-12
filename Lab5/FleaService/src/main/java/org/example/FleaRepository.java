package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FleaRepository extends JpaRepository<Flea, Long> {
    List<Flea> getAllByCatId(Long id);
    List<Flea> getAllByName(String name);
}