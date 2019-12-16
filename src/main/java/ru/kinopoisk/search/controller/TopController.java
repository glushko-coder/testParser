package ru.kinopoisk.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kinopoisk.search.model.Top;
import ru.kinopoisk.search.service.impl.TopServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TopController {

    @Autowired
    TopServiceImpl topServiceImpl;

    @GetMapping("/")
    public void kinopoiskParser() throws IOException {
        topServiceImpl.getTop();
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Top>> getTopTenByDate(@PathVariable("date") String date) {
        LocalDate local = LocalDate.parse(date);
        return new ResponseEntity<>(topServiceImpl.getTopTenByDate(local), HttpStatus.OK);
    }

}
