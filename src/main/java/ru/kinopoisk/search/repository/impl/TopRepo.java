package ru.kinopoisk.search.repository.impl;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.kinopoisk.search.model.Top;
import ru.kinopoisk.search.repository.AbstractRepo;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TopRepo extends AbstractRepo<Top> {

    public List<Top> getTopTenByDate(LocalDate date) {
        Query query = entityManager.createQuery("SELECT t FROM Top t WHERE t.date =:date ORDER BY t.rating DESC", clazz);
        query.setParameter("date", date);
        query.setFirstResult(0);
        query.setMaxResults (10);
        return query.getResultList();
    }

}
