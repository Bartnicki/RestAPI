package com.bartnicki.kamil.springbootapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FilmNameAlreadyExistException extends RuntimeException {
    public FilmNameAlreadyExistException(String name) {
        super("Object with name = " + name + " is already exist!");
    }
}