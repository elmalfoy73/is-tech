package org.example;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> getAllByOwnerId(Long id);
    List<Cat> getAllByName(String name);

}
