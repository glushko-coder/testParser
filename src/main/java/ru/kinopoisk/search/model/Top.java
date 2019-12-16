package ru.kinopoisk.search.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="top")
public class Top implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int position;
    @Column
    private String movieTitle;
    @Column
    private int year;
    @Column
    private float rating;
    @Column
    private int vote;
    @Column
    private LocalDate date;

    public Top() {}

    public Top(int position, String movieTitle, int year, float rating, int vote, LocalDate date) {
        this.position = position;
        this.movieTitle = movieTitle;
        this.year = year;
        this.rating = rating;
        this.vote = vote;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Top)) return false;
        Top top = (Top) o;
        return getPosition() == top.getPosition() &&
                getYear() == top.getYear() &&
                Float.compare(top.getRating(), getRating()) == 0 &&
                getVote() == top.getVote() &&
                Objects.equals(getId(), top.getId()) &&
                Objects.equals(getMovieTitle(), top.getMovieTitle()) &&
                Objects.equals(getDate(), top.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPosition(), getMovieTitle(), getYear(), getRating(), getVote(), getDate());
    }

}
