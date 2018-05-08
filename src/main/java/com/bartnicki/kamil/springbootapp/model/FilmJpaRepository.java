package com.bartnicki.kamil.springbootapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FilmJpaRepository extends JpaRepository<Films, Long> {

    Films findByName(String name);

}
