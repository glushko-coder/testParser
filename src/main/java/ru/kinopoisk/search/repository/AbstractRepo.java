package ru.kinopoisk.search.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Repository("abstractRepo")
public abstract class AbstractRepo<T extends Serializable> {

    protected Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractRepo() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
