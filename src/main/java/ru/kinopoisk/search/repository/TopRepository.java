package ru.kinopoisk.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinopoisk.search.model.Top;

@Repository
public interface TopRepository extends JpaRepository<Top, Long> {

}
