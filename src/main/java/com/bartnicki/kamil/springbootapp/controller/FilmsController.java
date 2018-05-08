package com.bartnicki.kamil.springbootapp.controller;


import com.bartnicki.kamil.springbootapp.model.Films;
import com.bartnicki.kamil.springbootapp.model.FilmJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmsController {

    @Autowired
    private FilmJpaRepository filmJpaRepository;

    @GetMapping(value = "/all")
    public List<Films> findAll() {
        return filmJpaRepository.findAll();
    }

    @GetMapping(value = "/{name}")
    public Films findByName(@PathVariable final String name) {
        return filmJpaRepository.findByName(name);
    }

    @PostMapping(value = "/add")
    public Films add(@RequestBody final Films film) {
        if (film.getName() != null)
            if (filmJpaRepository.findByName(film.getName()) != null)
                throw new FilmNameAlreadyExistException(film.getName());
        filmJpaRepository.save(film);
        return filmJpaRepository.findByName(film.getName());
    }

}
